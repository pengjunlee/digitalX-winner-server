package com.pengjunlee.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:30
 */
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // 主键ID

    private String rateContent;

    private String rateDate;

    private List<String> pics;

    private Boolean anony;

    private Integer userVipLevel;
    private Boolean goldUser;

    private String displayUserNick;

    private CommentAppendEntity appendComment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRateContent() {
        return rateContent;
    }

    public void setRateContent(String rateContent) {
        this.rateContent = rateContent;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public Boolean getAnony() {
        return anony;
    }

    public void setAnony(Boolean anony) {
        this.anony = anony;
    }

    public Integer getUserVipLevel() {
        return userVipLevel;
    }

    public void setUserVipLevel(Integer userVipLevel) {
        this.userVipLevel = userVipLevel;
    }

    public Boolean getGoldUser() {
        return goldUser;
    }

    public void setGoldUser(Boolean goldUser) {
        this.goldUser = goldUser;
    }

    public String getDisplayUserNick() {
        return displayUserNick;
    }

    public void setDisplayUserNick(String displayUserNick) {
        this.displayUserNick = displayUserNick;
    }

    public CommentAppendEntity getAppendComment() {
        return appendComment;
    }

    public void setAppendComment(CommentAppendEntity appendComment) {
        this.appendComment = appendComment;
    }
}
