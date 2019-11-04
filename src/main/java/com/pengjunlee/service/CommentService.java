package com.pengjunlee.service;

import com.pengjunlee.domain.CommentGoodsEntity;
import com.pengjunlee.domain.CommentEntity;
import com.pengjunlee.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:18
 */
public interface CommentService {

    PageUtil pageCommentGoods(int page);



    List<CommentEntity> listCommentByGoods(String goodsId);

    PageUtil pageCommentByGoods(String goodsId, int page);
}
