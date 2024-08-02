package com.bookmall.Controller;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.CusException.AutoUpdateTimeException;
import com.bookmall.CusException.IndentifiedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(AutoUpdateTimeException.class)
    @ResponseBody
    public Result bookExpHandler(AutoUpdateTimeException a){
        log.info("全局时间字段 自动填充失败");
        return new Result(Code.UPDATE_TIME_EXCEPTION, Message.UPDATE_TIME_EXCEPTION);
    }

    @ExceptionHandler(IndentifiedException.class)
    @ResponseBody
    public Result indentifiedExpHandler(IndentifiedException i){
        log.info("微信HTTP请求失败");
        return new Result(Code.USER_LOGIN_EXCEPTION,Message.USER_LOGIN_EXCEPTION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result unknownException(Exception e){
        log.info("未知错误   " + e.getMessage());
        return new Result(9999,"UNKNOWN_ERROE");
    }



}
