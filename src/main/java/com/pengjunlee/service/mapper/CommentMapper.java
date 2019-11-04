package com.pengjunlee.service.mapper;

import com.pengjunlee.domain.CommentGoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pengjunlee
 * @create 2019-09-03 17:13
 */
@Mapper
public interface CommentMapper {

    List<CommentGoodsEntity> pageCommentGoods(@Param("offset") Integer offset, @Param("limit") Integer limit);

    int countCommentGoods();


}
