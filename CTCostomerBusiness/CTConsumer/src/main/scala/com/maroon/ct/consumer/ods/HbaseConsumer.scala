package com.maroon.ct.consumer.ods

import com.maroon.ct.consumer.bean.CallRecords
import com.maroon.ct.consumer.util.{MyKafkaUtil, OffsetManagerUtil}
import org.apache.commons.lang.time.FastDateFormat
import org.apache.hadoop.conf.Configuration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.util.Date

object HbaseConsumer {

  def main(args: Array[String]): Unit = {
    // TODO 1-创建ssc对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("hbaseconsumer")
    val ssc = new StreamingContext(conf, Seconds(5))
    // TODO 2-读取kafka中的数据
    // (1)主题名称和消费者组
    val topicName = "ct_callrecords"
    val groupId = "ct_call_business_group"
    // (2)获取偏移量offset
    val offset: Map[TopicPartition, Long] = OffsetManagerUtil.getOffset(topicName, groupId)
    // (3)获取流信息
    var kafkaDStream: InputDStream[ConsumerRecord[String, String]] = null
    if (offset != null && offset.size > 0) {
      kafkaDStream = MyKafkaUtil.getKafkaStream(ssc, topicName, groupId)
    } else {
      kafkaDStream = MyKafkaUtil.getKafkaStream(ssc, topicName, groupId, offset)
    }
    // TODO 3-获取当前采集周期后的offset
    var offsetRanges = Array.empty[OffsetRange]
    val offsetKafkaDstream: DStream[ConsumerRecord[String, String]] = kafkaDStream.transform(
      rdd => {
        offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd
      }
    )
    // TODO 4-获取到数据值
    val CallRecordsDStream: DStream[CallRecords] = offsetKafkaDstream.map {
      record =>
        val valuestr = record.value()
        val arrCalls: Array[String] = valuestr.split("\n")
        CallRecords(arrCalls(0), arrCalls(1), arrCalls(2), arrCalls(3), arrCalls(4), arrCalls(5)) // 重新封装成CallRecords
    }
    // TODO 5-写入Hbase
    import org.apache.phoenix.spark._
    CallRecordsDStream.foreachRDD{
      rdd =>
        rdd.saveToPhoenix(
        "CALLRECORDS",
        Seq("maincall","maincall_name","bycall","bycall_name","data_time","duration"),
        new Configuration,
        Some("hadoop01,hadoop02,hadoop03:2181")
      )
        rdd.foreach(
          callrecords =>
            System.out.println(callrecords)
        )
        // TODO 6-维护偏移量
        OffsetManagerUtil.saveOffset(topicName,groupId,offsetRanges)
        val dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
        System.out.println(s"-------------${dateFormat.format(new Date())}--------------")
    }
    // TODO 7-开启环境
    ssc.start()
    ssc.awaitTermination()
  }
}
