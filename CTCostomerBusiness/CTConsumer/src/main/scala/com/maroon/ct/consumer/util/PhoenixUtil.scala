package com.maroon.ct.consumer.util

import com.alibaba.fastjson.JSONObject
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import scala.collection.mutable.ListBuffer

object PhoenixUtil {

  var propertiesName: String = _ // 配置文件平常
  var conn: Connection = _
  var resultSet: ResultSet = _
  var ps: PreparedStatement = _

  /**
   * 开启资源
   * @return
   */
  def open(): Connection = { // 获取到连接
    val properties = PropertiesUtil.load(propertiesName) // 加载配置文件参数
    Class.forName(properties.getProperty("phDriver")) // 驱动类phoenixDriver
    val conn: Connection = DriverManager.getConnection(properties.getProperty("phUrl")) // 获取连接配置
    conn
  }

  /**
   * 释放资源
   */
  def close(): Unit ={
    resultSet.close()
    ps.close()
    conn.close()
  }

  /*
  将Hbase中查询的结果返回成json对象串列表
   */
  def queryList(sql: String): List[JSONObject] = {
    conn = open()
    // 创建数据库操作对象
    ps = conn.prepareStatement(sql)
    // 执行sql语句
    resultSet = ps.executeQuery()
    val resMetaData = resultSet.getMetaData // 获取数据的元数据信息
    // 处理结果集
    val listBufferJson = new ListBuffer[JSONObject]()
    while (resultSet.next()) {
      // 创建JSON对象
      val jsonObject = new JSONObject()
      // 将结果封装成Json对象
      //{"erpcode":"xxx","if_new_material":"1"}
      for (i <- 1 to resMetaData.getColumnCount) {
        jsonObject.put(resMetaData.getColumnName(i), resultSet.getObject(i))
      }
      listBufferJson.append(jsonObject)
    }
    // 释放资源
    close()
    // 结果输出
    listBufferJson.toList
  }
}
