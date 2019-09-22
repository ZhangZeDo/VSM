package com.zzd.serviceImpl;


import com.zzd.dao.TCommentMapper;
import com.zzd.model.TComment;
import com.zzd.model.TCommentExample;
import com.zzd.service.CommentService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private TCommentMapper commentMapper;

    @Override
    public List<TComment> listComments() {
        TCommentExample example = new TCommentExample();
        example.createCriteria();
        List<TComment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    @Override
    public int addComment(TComment comment) {
        comment.setId(UniqueIdUtil.buildId("C"));
        comment.setStatus((byte)1);
        setCommentInfo(comment,"system");
        int result = commentMapper.insertSelective(comment);
        return result;
    }

    @Override
    public List<TComment> queryCommentByVideo(String videoId) {
        TCommentExample example = new TCommentExample();
        example.createCriteria().andVideoIdEqualTo(videoId).andStatusEqualTo((byte)1);
        List<TComment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    @Override
    public TComment queryCommentById(String id) {
        TCommentExample example = new TCommentExample();
        example.createCriteria().andIdEqualTo(id).andStatusEqualTo((byte)1);
        List<TComment> comments = commentMapper.selectByExample(example);
        if (comments != null && comments.size()!=0){
            return comments.get(0);
        }
        return null;
    }

    @Override
    public int closeComment(TComment comment, String loginName) {
        comment.setStatus((byte)0);
        setCommentInfo(comment,loginName);
        TCommentExample example = new TCommentExample();
        example.createCriteria().andIdEqualTo(comment.getId());
        int result = commentMapper.updateByExample(comment,example);
        return result;
    }

    private void setCommentInfo(TComment comment,String loginName){
        if (comment.getCreateBy()==null){
            comment.setCreateBy(loginName);
        }
        comment.setCreatedDatetime(new Date());
        comment.setUpdateBy(loginName);
        comment.setUpdatedDatetime(new Date());
    }
}
