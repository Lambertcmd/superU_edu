package com.geek.eduservice.controller;


import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.dto.TopCategoryDTO;
import com.geek.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Api(tags = "课程分类管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
     * 添加课程
     * @param file
     * @return
     */
    @ApiOperation("添加课程分类")
    @PostMapping("/addSubject")
    public R addSubject(@RequestPart("file") MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    /**
     * 树形课程分类列表
     * @return
     */
    @ApiOperation("课程分类列表")
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        List<TopCategoryDTO> list = subjectService.getAllSubject();
        return R.ok().data("list",list);
    }

}

