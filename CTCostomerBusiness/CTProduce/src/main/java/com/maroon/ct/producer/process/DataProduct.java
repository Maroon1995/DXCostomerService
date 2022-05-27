package com.maroon.ct.producer.process;

import com.maroon.ct.producer.bean.Contact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataProduct {

    /**
     * 从列表中随机抽样N个索引不重复的元素
     *
     * @param paramList ：列表
     * @param count     ： 抽取的元素个数
     * @return ： 返回一个N个随机样本的列表。
     */
    public <T extends Contact> List<T> getRandomList(List<T> paramList, int count) {
        if (paramList.size() < count) {
            return paramList;
        }
        Random random = new Random();
        ArrayList<Integer> indexList = new ArrayList<>();
        ArrayList<T> sampleList = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < count; i++) {
            temp = random.nextInt(paramList.size()); // 随机生成索引
            if (!indexList.contains(temp)) { // 若列表索引不再indexList中
                indexList.add(temp); // 列表索引
                sampleList.add(paramList.get(temp)); // 并获取当前索引对应的值
            } else {
                i--; //从新生成索引
            }
        }
        return sampleList;
    }

    public String randomDate(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // 将传入的日期字符串解析成想要的日期格式
            Date start = simpleDateFormat.parse(startDate);
            Date end = simpleDateFormat.parse(endDate);
            if (start.getTime() > end.getTime()) {
                System.out.println("传入的开始大于结束日期，在这不合适");
                return null;
            }
            long resDate = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            return resDate + "";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
