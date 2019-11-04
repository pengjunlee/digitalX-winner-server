package com.pengjunlee.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author pengjunlee
 * @create 2019-09-03 14:48
 */

@Data
@TableName("tbl_tmall_shop")//@TableName中的值对应着表名
public class TmallShopEntity extends BaseDomain {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String url;

    private Integer goodsCount;
    private Integer presaleGoodsCount;
    private Long presaleBillTotal;

}
