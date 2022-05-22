package com.maroon.ct.common.bean;


import java.io.Closeable;
import java.io.IOException;

/**
 * 输出接口:要有两个方法 设置输出路径 、输出数据
 */
public interface DataOut extends Closeable {

    public void setPath(String path);

    public void write(String log);


}
