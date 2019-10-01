package com.zzd.controller;

import com.zzd.dto.VideoDTO;
import com.zzd.model.TAdmin;
import com.zzd.model.TUser;
import com.zzd.model.TVideo;
import com.zzd.service.VideoService;
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
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private VideoService videoService;

    @RequestMapping(value = "/listVideo",method = RequestMethod.GET)
    public String listVideo(HttpServletRequest request){
        try{
            List<TVideo> videos = videoService.listVideos();
            request.setAttribute("videos",videos);
            return "listVideo";
        }catch (Exception e){
            logger.error("获取在线视频列表失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/upperVideo")
    public String upperVideo(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TVideo video = videoService.queryVideoById(id);
            video.setStatus((byte)1);
            videoService.updateVideo(video,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TVideo> videos = videoService.listVideos();
            request.setAttribute("videos",videos);
            return "listVideo";
        }catch (Exception e){
            logger.error("上架在线视频列表失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/lowerVideo")
    public String lowerVideo(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TVideo video = videoService.queryVideoById(id);
            video.setStatus((byte)0);
            videoService.updateVideo(video,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TVideo> videos = videoService.listVideos();
            request.setAttribute("videos",videos);
            return "listVideo";
        }catch (Exception e){
            logger.error("下架在线视频列表失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/detailVideo")
    public String detailVideo(HttpServletRequest request){
        try{
            String id = request.getParameter("id");
            VideoDTO videoDTO = videoService.queryVideoById(id);
            request.setAttribute("video",videoDTO);
            return "detailVideo";
        }catch (Exception e){
            logger.error("获取在线视频详情失败，原因：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/videoDetail")
    public String videoDetail(HttpServletRequest request){
        try{
            String id = request.getParameter("id");
            VideoDTO videoDTO = videoService.queryVideoById(id);
            request.setAttribute("video",videoDTO);
            return "videoDetail";
        }catch (Exception e){
            logger.error("获取在线视频详情失败，原因：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "getMyVideos")
    public String getMyVideos(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            TUser user = (TUser)session.getAttribute("User");
            List<VideoDTO> myVideos = videoService.listMyVideos(user.getId());
            request.setAttribute("myVideos",myVideos);
            return "myVideo";
        }catch (Exception e){
            logger.error("查询我的视频失败，原因{}",e);
            return e.getMessage();
        }
    }



}
