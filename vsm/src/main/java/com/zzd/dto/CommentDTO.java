package com.zzd.dto;

import com.zzd.model.TComment;

public class CommentDTO extends TComment {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
