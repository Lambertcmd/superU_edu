package com.geek.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.eduservice.client.UcenterClient;
import com.geek.eduservice.entity.EduComment;
import com.geek.eduservice.mapper.EduCommentMapper;
import com.geek.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void saveComment(EduComment comment, String memberId) {
        //根据用户id远程调用用户模块查询用户信息
        CommentMemberInfo commentMemberInfo = Optional.ofNullable(ucenterClient.getInfoById(memberId))
                .orElseThrow(() -> new GuliException(20001, "获取用户信息出错"));
        //补充评论对象的字段
        comment.setMemberId(memberId);
        BeanUtils.copyProperties(commentMemberInfo, comment);
        //提交保存评论
        int insert = baseMapper.insert(comment);
        if (insert < 1){
            throw new GuliException(20001, "评论插入出错");
        }
    }

    @Override
    public Map<String, Object> pageQuery(Page<EduComment> pageComment,String courseId) {

        //分页所有数据
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        Page<EduComment> commentPage = baseMapper.selectPage(pageComment, queryWrapper);
        //将分页的数据封装到map中
        Map<String, Object> map = new HashMap<>();

        long current = commentPage.getCurrent();
        long total = commentPage.getTotal();
        long size = commentPage.getSize();
        List<EduComment> records = commentPage.getRecords();
        long pages = commentPage.getPages();
        boolean hasNext = commentPage.hasNext();
        boolean hasPrevious = commentPage.hasPrevious();

        map.put("current",current);
        map.put("total",total);
        map.put("size",size);
        map.put("records",records);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);

        return map;
    }
}
