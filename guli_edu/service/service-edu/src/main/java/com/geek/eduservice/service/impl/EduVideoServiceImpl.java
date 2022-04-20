package com.geek.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.result.R;
import com.geek.eduservice.client.VodClient;
import com.geek.eduservice.entity.EduVideo;
import com.geek.eduservice.mapper.EduVideoMapper;
import com.geek.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    /**
     * 根据课程id删除小节
     * @param courseId
     */
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        //删除所有小节下的云端视频
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapper);
//        List<String> videoIdList = eduVideoList.stream().map(EduVideo::getVideoSourceId).collect(Collectors.toList());
        List<String> videoIdList = eduVideoList.stream()
                .map(EduVideo::getVideoSourceId)
                .filter(videoSourceId -> !StringUtils.isBlank(videoSourceId)).collect(Collectors.toList());

        if (videoIdList.size() > 0) {
            vodClient.deleteBatchVideo(videoIdList);
        }
        //删除所有小节
        baseMapper.delete(wrapper);
    }

    @Override
    public R deleteVideo(String id) {
        //根据视频id获取云端视频id
        String videoSourceId= baseMapper.selectById(id).getVideoSourceId();
        //判断视频是否为空
        if (!StringUtils.isBlank(videoSourceId)) {
            //根据云端视频id删除云端视频
            R result = vodClient.removeVideoSourceById(videoSourceId);
            if (result.getCode() == 20001){
                throw new GuliException(20001, "删除视频失败，启动熔断器");
            }
        }
        //根据视频id删除小节
        int result = baseMapper.deleteById(id);

        //是否删除成功
        if (result > 0) {
            return R.ok();
        }else {
            throw new GuliException(20001,"视频删除失败");
        }
    }
}
