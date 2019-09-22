package com.zzd.controller;

import com.xiaoleilu.hutool.util.StrUtil;
import com.zzd.model.TAdmin;
import com.zzd.service.AdminService;
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
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AdminService adminService;

    @RequestMapping(value = "changePassword" ,method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            TAdmin admin = (TAdmin) session.getAttribute("Admin");
            String password = request.getParameter("password");
            String newPassword = (request.getParameter("newPassword"));
            if (!StrUtil.equals(admin.getAdminPassword(),password)){
                request.setAttribute("changeMsg","修改密码失败，你输入的密码有误！");
                return "changPassword";
            }
            if (StrUtil.equals(password,newPassword)){
                request.setAttribute("changeMsg","修改密码失败，新密码和旧密码一致");
                return "changPassword";
            }
            admin.setAdminPassword(newPassword);
            adminService.updateAdmin(admin,admin.getAdminName());
            return "homePage";
        }catch (Exception e){
            logger.error("修改密码异常，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "listAdmin" ,method = RequestMethod.GET)
    public String listAdmin(HttpServletRequest request){
        try {
            List<TAdmin> admins = adminService.listAdmins();
            request.setAttribute("admins",admins);
            return "listAdmin";
        }catch (Exception e){
            logger.error("获取人员列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    //封锁系统人员账号
    @RequestMapping(value = "blockadeAdmin")
    public String blockadeAdmin(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            TAdmin admin = adminService.queryAdminByName(name);
            admin.setStatus((byte)0);
            adminService.updateAdmin(admin,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TAdmin> admins = adminService.listAdmins();
            request.setAttribute("admins",admins);
            return "listAdmin";
        }catch (Exception e){
            logger.error("封锁系统人员账号失败，原因{}",e);
            return e.getMessage();
        }
    }

    //解封系统人员账号
    @RequestMapping(value = "UnsealAdmin")
    public String UnsealAdmin(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            TAdmin admin = adminService.queryAdminByName(name);
            admin.setStatus((byte)1);
            adminService.updateAdmin(admin,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TAdmin> admins = adminService.listAdmins();
            request.setAttribute("admins",admins);
            return "listAdmin";
        }catch (Exception e){
            logger.error("解封系统人员账号失败，原因{}",e);
            return e.getMessage();
        }
    }

}
