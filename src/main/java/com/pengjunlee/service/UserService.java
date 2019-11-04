package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface UserService extends IService<UserEntity> {

    // T getById(Serializable id);

    // boolean updateById(T entity);

    // boolean save(T entity);


    // 根据用户名查询用户
    UserEntity getByName(String name);

    UserAuthInfo getUserAuthByName(String name);

    boolean updateByName(UserEntity userEntity);

    PageUtil pageUserByCond(Map<String, Object> map);
}
