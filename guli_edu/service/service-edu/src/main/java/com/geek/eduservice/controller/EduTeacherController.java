package com.geek.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.R;
import com.geek.eduservice.entity.EduTeacher;
import com.geek.eduservice.entity.vo.TeacherQuery;
import com.geek.eduservice.service.EduTeacherService;
import com.geek.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2021-12-27
 */
@Api(tags = "讲师管理")
@Slf4j
@CrossOrigin //跨域 浏览器从一个域名的网页去请求另一个域名的资源时，域名、端口、协议任一不同，都是跨域 。前后端分离开发中，需要考虑ajax跨域的问题,这里我们可以从服务端解决这个问题
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询讲师表所有数据
     * @return
     */
    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> teacherList = teacherService.list(null);
        return R.ok().data("teacherList",teacherList);
    }

    /**
     * 逻辑删除讲师
     * @param id
     * @return
     */
    @ApiOperation("根据id逻辑删除讲师")
    @ApiImplicitParam(name = "id",value = "讲师id")
    @DeleteMapping("/{id}")
    public R removeTeacherById(@PathVariable("id") String id){
        return R.error();
//        boolean flag = teacherService.removeById(id);
//        return flag ? R.ok() : R.error();
    }

//    /**
//     * 分页查询所有讲师
//     * @param page      当前页
//     * @param size      每页大小
//     * @return
//     */
//    @ApiOperation("根据页码和页大小分页查询讲师")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page",value = "当前页"),
//            @ApiImplicitParam(name = "size",value = "每页大小")
//    })
//    @GetMapping("/pageTeacher/{page}/{size}")
//    public R pageListTeacher(@PathVariable("page") long page,
//                             @PathVariable("size") long size){
//        Page<EduTeacher> pageInit = new Page<>(page,size);
////        int i = 10/0;
////        try {
////            int i = 10/0;
////        }catch (Exception e){
////            throw new GuliException(20001,"执行了自定义异常处理");
////        }
//
//        Page<EduTeacher> teacherPage = teacherService.page(pageInit);
//        return R.ok().data("total",teacherPage.getTotal()).data("rows",teacherPage.getRecords());
//    }

    /**
     * 分页复杂查询讲师
     * @param page       当前页
     * @param size          每页大小
     * @param teacherQuery  接收前端传来的查询条件
     * @return
     */
    @PostMapping("/pageTeacherCondition/{page}/{size}")
    @ApiOperation("分页复杂查询讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页"),
            @ApiImplicitParam(name = "size",value = "每页大小")
    })
    public R pageTeacherCondition(@PathVariable("page") long page, @PathVariable("size") long size,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        log.info("分页复杂查询讲师");
        //创建page
        Page<EduTeacher> teacherPage = new Page<>(page,size);
        //构建查询条件
        teacherService.pageQuery(teacherPage, teacherQuery);
        //调用方法实现复杂查询分页
        long total = teacherPage.getTotal();//数据量
        List<EduTeacher> records = teacherPage.getRecords();//当前页的内容
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 添加讲师
     * @param eduTeacher
     * @return
     */
    @PostMapping("/addTeacher")
    @ApiOperation("添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        log.info("添加讲师,name:"+eduTeacher.getName());
        boolean save = teacherService.save(eduTeacher);
        return save ? R.ok() : R.error();
    }

    /**
     * 根据id查询讲师
     * @param id
     * @return
     */
    @ApiOperation("根据id查询讲师")
    @ApiImplicitParam(name = "id",value = "讲师id")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable("id") String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }


    /**
     * 修改讲师信息
     * @param eduTeacher
     * @return
     */
    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.updateById(eduTeacher);
        return result ? R.ok() : R.error();
    }



}

