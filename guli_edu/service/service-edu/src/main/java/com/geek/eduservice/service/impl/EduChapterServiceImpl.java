package com.geek.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.eduservice.entity.EduChapter;
import com.geek.eduservice.entity.EduVideo;
import com.geek.eduservice.entity.chapter.ChapterVo;
import com.geek.eduservice.entity.chapter.VideoVo;
import com.geek.eduservice.mapper.EduChapterMapper;
import com.geek.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.eduservice.service.EduVideoService;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        //2.根据课程id查询所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);

        List<ChapterVo> finalList = new ArrayList<>();
        //3.遍历所有章节list集合进行封装
        chapterList.forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //4.遍历所有小节list集合进行封装
            List<VideoVo> videoVoList = new ArrayList<>();
            videoList.forEach(video -> {
                if(StringUtils.equals(video.getChapterId(), chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            });
            chapterVo.setChildren(videoVoList);
            finalList.add(chapterVo);
        });
        return finalList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        //根据chapterId查询小节表，如果该章节有小节，不进行删除
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        long count = videoService.count(queryWrapper);//查出的记录数
        if (count > 0) {//查询出小节 不进行删除
            throw new GuliException(20001, "该章节下存在小节，请先删除小节");
        }else{//该章节下没有小节，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }


    }
}
