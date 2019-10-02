package com.zzd.serviceImpl;

import com.zzd.dao.TReportRecordMapper;
import com.zzd.model.*;
import com.zzd.service.CommentService;
import com.zzd.service.ReportRecordService;
import com.zzd.service.VideoService;
import com.zzd.service.ViolationRecordService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("reportRecordService")
public class ReportRecordServiceImpl implements ReportRecordService {
    @Resource
    private TReportRecordMapper reportRecordMapper;
    @Resource
    private ViolationRecordService violationRecordService;
    @Resource
    private CommentService commentService;
    @Resource
    private VideoService videoService;

    @Override
    public List<TReportRecord> listReport() {
        TReportRecordExample example = new TReportRecordExample();
        example.createCriteria();
        List<TReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        return reportRecords;
    }

    @Override
    public TReportRecord queryReportById(String id) {
        TReportRecord reportRecord = reportRecordMapper.selectByPrimaryKey(id);
        return reportRecord;
    }

    @Override
    public int addReportRecord(TReportRecord reportRecord) {
        reportRecord.setId(UniqueIdUtil.buildId("R"));
        reportRecord.setStatus((byte)1);
        setReportRecordInfo(reportRecord,"system");
        int result = reportRecordMapper.insertSelective(reportRecord);
        return result;
    }

    @Override
    public int handleReportRecord(TReportRecord reportRecord, String loginName) {
        setReportRecordInfo(reportRecord,"system");
        TReportRecordExample example = new TReportRecordExample();
        example.createCriteria().andIdEqualTo(reportRecord.getId());
        int result = reportRecordMapper.updateByExample(reportRecord,example);
        if (reportRecord.getStatus()==(byte)2){
            //在违规记录中添加一条记录
            TViolationRecord violationRecord = new TViolationRecord();
            String remark = "";
            String userID = "";
            if (reportRecord.getReportType()==(byte)1){ //举报类型是视频，先获取到该视频用户id
                TVideo video = videoService.queryVideoById(reportRecord.getReportId());
                userID = video.getUserId();
                remark = "用户"+userID+"的视频"+reportRecord.getReportId()+"违规";
            }else if (reportRecord.getReportType()==(byte)2){ //举报类型是评论，先获取到该评论用户id
                TComment comment = commentService.queryCommentById(reportRecord.getReportId());
                userID =comment.getUserId();
                remark = "用户"+userID+"的评论"+reportRecord.getReportId()+"违规";
                //关闭该条评论
                commentService.closeComment(comment,loginName);
            }
            violationRecord.setUserId(userID);
            violationRecord.setRemark(remark);
            //添加一条违规记录
            result = violationRecordService.addViolationRecord(violationRecord,loginName);
        }
        return result;
    }

    private void setReportRecordInfo(TReportRecord reportRecord,String loginName){
        if (reportRecord.getCreateBy()==null){
            reportRecord.setCreateBy(loginName);
        }
        reportRecord.setCreatedDatetime(new Date());
        reportRecord.setUpdateBy(loginName);
        reportRecord.setUpdatedDatetime(new Date());
    }
}
