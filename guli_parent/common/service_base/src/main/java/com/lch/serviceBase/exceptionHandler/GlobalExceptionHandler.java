package com.lch.serviceBase.exceptionHandler;

import com.lch.commonUtils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: lch
 * @time: 2022/5/13 17:29
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("全局异常处理");
    }

    //特定异常处理（更改ExceptionHandler）
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error2(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("ArithmeticException异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(GuLiException.class)
    @ResponseBody
    public Result error2(GuLiException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
