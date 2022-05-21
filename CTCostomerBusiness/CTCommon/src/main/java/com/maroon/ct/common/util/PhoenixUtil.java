package com.maroon.ct.common.util;

import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过phoenix操作hbase的工具类
 */
public class PhoenixUtil {

    private Connection conn;

    public PhoenixUtil(Connection conn) {
        this.conn = conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * 将Hbase中查询的结果返回成json对象串列表
     * @param sql 查询的sql语句
     * @return
     * @throws SQLException
     */
    public List<JSONObject> queryList(String sql) throws SQLException {
        // 创建数据库操作对象
        PreparedStatement ps = conn.prepareStatement(sql);
        // 执行sql语句
        ResultSet resultSet = ps.executeQuery();                    // 数据
        ResultSetMetaData resMetaData = resultSet.getMetaData();    // 数据元信息
        // 结果列表
        List<JSONObject> resListJson = new ArrayList<JSONObject>();
        while (resultSet.next()) {
            // 创建Json对象
            JSONObject jsonObject = new JSONObject();
            // 将结果封装到Json对象中
            for (int i = 0; i < resMetaData.getColumnCount(); i++) {
                jsonObject.put(resMetaData.getTableName(i), resultSet.getObject(i));
            }
            // 结果添加到结果列表中
            resListJson.add(jsonObject);
        }
        // 释放资源
        resultSet.close();
        ps.close();
        conn.close();

        return resListJson;
    }


}
