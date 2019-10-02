package com.zzd.controller;

import com.xiaoleilu.hutool.util.StrUtil;
import com.zzd.model.*;
import com.zzd.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @Resource
    private ApplyRecordService applyRecordService;
    @Resource
    private VideoTypeService videoTypeService;
    @Resource
    private VideoService videoService;

    @RequestMapping(value = "/userLogin" , method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request){
        try{
            TUser user = new TUser();
            user.setUserName(request.getParameter("userName"));
            user.setUserPassword(request.getParameter("userPassword"));
            if (userService.queryUsersByName(user.getUserName())!=null){
                if (userService.queryUsersByName(user.getUserName()).getStatus()==(byte)0) {
                    request.setAttribute("loginMsg","该账户已被封锁，请联系我们解封！");
                    return "index";
                }
                if( !userService.userLogin(user)){
                    request.setAttribute("loginMsg","密码输入错误！");
                    return "index";
                }
                HttpSession session = request.getSession();
                session.setAttribute("User",userService.queryUsersByName(user.getUserName()));
                //查询推荐点击量高视频
                List<TVideo> bigClickVideo = videoService.getBigClickVideo();
                session.setAttribute("bigClickVideo",bigClickVideo);
                //查询推荐点赞量高视频
                List<TVideo> bigPraisesVideo = videoService.getBigPraises();
                session.setAttribute("bigPraisesVideo",bigPraisesVideo);
                //显示视频分类
                List<TVideoType> videoTypes = videoTypeService.listVideoTypes();
                session.setAttribute("videoTypes",videoTypes);
                //显示首页视频
                List<TVideo> videos = videoService.listVideoByType(null);
                request.setAttribute("videos",videos);
                request.setAttribute("theme","全部");

                return "vsmPage";
            }else if (adminService.queryAdminByName(user.getUserName())!=null){
                TAdmin admin = new TAdmin();
                admin.setAdminName(user.getUserName());
                admin.setAdminPassword(user.getUserPassword());
                if (adminService.queryAdminByName(admin.getAdminName()).getStatus()==(byte)0){
                    request.setAttribute("loginMsg","该账户已被封锁，请联系我们解封！");
                    return "index";
                }
                if( !adminService.adminLogin(admin)){
                    request.setAttribute("loginMsg","密码输入错误！");
                    return "index";
                }
                HttpSession session = request.getSession();
                session.setAttribute("Admin",adminService.queryAdminByName(admin.getAdminName()));
                return "homePage";
            }
            request.setAttribute("loginMsg","你输入的账号有误！");
            return "index";
        }catch (Exception e){
            logger.error("登录异常，原因是：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "registerUser" , method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request){
        try{
            TUser user = new TUser();
            user.setUserName(request.getParameter("userName"));
            String one = request.getParameter("oneUserPassword");
            String two = request.getParameter("twoUserPassword");
            if (userService.queryUsersByName(user.getUserName())!=null){
                request.setAttribute("registerMsg","用户名已存在");
                return "register";
            }
            if (!StrUtil.equals(one,two)){
                request.setAttribute("registerMsg","两次输入的密码不一致");
                return "register";
            }
            user.setUserPassword(one);
            user.setUserPhone(request.getParameter("userPhone"));
            user.setUserMail(request.getParameter("userMail"));
            userService.addUser(user);
            return "index";
        }catch (Exception e){
            logger.error("注册异常，原因是：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "contactUs" , method = RequestMethod.POST)
    public String contactUs(HttpServletRequest request){
        try{
            String userName = request.getParameter("userName");
            String remark = request.getParameter("remark");
            TApplyRecord applyRecord = new TApplyRecord();
            applyRecord.setUserName(userName);
            applyRecord.setRemark(remark);
            applyRecordService.addApplyRecord(applyRecord);
            return "index";
        }catch (Exception e){
            logger.error("发送联系异常，原因是：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "loginOut" , method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            session.setAttribute("User",null);
            session.setAttribute("Admin",null);
            return "index";
        }catch (Exception e){
            logger.error("退出系统异常，原因是：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "getInformation" , method = RequestMethod.GET)
    public String getInformation(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            TAdmin admin = (TAdmin) session.getAttribute("Admin");
            request.setAttribute("person",adminService.queryAdminByName(admin.getAdminName()));
            return "homePage";
        }catch (Exception e){
            logger.error("退出系统异常，原因是：{}",e);
            return e.getMessage();
        }
    }


    @RequestMapping(value = "listUser" ,method = RequestMethod.GET)
    public String listUser(HttpServletRequest request){
        try {
            List<TUser> users = userService.listUsers();
            request.setAttribute("users",users);
            return "listUser";
        }catch (Exception e){
            logger.error("获取人员列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    //封锁系统人员账号
    @RequestMapping(value = "blockadeUser")
    public String blockadeUser(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            TUser user = userService.queryUsersByName(name);
            user.setStatus((byte)0);
            userService.updateUser(user,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TUser> users = userService.listUsers();
            request.setAttribute("users",users);
            return "listUser";
        }catch (Exception e){
            logger.error("封锁用户账号失败，原因{}",e);
            return e.getMessage();
        }
    }

    //解封系统人员账号
    @RequestMapping(value = "UnsealUser")
    public String UnsealUser(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            TUser user = userService.queryUsersByName(name);
            user.setStatus((byte)1);
            userService.updateUser(user,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TUser> users = userService.listUsers();
            request.setAttribute("users",users);
            return "listUser";
        }catch (Exception e){
            logger.error("解封用户账号失败，原因{}",e);
            return e.getMessage();
        }
    }
}
