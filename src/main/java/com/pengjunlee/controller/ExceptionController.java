package com.pengjunlee.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
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

    /**
     * 自定义状态码：
     * 0 表示成功获取到响应；
     * -1 表示通用获取响应失败；
     * -2 表示由于鉴权引起的响应失败；
     */

    // 捕捉shiro的异常
    @ExceptionHandler({ShiroException.class, JWTVerificationException.class})
    public Object handleShiroException(ShiroException e, HttpServletResponse response) {
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
        ret.setMessage(ex.getMessage());
        return ret;
    }
}
