package com.zzd.controller;

import com.zzd.model.TAdmin;
import com.zzd.model.TApplyRecord;
import com.zzd.service.ApplyRecordService;
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
public class ApplyRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ApplyRecordService applyRecordService;


    @RequestMapping(value = "listApplyRecord" ,method = RequestMethod.GET)
    public String listAdmin(HttpServletRequest request){
        try {
            List<TApplyRecord> applyRecords = applyRecordService.listApplyRecords();
            request.setAttribute("applyRecords",applyRecords);
            return "listApplyRecord";
        }catch (Exception e){
            logger.error("获取用户申请列表失败，原因{}",e);
            return e.getMessage();
        }
    }


    @RequestMapping(value = "AgreeApply")
    public String AgreeApply(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            TApplyRecord applyRecord = applyRecordService.queryApplyRecordById(id);
            applyRecord.setStatus((byte)2);
            applyRecord.setReply("申请已被同意");
            applyRecordService.handleApplyRecord(applyRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TApplyRecord> applyRecords = applyRecordService.listApplyRecords();
            request.setAttribute("applyRecords",applyRecords);
            return "listApplyRecord";
        }catch (Exception e){
            logger.error("审批用户申请同意失败，原因{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "RejectApply")
    public String RejectApply(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            TApplyRecord applyRecord = applyRecordService.queryApplyRecordById(id);
            applyRecord.setStatus((byte)0);
            applyRecord.setReply("申请已被驳回");
            applyRecordService.handleApplyRecord(applyRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TApplyRecord> applyRecords = applyRecordService.listApplyRecords();
            request.setAttribute("applyRecords",applyRecords);
            return "listApplyRecord";
        }catch (Exception e){
            logger.error("审批用户申请驳回失败，原因{}",e);
            return e.getMessage();
        }
    }
}
