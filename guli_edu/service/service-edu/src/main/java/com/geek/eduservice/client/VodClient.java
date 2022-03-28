package com.geek.eduservice.client;

import com.geek.commonutils.R;
import com.geek.eduservice.entity.EduVideo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName VodClient
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/21 12:40
 * @Version 1.0
 **/
@Component
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    /**
     * 根据云端视频id删除云端视频
     *
     * @param id
     * @return
     */
    @DeleteMapping("/eduvod/video/removeVideoSourceById/{id}")
    R removeVideoSourceById(@PathVariable("id") String id);

    /**
     * 根据云端视频id集合批量删除云端视频
     *
     * @param videoSourceIdList
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteBatchVideo")
    R deleteBatchVideo(@RequestParam("videoSourceIdList") List<String> videoSourceIdList);
}
