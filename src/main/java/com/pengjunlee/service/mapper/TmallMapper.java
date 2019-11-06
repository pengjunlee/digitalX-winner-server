package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.TmallShopEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface TmallMapper extends BaseMapper<TmallShopEntity> {

    List<TmallShopEntity> pageShopByCond(Map<String, Object> map);

    int countShopByCond(Map<String, Object> map);


}
