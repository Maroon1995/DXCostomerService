package com.maroon.ct.producer.bean;

import com.maroon.ct.common.bean.Data;

public class Contact extends Data {

    private String tel;
    private String name;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 解析接收到的数据
     * @param value
     */
    @Override
    public void setValue(Object value) {
        String content = (String) value;
        String[] values = content.split("\t");
        setName(values[1]);
        setTel(values[0]);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
