package com.geek.eduservice.controller;


import com.geek.commonutils.R;
import com.geek.eduservice.entity.EduChapter;
import com.geek.eduservice.entity.chapter.ChapterVo;
import com.geek.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@RestController
@Api(tags = "章节管理")
@CrossOrigin
@Slf4j
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;


    /**
     * 根据课程id查询课程大纲
     * @param courseId
     * @return
     */
    @ApiOperation("根据课程id查询课程大纲")
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable("courseId") String courseId){
        List<ChapterVo> list =  chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("chapterVideoList",list);
    }

    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }

    @ApiOperation("根据章节id查询章节")
    @GetMapping("/getChapterInfoById/{chapterId}")
    public R getChapterInfoById(@PathVariable("chapterId") String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){
        log.info("chapter"+chapter.toString());
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable("chapterId") String chapterId){
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }


}

