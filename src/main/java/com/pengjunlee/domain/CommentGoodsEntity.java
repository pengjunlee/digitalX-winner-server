package com.pengjunlee.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengjunlee
 * @create 2019-09-03 14:48
 */
public class CommentGoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String goodsName;

    private String price;

    private String imgUrl;

    private String saleCount; // 预售数量

    private String sellCount; // 月销量

    private String rateCount;

    private String shopId;

    private Boolean preSale;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    public String getSellCount() {
        return sellCount;
    }

    public void setSellCount(String sellCount) {
        this.sellCount = sellCount;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Boolean getPreSale() {
        return preSale;
    }

    public void setPreSale(Boolean preSale) {
        this.preSale = preSale;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
