package com.atguigu.servicebase.config.exceptionhandler;

import com.atguigu.servicebase.config.exception.GuliException;
import com.atguigu.util.GuiguResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wj
 * @create 2021/5/23  11:20
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GuiguResult error(Exception e){
        e.printStackTrace();
        return GuiguResult.error().message(e.getMessage()+"全局异常处理器");

    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public GuiguResult errorGuli(GuliException e){

        log.error(e.getMsg());
        e.printStackTrace();
        return GuiguResult.error().code(e.getCode()).message(e.getMsg());

    }
}
