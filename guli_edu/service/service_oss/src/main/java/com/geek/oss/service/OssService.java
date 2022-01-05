package com.geek.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssService
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:56
 * @Version 1.0
 **/

public interface OssService {
    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
