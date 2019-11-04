package com.pengjunlee.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjunlee.domain.TmallGoodsEntity;
import com.pengjunlee.domain.TmallShopEntity;
import com.pengjunlee.service.TmallService;
import com.pengjunlee.service.mapper.TmallMapper;
import com.pengjunlee.utils.PageUtil;
import com.pengjunlee.utils.TmallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:19
 */
@Service("menuService")
public class TmallServiceImpl extends ServiceImpl<TmallMapper, TmallShopEntity> implements TmallService {

    @Resource
    private TmallMapper tmallMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PageUtil pageShopByCond(Map<String, Object> map) {
        Integer offset = TmallUtil.getOffsetFromParams(map);
        map.put("offset", offset);
        List<TmallShopEntity> shops = tmallMapper.pageShopByCond(map);
        int count = tmallMapper.countShopByCond(map);
        return new PageUtil(shops, count);
    }

    @Override
    public PageUtil pageGoodsByCond(Map<String, Object> map) {
        // 获取数据条数
        Query query = new Query(Criteria.where("shopId").is(new Integer(map.get("shopId").toString())));
        if (map.get("name") != null) {
            Pattern pattern = Pattern.compile("^.*" + map.get("name").toString().trim() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }
        if (map.get("preSale") != null) {
            boolean vv = map.get("preSale").toString().equals("1") ? true : false;
            query.addCriteria(Criteria.where("preSale").is(vv));
        }
        int count = mongoTemplate.find(query, TmallGoodsEntity.class, "tmallGoodsEntity").size();
        Integer offset = TmallUtil.getOffsetFromParams(map);
        String sort_by = map.get("sortBy") == null ? "saleCount" : map.get("sortBy").toString();
        String sort_order = map.get("sortOrder") == null ? "DESC" : map.get("sortOrder").toString();
        // 获取数据内容
        query.with(new Sort(Sort.Direction.valueOf(sort_order), sort_by));
        query.skip(offset);
        query.limit(new Integer(map.get("limit").toString()));
        List<TmallGoodsEntity> tmallGoodsEntities = mongoTemplate.find(query, TmallGoodsEntity.class, "tmallGoodsEntity");
        return new PageUtil(tmallGoodsEntities, count);
    }

    @Override
    public boolean updateShopById(Long shopId) {
        // 获取数据条数
        Query query = new Query(Criteria.where("shopId").is(shopId));
        query.addCriteria(Criteria.where("enabled").is(true));
        List<TmallGoodsEntity> tmallGoodsEntities = mongoTemplate.find(query, TmallGoodsEntity.class, "tmallGoodsEntity");
        Integer goodsCount = tmallGoodsEntities.size();
        Double presaleBillTotal = 0.0; //
        Integer presaleGoodsCount = 0;
        for (TmallGoodsEntity goods : tmallGoodsEntities) {
            if (goods.getPreSale()) {
                presaleBillTotal += goods.getPreSaleTotal();
                presaleGoodsCount += 1;
            }
        }
        long amount = new Double(presaleBillTotal / 10000).intValue();
        TmallShopEntity shopEntity = new TmallShopEntity();
        shopEntity.setGoodsCount(goodsCount);
        shopEntity.setPresaleGoodsCount(presaleGoodsCount);
        shopEntity.setPresaleBillTotal(amount);
        shopEntity.beforeUpdate();
        UpdateWrapper<TmallShopEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", shopId);
        return update(shopEntity, updateWrapper);
    }


}
