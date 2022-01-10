package com.geek.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geek.eduservice.entity.EduSubject;
import com.geek.eduservice.entity.excel.SubjectData;
import com.geek.eduservice.service.EduSubjectService;
import com.geek.servicebase.exception.GuliException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SubjectExcelListner
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/7 10:25
 * @Version 1.0
 **/
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //由于SubjectExcelListener不能交给Spring进行管理的，不能注入其他对象
    //不能通过Mapper来实现数据库操作，但是可以手动new一个listener并传入service
    public EduSubjectService subjectService;

    //每读取一行执行一次方法
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //判断一级分类是否重复
        String oneSubjectName = subjectData.getOneSubjectName();
        EduSubject existOneSubject = existOneSubject(oneSubjectName);
        //表里没有相同的一级分类,添加一级分类
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(oneSubjectName);
            subjectService.save(existOneSubject);
        }
        else {
            log.info("一级分类" + oneSubjectName + "已存在");
        }
        //判断二级分类是否重复
        String twoSubjectName = subjectData.getTwoSubjectName();
        String parentId = existOneSubject.getId();
        EduSubject existTwoSubject = existTwoSubject(twoSubjectName, parentId);
        //表里没有相同的二级分类
        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(twoSubjectName);
            subjectService.save(existTwoSubject);
        }else {
            log.info("二级分类" + twoSubjectName + "已存在");
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name)
                .eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断一级分类不能重复添加
    private EduSubject existTwoSubject(String name,String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name)
                .eq("parent_id", parentId);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    //读取结束执行方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
