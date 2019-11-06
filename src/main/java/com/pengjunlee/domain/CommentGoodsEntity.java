package com.pengjunlee.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author pengjunlee
 * @create 2019-09-03 14:48
 */
@Data
@TableName("tbl_tmall_goods")//@TableName中的值对应着表名
public class CommentGoodsEntity extends BaseDomain {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String shop;

    private String imgUrl;

    private String goodsNum;
}
