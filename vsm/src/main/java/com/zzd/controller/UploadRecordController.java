package com.zzd.controller;

import com.zzd.model.TAdmin;
import com.zzd.model.TUploadRecord;
import com.zzd.service.UploadRecordService;
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
public class UploadRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UploadRecordService uploadRecordService;

    @RequestMapping(value = "/listVideoUpload",method = RequestMethod.GET)
    public String listVideoUpload(HttpServletRequest request){
        try{
            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("获取上传视频列表失败，原因：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/agreeUpload")
    public String agreeUpload(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            uploadRecord.setStatus((byte)2);
            uploadRecordService.approvalUpload(uploadRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("审批视频上传同意失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/rejectUpload")
    public String rejectUpload(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            uploadRecord.setStatus((byte)0);
            uploadRecordService.approvalUpload(uploadRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("审批文件上传拒绝失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/detailUpload")
    public String detailUpload(HttpServletRequest request){
        try{
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            request.setAttribute("uploadRecord",uploadRecord);
            return "detailUpload";
        }catch (Exception e){
            logger.error("获取上传记录详情失败，原因：{}",e);
            return e.getMessage();
        }
    }
}
