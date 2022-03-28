package com.geek.eduservice.client;

import com.geek.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName VodFileDegradeFeignClient
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/21 18:58
 * @Version 1.0
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeVideoSourceById(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatchVideo(List<String> videoSourceIdList) {
        return R.error().message("批量删除视频出错");
    }
}
