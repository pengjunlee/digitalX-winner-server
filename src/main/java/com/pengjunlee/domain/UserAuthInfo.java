package com.pengjunlee.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用于返回给前端使用的用户信息封装类
 *
 * @author pengjunlee
 * @create 2019-09-03 14:44
 */
@Data
public class UserAuthInfo {

    private Long id;
    private String name;

    private String nickName;

    private String[] roles;

    private String introduction; // 简介

    private String avatar; // 头像

    public UserAuthInfo() {
        super();
    }

    public UserAuthInfo(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.nickName = userEntity.getNickName();
        this.roles = userEntity.getRoles().split(",");
        this.introduction = userEntity.getIntroduction();
        this.avatar = userEntity.getAvatar();
    }
}
