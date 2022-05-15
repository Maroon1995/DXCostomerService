package com.maroon.ct.common.constant;

import com.maroon.ct.common.bean.Value;


/**
 * 名称常量枚举类
 */
public enum Names implements Value {
    NAMESPACES("ct");
    private String name;

    private Names(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
        this.name = (String) value;
    }

    @Override
    public Object getValue() {
        return name;
    }
}
