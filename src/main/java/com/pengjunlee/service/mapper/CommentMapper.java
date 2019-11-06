package com.pengjunlee.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjunlee.domain.CommentGoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentGoodsEntity> {

    List<CommentGoodsEntity> pageCommentGoods(Map<String, Object> map);

    int countCommentGoods(Map<String, Object> map);


}
