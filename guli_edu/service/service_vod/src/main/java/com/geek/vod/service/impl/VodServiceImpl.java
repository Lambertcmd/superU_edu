package com.geek.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.geek.commonutils.result.R;
import com.geek.servicebase.exception.GuliException;
import com.geek.vod.service.VodService;
import com.geek.vod.utils.ConstantVodPropertiesUtil;
import com.geek.vod.utils.InitVodClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName VodServiceImpl
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/19 1:00
 * @Version 1.0
 **/
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        //accessKeyId, accessKeySecret
        String accessKeyId = ConstantVodPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodPropertiesUtil.ACCESS_KEY_SECRET;
        //fileName:上传文件原始名称
        String fileName = file.getOriginalFilename();
        //title:上传到阿里云后的视频名称
        //从最后一个.开始往前面截取内容 (01.mp4只截取01)
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        //inputStream 上传文件输入流
        InputStream inputStream = null;
        //最终上传的视频id
        String videoId = null;
        try {
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

            if (response.isSuccess()) {
                videoId = response.getVideoId();
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;
    }

    @Override
    public R removeVideoById(String id) {
        String accessKeyId = ConstantVodPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodPropertiesUtil.ACCESS_KEY_SECRET;
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);

            DeleteVideoRequest request = new DeleteVideoRequest();
            DeleteVideoResponse response = new DeleteVideoResponse();
            //设置需要删除的视频id
            request.setVideoIds(id);
            response = client.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }

    }

    @Override
    public R deleteBatchVideo(List<String> videoSourceIdList) {
        String accessKeyId = ConstantVodPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodPropertiesUtil.ACCESS_KEY_SECRET;
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);

            DeleteVideoRequest request = new DeleteVideoRequest();
            DeleteVideoResponse response = new DeleteVideoResponse();

            //将videoIdList转换成1，2，3 (遍历集合内的方法，每个元素用,隔开)
            String videoIds = StringUtils.join(videoSourceIdList, ",");
            log.info("videoIds"+videoIds);
            //设置需要删除的视频id
            request.setVideoIds(videoIds);
            response = client.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "批量删除视频失败");
        }
    }

    @Override
    public String getPlayAuth(String videoId) throws ClientException {
        //获取阿里云存储相关常量
        String accessKeyId = ConstantVodPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodPropertiesUtil.ACCESS_KEY_SECRET;
        //初始化
        DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);
        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        //获得播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return playAuth;
    }

}
