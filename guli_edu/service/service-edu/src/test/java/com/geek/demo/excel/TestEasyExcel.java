package com.geek.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestEasyExcel
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/6 20:17
 * @Version 1.0
 **/
public class TestEasyExcel {
    @Test
    public void test(){
        //实现excel写的操作
        String filename = "D:\\tmp\\excel\\write.xlsx";

        List<DemoData> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData(i, "lucy" + i);
            list.add(demoData);
        }
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(list);
    }


    @Test
    public void readTest(){
        String filename = "D:\\tmp\\excel\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
