package com.maroon.ct.producer.io;

import com.maroon.ct.common.bean.DataOut;

import java.io.*;

public class LocalFileOutput implements DataOut {

    OutputStreamWriter writer = null;

    public LocalFileOutput(String path) throws IOException {
        setPath(path);
    }

    /**
     * 设置输出路径的同时创建一个写入的方法
     * @param path
     */
    @Override
    public void setPath(String path) {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String log) {
        try {
            writer.write(log + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        assert writer != null;
        writer.flush();
        writer.close();
    }
}
