package com.maroon.ct.producer.io;

import com.maroon.ct.common.bean.Data;
import com.maroon.ct.common.bean.DataIn;

import java.io.IOException;
import java.util.List;

public class HbaseFileInput implements DataIn {


    @Override
    public void setPath(String path) {

    }

    @Override
    public Object read() throws IOException {
        return null;
    }

    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
