package com.geek.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName GuliException
 * @Description 自定义异常处理
 * @Author Lambert
 * @Date 2021/12/28 20:21
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;//状态码

    private String msg;//异常信息


}
