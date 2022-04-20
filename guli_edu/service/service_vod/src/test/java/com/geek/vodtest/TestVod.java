package com.geek.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.geek.vod.utils.InitVodClient;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName TestVod
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/17 21:07
 * @Version 1.0
 **/
public class TestVod {
    public static void main(String[] args){
//        //上传本地视频到阿里云
//        String accessKeyId = "LTAI5tPvpheNZzHZqu2Htgko";
//        String accessKeySecret = "T18RmuOTonkTn7oyzGoUCrgIqWx28v";
//        String title = "005cd1127ead1cf18473e86f840ad820 - upload by sdk";//上传之后的文件名称
//        String fileName = "C:\\Users\\24737\\Videos\\Captures\\005cd1127ead1cf18473e86f840ad820.mp4";//本地路径和名称
//
//        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
//        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
//        request.setPartSize(2 * 1024 * 1024L);
//        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
//        request.setTaskNum(1);
//
//        UploadVideoImpl uploader = new UploadVideoImpl();
//        UploadVideoResponse response = uploader.uploadVideo(request);
//        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
//        if (response.isSuccess()) {
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//        } else {
//            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//            System.out.print("ErrorCode=" + response.getCode() + "\n");
//            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
//        }
        getPlayAuth();

    }
    //根据视频id获取视频播放凭证
    public static void getPlayAuth(){

        DefaultAcsClient client = InitVodClient.initVodClient("LTAI5tPvpheNZzHZqu2Htgko", "T18RmuOTonkTn7oyzGoUCrgIqWx28v");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        }catch (ClientException e){
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws ClientException {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("aac115585913445a880164ee1508c879");
//        request.setAuthInfoTimeout(200L);//设置播放凭证的有效期 默认100s 取值范围：100~3000
        return client.getAcsResponse(request);
    }

    //1. 根据视频id获取视频地址
    public static void getPlayUrl(){

        //创建初始化对象
        DefaultAcsClient client = InitVodClient.initVodClient("LTAI5tPvpheNZzHZqu2Htgko", "T18RmuOTonkTn7oyzGoUCrgIqWx28v");
        //创建获取视频地址的request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("325ffecff9124b69a161ea0288c864d8");
        //调用初始化对象里面的方法传递request，获取数据
        try {
            response = client.getAcsResponse(request);

            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        }catch (ClientException e){
            e.printStackTrace();
        }
    }
}
