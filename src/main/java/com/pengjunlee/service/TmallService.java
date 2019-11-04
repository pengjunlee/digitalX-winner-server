package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.TmallShopEntity;
import com.pengjunlee.utils.PageUtil;

import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface TmallService extends IService<TmallShopEntity> {

    PageUtil pageShopByCond(Map<String, Object> map);

    PageUtil pageGoodsByCond(Map<String, Object> map);

    boolean updateShopById(Long shopId);
}
