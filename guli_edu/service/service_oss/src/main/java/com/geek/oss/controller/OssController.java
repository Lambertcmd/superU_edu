package com.geek.oss.controller;

import com.geek.commonutils.result.R;
import com.geek.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:56
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@Slf4j
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
    @PostMapping("/upload")
    public R uploadOssFile(@RequestPart("file") MultipartFile file){
        log.info("用户上传头像");
        //获取上传的文件 MultipartFile并返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        log.info("头像上传成功！");
        return R.ok().data("url",url);
    }
}
