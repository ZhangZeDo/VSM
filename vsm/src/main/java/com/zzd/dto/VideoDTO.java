package com.zzd.dto;

import com.zzd.model.TVideo;

import java.util.List;


public class VideoDTO extends TVideo {
    private String typeName;

    private int rewardTotal;

    private List<CommentDTO> commentList;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getRewardTotal() {
        return rewardTotal;
    }

    public void setRewardTotal(int rewardTotal) {
        this.rewardTotal = rewardTotal;
    }

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDTO> commentList) {
        this.commentList = commentList;
    }
}
