package com.pengjunlee.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author pengjunlee
 * @create 2019-11-01 17:53
 */
@Data
public class BaseDomain {


    private Date createdTime;

    private Date updatedTime;

    private Long createdBy;

    private Long updatedBy;

    public void beforeSave() {
        Date now = new Date();
        this.setCreatedTime(now);
        this.setUpdatedTime(now);
        this.setCreatedBy(1L);
        this.setUpdatedBy(1L);
    }

    public void beforeUpdate() {
        Date now = new Date();
        this.setUpdatedTime(now);
        this.setUpdatedBy(1L);
    }
}
