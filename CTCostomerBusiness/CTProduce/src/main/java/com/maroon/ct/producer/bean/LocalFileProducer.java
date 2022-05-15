package com.maroon.ct.producer.bean;

import com.maroon.ct.common.bean.DataIn;
import com.maroon.ct.common.bean.DataOut;
import com.maroon.ct.common.bean.Producer;

import java.io.IOException;
import java.util.List;

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
            // 读取通讯录数据
            List<Contact> contacts = in.read(Contact.class);
            for (Contact contact : contacts) {
                System.out.println(contact.toString());
            }

//            while (flag) {
//                // 从通讯录中随机查找2个电话号码作为主叫和被叫
//
//                // 生成随机的通话时间
//
//                // 生成随机的通话时长
//
//                // 生成通话记录
//
//                // 将通话记录写到数据文件中
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }
}
