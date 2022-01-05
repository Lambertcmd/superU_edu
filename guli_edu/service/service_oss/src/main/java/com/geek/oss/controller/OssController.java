package com.geek.oss.controller;

import com.geek.commonutils.R;
import com.geek.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:56
 * @Version 1.0
 **/
@RestController
@Api(tags = "阿里云OSS文件上传")
@RequestMapping("/eduoss/file-oss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件到阿里云oss
     * @param file
     * @return
     */
    @ApiOperation("上传文件到阿里云OSS")
    @PostMapping
    public R uploadOssFile(@RequestPart("file") MultipartFile file){
        //获取上传的文件 MultipartFile并返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
