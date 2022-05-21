package com.maroon.ct.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties load(String propertyName) throws IOException {

        // 创建一个Properties对象
        Properties prop = new Properties();
        // 配置文件路径
        InputStreamReader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyName),
                StandardCharsets.UTF_8);
        // 加载配置文件
        prop.load(reader);
        return prop;
    }

}
