package com.maroon.ct.producer;

import com.maroon.ct.producer.bean.LocalFileProducer;
import com.maroon.ct.producer.io.LocalFileInput;

public class Bootstrap {

    public static void main(String[] args) {

        LocalFileProducer producer = new LocalFileProducer();
        producer.setInput(new LocalFileInput("F:\\resouceDownloadPath\\baiDuCloud\\contact.log"));

        producer.produce();

    }
}


