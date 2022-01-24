package com.geek.eduservice.entity.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TopCategory
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/10 18:46
 * @Version 1.0
 **/
@Data
public class TopCategory {

    private String id;

    private String title;

    private List<SecondCategory> children = new ArrayList<>();
}
