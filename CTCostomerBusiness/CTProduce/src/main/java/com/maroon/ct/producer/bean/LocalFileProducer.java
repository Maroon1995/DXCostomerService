package com.maroon.ct.producer.bean;

import com.maroon.ct.common.bean.DataIn;
import com.maroon.ct.common.bean.DataOut;
import com.maroon.ct.common.bean.Producer;
import com.maroon.ct.producer.process.DataProduct;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * 输出到本地
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    private volatile boolean flag = true;


    @Override
    public void setInput(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOutput(DataOut out) {
        this.out = out;
    }

    @Override
    public void produce() {

        try {
            // TODO 1-读取通讯录数据
            List<Contact> contacts = in.read(Contact.class);
            // TODO 2-模拟生成通话记录
            List<Contact> randomList; //随机抽取两个不同的电话号码
            DataProduct dataProduct = new DataProduct();
            while (flag) {
                // 从通讯录中随机查找2个电话号码作为主叫和被叫
                randomList = dataProduct.getRandomList(contacts, 2); //随机抽取两个不同的电话号码

                String maincall = randomList.get(0).getTel();
                String maincall_name = randomList.get(0).getName();
                String bycall = randomList.get(1).getTel();
                String bycall_name = randomList.get(1).getName();

                // 生成随机的建立通话时间
                String data_time = dataProduct.randomDate("2021-01-01", "2022-01-01");

                // 生成随机的通话时长(30分钟内_0600)
                int duar = new Random().nextInt(60 * 30) + 1;
                String duration =  new DecimalFormat("0000").format(duar);
                // 生成通话记录
                CallRecords callRecords = new CallRecords(maincall, maincall_name, bycall, bycall_name, data_time, duration);
                System.out.println(callRecords.toString());
                // TODO 3-将通话记录写到数据本地日志文件中
                out.write(callRecords.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }

}
