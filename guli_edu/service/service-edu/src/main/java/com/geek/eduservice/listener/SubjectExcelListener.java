package com.geek.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.geek.eduservice.entity.excel.SubjectData;

/**
 * @ClassName SubjectExcelListner
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/7 10:25
 * @Version 1.0
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //每读取一行执行一次方法
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }


    //读取结束执行方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
