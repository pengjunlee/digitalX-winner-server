package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {


    List<UserEntity> pageUserByCond(Map<String, Object> map);

    int countUserByCond(Map<String, Object> map);

}
