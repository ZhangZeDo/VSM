package com.zzd.controller;

import com.zzd.model.TAdmin;
import com.zzd.model.TViolationRecord;
import com.zzd.service.ViolationRecordService;
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
public class ViolationRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ViolationRecordService violationRecordService;

    @RequestMapping(value = "listViolation" ,method = RequestMethod.GET)
    public String listViolation(HttpServletRequest request){
        try {
            List<TViolationRecord> violationRecords = violationRecordService.listViolations();
            request.setAttribute("violationRecords",violationRecords);
            return "listViolation";
        }catch (Exception e){
            logger.error("获取人员列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "closeViolation")
    public String closeViolation(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TViolationRecord violationRecord = violationRecordService.queryViolationById(id);
            violationRecordService.closeViolationRecord( violationRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TViolationRecord> violationRecords = violationRecordService.listViolations();
            request.setAttribute("violationRecords",violationRecords);
            return "listViolation";
        }catch (Exception e){
            logger.error("封锁系统人员账号失败，原因{}",e);
            return e.getMessage();
        }
    }
}
