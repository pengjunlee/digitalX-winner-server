package com.pengjunlee.domain;

import lombok.Data;

/**
 * @author pengjunlee
 * @create 2019-09-03 14:48
 */
@Data
public class TmallGoodsEntity {

    /*{
        "_id": 20159694203,
            "imgUrl": "//img.alicdn.com/bao/uploaded/i4/430490406/O1CN01BzX29B1ErzMxI791m_!!0-item_pic.jpg_180x180.jpg",
            "title": "全棉时代 产妇一次性内裤女士纯棉孕妇产后月子待产用品旅行 25条",
            "price": 96,
            "totalSaleCount": 631868,
            "rateCount": 40631,
            "enabled": true,
            "updateTime": 1571993200,
            "shopId": 1,
            "monthSaleCount": "2.5万+",
            "originalPrice": 194,
            "preSale": true,
            "preSaleCash": 10,
            "preSaleCount": 26135,
            "preSaleTotal": 2508960,
            "totalQuantity": 18423
    }*/

    private Long id;
    private String imgUrl;
    private String title;
    private Double price; // 当前价格
    private Integer totalSaleCount; // 总销量
    private Integer rateCount; // 评论数
    private Boolean enabled; // 是否下架
    private Long updateTime; // 更新时间，秒数
    private Long shopId; // 关联的店铺ID
    private String monthSaleCount; // 月销量
    private Double originalPrice; // 原价
    private Boolean preSale; // 是否是预售商品
    private Integer preSaleCash;// 预售商品字段：订金
    private Integer preSaleDiscount;// 预售商品字段：订金
    private Integer preSaleCount;// 预售商品字段：预售数量
    private Integer preSaleTotal;// 预售商品字段：预售总金额
    private Integer totalQuantity;// 库存
}
