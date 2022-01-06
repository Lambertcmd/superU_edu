package com.geek.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/6 20:33
 * @Version 1.0
 **/
public class ExcelListener extends AnalysisEventListener<DemoData> {

    //一行一行读取excel内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("*****" + demoData);
    }

    //读取表头的方法
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    //读取完成之后执行方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
