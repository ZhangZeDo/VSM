package com.zzd.controller;

import com.zzd.model.TAdmin;
import com.zzd.model.TComment;
import com.zzd.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CommentService commentService;

    @RequestMapping(value = "listComment" ,method = RequestMethod.GET)
    public String listComment(HttpServletRequest request){
        try {
            List<TComment> comments = commentService.listComments();
            request.setAttribute("comments",comments);
            return "listComment";
        }catch (Exception e){
            logger.error("获取用户评论列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "closeComment")
    public String closeComment(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TComment comment = commentService.queryCommentById(id);
            commentService.closeComment(comment,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TComment> comments = commentService.listComments();
            request.setAttribute("comments",comments);
            return "listComment";
        }catch (Exception e){
            logger.error("关闭当前评论失败，原因{}",e);
            return e.getMessage();
        }
    }
}
