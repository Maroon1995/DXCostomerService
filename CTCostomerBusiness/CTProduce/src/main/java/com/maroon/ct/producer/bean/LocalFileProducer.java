package com.maroon.ct.producer.bean;

import com.maroon.ct.common.bean.DataIn;
import com.maroon.ct.common.bean.DataOut;
import com.maroon.ct.common.bean.Producer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 输出到本地
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    private volatile boolean flag = true;


    @Override
    public void setInput(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOutput(DataOut out) {
        this.out = out;
    }

    @Override
    public void produce() {

        try {
            // 读取通讯录数据
            List<Contact> contacts = in.read(Contact.class);
            List<Contact> randomList = getRandomList(contacts, 2); //随机抽取两个不同的电话号码
            while (flag) {
                // 从通讯录中随机查找2个电话号码作为主叫和被叫
                randomList = getRandomList(contacts, 2); //随机抽取两个不同的电话号码

                String maincall = randomList.get(0).getTel();
                String maincall_name = randomList.get(0).getName();
                String bycall = randomList.get(1).getTel();
                String bycall_name = randomList.get(1).getName();

                // 生成随机的建立通话时间
                String data_time = randomDate("2021-01-01", "2022-01-01");

                // 生成随机的通话时长(30分钟内_0600)
                int duar = new Random().nextInt(60 * 30) + 1;
                String duration =  new DecimalFormat("0000").format(duar);
                // 生成通话记录
                CallRecords callRecords = new CallRecords(maincall, maincall_name, bycall, bycall_name, data_time, duration);
                System.out.println(callRecords.toString());
                // 将通话记录写到数据文件中或Hbase中

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }

    /**
     * 从列表中随机抽样N个索引不重复的元素
     *
     * @param paramList ：列表
     * @param count     ： 抽取的元素个数
     * @return ： 返回一个N个随机样本的列表。
     */
    public static <T extends Contact> List<T> getRandomList(List<T> paramList, int count) {
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

    private String randomDate(String startDate, String endDate) {
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
