package com.geek.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName SubjectData
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/7 10:21
 * @Version 1.0
 **/
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
