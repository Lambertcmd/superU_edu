package com.geek.eduservice.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.commonutils.jwt.JwtUtils;
import com.geek.commonutils.result.R;
import com.geek.eduservice.client.UcenterClient;
import com.geek.eduservice.entity.EduComment;
import com.geek.eduservice.service.EduCommentService;
import com.geek.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "评论功能")
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @ApiOperation("添加课程评论")
    @PostMapping("/saveComment")
    public R saveComment(@RequestBody EduComment comment, HttpServletRequest request) {
        //判断评论对象是否为空
        Optional.ofNullable(comment).orElseThrow(() -> new GuliException(20001, "评论对象为空"));
        //通过token得到用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isBlank(memberId)) {
            return R.error().code(28004).message("请先登录！");
        }
        commentService.saveComment(comment, memberId);
        return R.ok();
    }

    @ApiOperation("分页查询课程评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页"),
            @ApiImplicitParam(name = "limit",value = "每页大小"),
            @ApiImplicitParam(name = "courseId",value = "课程id")
    })
    @PostMapping("pageCommentList/{page}/{limit}/{courseId}")
    public R pageCommentList(@PathVariable("page") long page, @PathVariable("limit") long limit,
                             @PathVariable("courseId") String courseId) {
        Page<EduComment> pageComment = new Page<>(page, limit);
        Map<String,Object> map = commentService.pageQuery(pageComment,courseId);
        return R.ok().data(map);
    }

}

