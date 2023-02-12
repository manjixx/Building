package com.eva.backend.exception;

import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author：
 * Date：2023/1/1816:28
 * Desc:
 */
@ControllerAdvice
@ResponseBody
public class DefaultException {
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        String message = null;

        if(e instanceof IllegalArgumentException){
            message = "传入参数错误";
        }

        if(e instanceof MethodArgumentNotValidException){
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }

        if(e instanceof UnauthorizedException){
            message = "权限认证失败";
        }

        return ResultFactory.buildFailResult(message);
    }
}
