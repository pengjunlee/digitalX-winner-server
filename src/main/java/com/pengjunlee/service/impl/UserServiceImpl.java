package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.UserService;
import com.pengjunlee.service.mapper.UserMapper;
import com.pengjunlee.utils.PageUtil;
import com.pengjunlee.utils.TmallUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:19
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity getByName(String name) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getName, name);
        return getOne(queryWrapper);
    }

    @Override
    public UserAuthInfo getUserAuthByName(String name) {
        UserEntity userEntity = getByName(name);
        UserAuthInfo userAuth = null;
        if (userEntity != null) {
            userAuth = new UserAuthInfo(userEntity);
        }
        return userAuth;
    }

    @Override
    public boolean updateByName(UserEntity userEntity) {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", userEntity.getName());
        userEntity.setName(null);
        return update(userEntity, updateWrapper);
    }

    @Override
    public PageUtil pageUserByCond(Map<String, Object> map) {
        Integer offset = TmallUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<UserEntity> users = userMapper.pageUserByCond(map);
        int count = userMapper.countUserByCond(map);
        return new PageUtil(users, count);
    }

}
