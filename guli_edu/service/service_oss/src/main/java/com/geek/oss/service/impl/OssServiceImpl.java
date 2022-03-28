package com.geek.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.geek.oss.service.OssService;
import com.geek.oss.utils.ConstantOssPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:57
 * @Version 1.0
 **/
@Slf4j
@Service
public class OssServiceImpl implements OssService {


    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantOssPropertiesUtil.END_POINT;
        String accessKeyId = ConstantOssPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantOssPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantOssPropertiesUtil.BUCKET_NAME;
        log.info("endpoint:"+endpoint);

        try {
            // 1.创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 2.获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String filename = file.getOriginalFilename();

            // 给文件名称添加一个随机的名称，使每个文件名都不相同
            String uuid = UUID.randomUUID().toString().replaceAll("-","");

            filename =  uuid + filename;
            //把文件按照日期进行分类 (2022/01/06/01.jpg)
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + filename;

            // 3.调用oss方法实现上传 put(buckteName,上传到oss的文件路径和名称(aa/bb/ab.jpg),上传文件输入流)
            ossClient.putObject(bucketName, filename, inputStream);

            // 4.关闭OSSClient。
            ossClient.shutdown();

            // 把上传到OSS的文件路径返回(需要手动拼接路径)
            //https://guli-file--upload.oss-cn-shenzhen.aliyuncs.com/243824a061a29b4bff8bbe011207bbe1_1.jpg
            String url = "https://"+ bucketName + "." + endpoint + "/" + filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
