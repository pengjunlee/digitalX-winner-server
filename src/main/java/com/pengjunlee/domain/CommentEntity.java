package com.pengjunlee.domain;

import lombok.Data;

import java.util.List;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:30
 */
@Data
public class CommentEntity {


    private Long id; // 主键ID

    private String rateContent;

    private String rateDate;

    private List<String> pics;

    private Boolean anony;

    private Integer userVipLevel;
    private Boolean goldUser;

    private String displayUserNick;

    private CommentAppendEntity appendComment;

}
