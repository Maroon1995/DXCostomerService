package com.maroon.ct.producer.io;

import com.maroon.ct.common.bean.DataOut;

import java.io.IOException;

public class LocalFileOutput implements DataOut {
    public LocalFileOutput(String path) throws IOException {
        setPath(path);
    }

    @Override
    public void setPath(String path) {

    }

    @Override
    public void close() throws IOException {

    }
}
