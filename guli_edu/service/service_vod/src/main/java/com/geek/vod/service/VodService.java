package com.geek.vod.service;

import com.aliyuncs.exceptions.ClientException;
import com.geek.commonutils.result.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据云端视频id删除云端视频
     * @param id
     * @return
     */
    R removeVideoById(String id);

    /**
     * 根据云端视频id集合批量删除云端视频
     * @param videoIdList
     * @return
     */
    R deleteBatchVideo(List<String> videoIdList);

    /**
     * 根据云端视频id获得播放凭证
     * @param videoId
     * @return
     */
    String getPlayAuth(String videoId) throws ClientException;
}
