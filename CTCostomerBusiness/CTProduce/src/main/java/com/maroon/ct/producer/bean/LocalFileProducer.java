package com.maroon.ct.producer.bean;

import com.maroon.ct.common.bean.DataIn;
import com.maroon.ct.common.bean.DataOut;
import com.maroon.ct.common.bean.Producer;

import java.io.IOException;
import java.util.ArrayList;
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
            List randomList = getRandomList(contacts, 2); //随机抽取两个不同的电话号码
            System.out.println(randomList.get(0));
//            while (flag) {
//            // 从通讯录中随机查找2个电话号码作为主叫和被叫
//                List randomList = getRandomList(contacts, 2); //随机抽取两个不同的电话号码
//
//                String maincall = randomList.get(0);
//                String maincall_name;
//                String bycall;
//                String bycall_name;
//                String data_time;
//                String duration;
//
//            // 生成随机的通话时间
//
//            // 生成随机的通话时长
//
//            // 生成通话记录
//
//            // 将通话记录写到数据文件中
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }

    /**
     * 从列表中随机抽样N个索引不重复的元素
     * @param paramList ：列表
     * @param count ： 抽取的元素个数
     * @return ： 返回一个N个随机样本的列表。
     */
    public static List getRandomList(List paramList, int count) {
        if (paramList.size() < count) {
            return paramList;
        }
        Random random = new Random();
        ArrayList<Integer> indexList = new ArrayList<>();
        ArrayList<Object> sampleList = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < count; i++) {
            temp = random.nextInt(paramList.size()); // 随机生成索引
            System.out.println(temp);
            if (!indexList.contains(temp)) { // 若列表索引不再indexList中
                indexList.add(temp); // 列表索引
                sampleList.add(paramList.get(temp)); // 并获取当前索引对应的值
            }
            else {
                i--; //从新生成索引
            }
        }
        return sampleList;
    }

    public static void main(String[] args) {
        ArrayList<Object> testlist = new ArrayList<>();
        testlist.add("124");
        testlist.add("345");
        testlist.add("2345");
        testlist.add("23456");
        List randomList = getRandomList(testlist, 2);
        System.out.println(randomList.toString());
    }
}
