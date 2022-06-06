package com.geek.eduservice.service;

import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除该课程的所有小节
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);

    R deleteVideo(String id);
}
