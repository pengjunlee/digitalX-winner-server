package com.pengjunlee.domain;

import lombok.Data;

/**
 * 验证码类
 *
 * @author pengjunlee
 * @create 2019-11-04 11:09
 */
@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;

    private String baseImg;

}

