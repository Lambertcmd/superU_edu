package com.geek.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DemoData
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/6 20:15
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {

    //设置excel表头的名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
