package com.maroon.ct.producer;

import com.maroon.ct.producer.bean.LocalFileProducer;
import com.maroon.ct.producer.io.LocalFileInput;
import com.maroon.ct.producer.io.LocalFileOutput;

import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) throws IOException {
        String intput_path = args[0];
        String output_path = args[1];

        LocalFileProducer producer = new LocalFileProducer();
        producer.setInput(new LocalFileInput(intput_path));
        producer.setOutput(new LocalFileOutput(output_path));
//        producer.setInput(new LocalFileInput("F:\\resouceDownloadPath\\baiDuCloud\\contact.log"));
//        producer.setOutput(new LocalFileOutput("F:\\resouceDownloadPath\\baiDuCloud\\call.log"));
        producer.produce();

    }
}


