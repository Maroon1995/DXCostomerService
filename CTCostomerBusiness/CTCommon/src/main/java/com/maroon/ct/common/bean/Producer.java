package com.maroon.ct.common.bean;

import java.io.Closeable;

/**
 * 生产者接口，其中要包含三个方法，输入、生产、输出
 */
public interface Producer extends Closeable {

    public void setInput(DataIn in);

    public void setOutput(DataOut out);

    /*无限生产数据*/
    public void produce(String rate);

    /*有限生产数据*/
    public void produce(String rate, String limitnum);


}
