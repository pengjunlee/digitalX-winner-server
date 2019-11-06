package com.pengjunlee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjunlee.domain.CommentGoodsEntity;
import com.pengjunlee.domain.CommentEntity;
import com.pengjunlee.domain.TmallShopEntity;
import com.pengjunlee.utils.PageUtil;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface CommentService extends IService<CommentGoodsEntity> {

    PageUtil pageCommentGoodsByCond(Map<String, Object> params);

    List<CommentEntity> listCommentByGoods(Long goodsId);

    PageUtil pageCommentByGoods(Map<String, Object> params);
}
