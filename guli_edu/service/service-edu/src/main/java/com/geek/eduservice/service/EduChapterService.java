package com.geek.eduservice.service;

import com.geek.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程id查询章节和小节
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);
}
