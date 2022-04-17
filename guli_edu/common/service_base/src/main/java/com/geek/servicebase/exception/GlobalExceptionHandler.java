package com.geek.servicebase.exception;

import com.geek.commonutils.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一异常处理
 * @Author Lambert
 * @Date 2021/12/28 19:56
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)//指定出现某个异常时执行这个方法
    @ResponseBody //为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }

    /**
     * 特定异常(特定异常执行优先级高于全局异常)
     */
    @ExceptionHandler(ArithmeticException.class)//指定出现某个异常时执行这个方法
    @ResponseBody //为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理...");
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(GuliException.class)//指定出现某个异常时执行这个方法
    @ResponseBody //为了能够返回数据
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());

    }

}
