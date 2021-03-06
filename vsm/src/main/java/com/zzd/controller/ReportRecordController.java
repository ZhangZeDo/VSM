package com.zzd.controller;

import com.zzd.dto.VideoDTO;
import com.zzd.model.TAdmin;
import com.zzd.model.TReportRecord;
import com.zzd.model.TUser;
import com.zzd.service.ReportRecordService;
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
public class ReportRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ReportRecordService reportRecordService;
    @Resource
    private VideoService videoService;

    @RequestMapping(value = "listReportRecord" ,method = RequestMethod.GET)
    public String listReport(HttpServletRequest request){
        try {
            List<TReportRecord> reportRecords = reportRecordService.listReport();
            request.setAttribute("reportRecords",reportRecords);
            return "listReport";
        }catch (Exception e){
            logger.error("获取用户举报记录列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "agreeReport")
    public String agreeReport(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TReportRecord reportRecord = reportRecordService.queryReportById(id);
            reportRecord.setStatus((byte)2);
            reportRecordService.handleReportRecord(reportRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TReportRecord> reportRecords = reportRecordService.listReport();
            request.setAttribute("reportRecords",reportRecords);
            return "listReport";
        }catch (Exception e){
            logger.error("同意该条举报记录失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "rejectReport")
    public String rejectReport(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TReportRecord reportRecord = reportRecordService.queryReportById(id);
            reportRecord.setStatus((byte)0);
            reportRecordService.handleReportRecord(reportRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());
            List<TReportRecord> reportRecords = reportRecordService.listReport();
            request.setAttribute("reportRecords",reportRecords);
            return "listReport";
        }catch (Exception e){
            logger.error("驳回该条举报记录失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "addReport",method = RequestMethod.POST)
    public String addReport(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            TUser user = (TUser)session.getAttribute("User");

            String remark = request.getParameter("remark");
            String reportId = request.getParameter("reportId");
            String reportType = request.getParameter("reportType");

            TReportRecord reportRecord = new TReportRecord();
            reportRecord.setUserId(user.getId());
            reportRecord.setRemark(remark);
            reportRecord.setReportType(Integer.valueOf(reportType));
            reportRecord.setReportId(reportId);
            reportRecordService.addReportRecord(reportRecord);

            VideoDTO videoDTO = videoService.queryVideoById(reportId);
            request.setAttribute("video",videoDTO);
            return "videoDetail";
        }catch (Exception e){
            logger.error("用户举报失败，原因{}",e);
            return e.getMessage();
        }
    }
}
