package com.pengjunlee.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pengjunlee
 * @create 2019-09-03 14:48
 */
public class TmallGoodsEntity implements Serializable {

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

    private static final long serialVersionUID = 1L;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotalSaleCount() {
        return totalSaleCount;
    }

    public void setTotalSaleCount(Integer totalSaleCount) {
        this.totalSaleCount = totalSaleCount;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMonthSaleCount() {
        return monthSaleCount;
    }

    public void setMonthSaleCount(String monthSaleCount) {
        this.monthSaleCount = monthSaleCount;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Boolean getPreSale() {
        return preSale;
    }

    public void setPreSale(Boolean preSale) {
        this.preSale = preSale;
    }

    public Integer getPreSaleCash() {
        return preSaleCash;
    }

    public void setPreSaleCash(Integer preSaleCash) {
        this.preSaleCash = preSaleCash;
    }

    public Integer getPreSaleDiscount() {
        return preSaleDiscount;
    }

    public void setPreSaleDiscount(Integer preSaleDiscount) {
        this.preSaleDiscount = preSaleDiscount;
    }

    public Integer getPreSaleCount() {
        return preSaleCount;
    }

    public void setPreSaleCount(Integer preSaleCount) {
        this.preSaleCount = preSaleCount;
    }

    public Integer getPreSaleTotal() {
        return preSaleTotal;
    }

    public void setPreSaleTotal(Integer preSaleTotal) {
        this.preSaleTotal = preSaleTotal;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
