package com.zzd.service;

import com.zzd.model.TComment;

import java.util.List;

//评论服务接口
public interface CommentService {

    List<TComment> listComments();

    int addComment(TComment comment);

    List<TComment> queryCommentByVideo(String videoId);

    TComment queryCommentById(String id);

    int closeComment(TComment comment, String loginName);
}
