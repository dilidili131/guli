package com.lch.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lch
 * @time: 2022/6/2 22:46
 */

public class TeseEasyExcel {
    public static void main(String[] args) {
        //设置文件地址和文件名称
        String fileName = "D:\\write.xlsx";
        //
        //EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("学生"+i);
            list.add(data);
        }
        return list;
    }
}
