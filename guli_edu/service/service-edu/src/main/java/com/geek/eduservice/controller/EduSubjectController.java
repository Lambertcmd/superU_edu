package com.geek.eduservice.controller;


import com.geek.commonutils.R;
import com.geek.eduservice.entity.EduSubject;
import com.geek.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Api(tags = "课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("添加课程")
    @PostMapping("/addSubject")
    public R addSubject(@RequestPart("file") MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

}

