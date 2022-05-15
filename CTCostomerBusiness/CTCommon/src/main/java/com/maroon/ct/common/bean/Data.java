package com.maroon.ct.common.bean;


/**
 * 要输入的数据对象
 */

public class Data implements Value{

    public String content;

    @Override
    public void setValue(Object value) {
        content = (String) value;
    }

    @Override
    public Object getValue() {

        return this.content;
    }
}
