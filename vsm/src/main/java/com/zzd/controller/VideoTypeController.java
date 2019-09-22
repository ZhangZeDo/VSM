package com.zzd.controller;

import com.xiaoleilu.hutool.util.StrUtil;
import com.zzd.model.TAdmin;
import com.zzd.model.TVideo;
import com.zzd.model.TVideoType;
import com.zzd.service.AdminService;
import com.zzd.service.VideoTypeService;
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
public class VideoTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private VideoTypeService videoTypeService;
    @Resource
    private AdminService adminService;

    @RequestMapping(value = "listVideoType" ,method = RequestMethod.GET)
    public String listVideoType(HttpServletRequest request){
        try {
            List<TVideoType> videoTypes = videoTypeService.listVideoTypes();
            request.setAttribute("videoTypes",videoTypes);
            return "listVideoType";
        }catch (Exception e){
            logger.error("获取视频类型列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    //封锁视频类型
    @RequestMapping(value = "blockadeVideoType")
    public String blockadeVideoType(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TVideoType videoType = videoTypeService.queryVideoTypeById(id);
            videoType.setStatus((byte)0);
            videoTypeService.updateVideoType(videoType,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TVideoType> videoTypes = videoTypeService.listVideoTypes();
            request.setAttribute("videoTypes",videoTypes);
            return "listVideoType";
        }catch (Exception e){
            logger.error("封锁系统人员账号失败，原因{}",e);
            return e.getMessage();
        }
    }

    //解封视频类型
    @RequestMapping(value = "UnsealVideoType")
    public String UnsealVideoType(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TVideoType videoType = videoTypeService.queryVideoTypeById(id);
            videoType.setStatus((byte)1);
            videoTypeService.updateVideoType(videoType,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TVideoType> videoTypes = videoTypeService.listVideoTypes();
            request.setAttribute("videoTypes",videoTypes);
            return "listVideoType";
        }catch (Exception e){
            logger.error("解封系统人员账号失败，原因{}",e);
            return e.getMessage();
        }
    }

    //添加一个新的视频类型
    @RequestMapping(value = "addVideoType" ,method = RequestMethod.POST)
    public String addVideoType(HttpServletRequest request){
        try {
            String name = request.getParameter("videoTypeName");
            if (videoTypeService.queryVideoTypeByName(name)!=null){
                request.setAttribute("addVideoTypeMsg","添加失败，当前类型已存在");
                return "addVideoType";
            }
            TVideoType videoType = new TVideoType();
            videoType.setVideoTypeName(name);
            videoTypeService.addVideoType(videoType);
            List<TVideoType> videoTypes = videoTypeService.listVideoTypes();
            request.setAttribute("videoTypes",videoTypes);
            return "listVideoType";
        }catch (Exception e){
            logger.error("添加视频类型，原因{}",e);
            return e.getMessage();
        }
    }

}
