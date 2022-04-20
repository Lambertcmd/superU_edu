package com.geek.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
public interface EduCommentService extends IService<EduComment> {

    /**
     * 添加课程评论
     * @param comment
     * @param memberId
     */
    void saveComment(EduComment comment, String memberId);

    /**
     * 分页查询课程评论
     * @param pageComment
     * @return
     */
    Map<String, Object> pageQuery(Page<EduComment> pageComment,String courseId);
}
