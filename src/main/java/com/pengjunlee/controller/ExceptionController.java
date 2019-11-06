package com.pengjunlee.controller;

import com.pengjunlee.domain.BaseResponse;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author pengjunlee
 * @create 2019-09-03 10:49
 */
@RestControllerAdvice

public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Object handleShiroException(ShiroException e, HttpServletResponse response) {
        response.setStatus(401);
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setCode(-2);
        ret.setMessage(e.getMessage());
        return ret;
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest request, Throwable ex) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setCode(-1);
        ret.setMessage("请求失败："+ex.getMessage());
        return ret;
    }
}
