package com.geek.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/1 18:15
 * @Version 1.0
 **/
@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
