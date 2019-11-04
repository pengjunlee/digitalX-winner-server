package com.pengjunlee.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:30
 */
public class CommentAppendEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long commentId;
    private Integer days;
    private String reply;

    private String commentTime;
    private List<String> pics;

    private String content;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
