package com.pengjunlee.domain;


import java.io.Serializable;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:42
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(T data) {
        super();
        this.data = data;
    }

    public BaseResponse(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
