package com.zzd.serviceImpl;

import com.zzd.dao.TUploadRecordMapper;
import com.zzd.model.TUploadRecord;
import com.zzd.model.TUploadRecordExample;
import com.zzd.model.TVideo;
import com.zzd.service.UploadRecordService;
import com.zzd.service.VideoService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("uploadRecordService")
public class UploadRecordServiceImpl implements UploadRecordService {
    @Resource
    private TUploadRecordMapper uploadRecordMapper;
    @Resource
    private VideoService videoService;

    @Override
    public List<TUploadRecord> listUploads() {
        TUploadRecordExample example = new TUploadRecordExample();
        example.createCriteria();
        List<TUploadRecord> uploadRecords = uploadRecordMapper.selectByExample(example);
        return uploadRecords;
    }

    @Override
    public int approvalUpload(TUploadRecord uploadRecord,String loginName) {
        //如果是审批通过，在在线视频上加入这条记录
        if (uploadRecord.getStatus()==(byte)2){
            TVideo video = new TVideo();

            video.setUserId(uploadRecord.getUserId());
            video.setVideoTitle(uploadRecord.getUploadTitle());
            video.setVideoType(uploadRecord.getUploadType());
            video.setVideoUrl(uploadRecord.getUploadUrl());
            video.setCoverUrl(uploadRecord.getCoverUrl());
            video.setVideoDescription(uploadRecord.getUploadDescription());

            videoService.addVideo(video);
        }
        setUploadRecordInfo(uploadRecord,loginName);
        int result = uploadRecordMapper.updateByPrimaryKey(uploadRecord);
        return result;
    }

    @Override
    public TUploadRecord queryUploadById(String id) {
        TUploadRecord uploadRecord = uploadRecordMapper.selectByPrimaryKey(id);
        return uploadRecord;
    }

    @Override
    public int addUploadRecord(TUploadRecord uploadRecord) {
        uploadRecord.setId(UniqueIdUtil.buildId("U"));
        setUploadRecordInfo(uploadRecord,"system");
        uploadRecord.setStatus((byte)1);
        int result =uploadRecordMapper.insertSelective(uploadRecord);
        return result;
    }

    private void setUploadRecordInfo(TUploadRecord uploadRecord,String loginName){
        if (uploadRecord.getCreateBy()==null){
            uploadRecord.setCreateBy(loginName);
        }
        uploadRecord.setCreatedDatetime(new Date());
        uploadRecord.setUpdateBy(loginName);
        uploadRecord.setUpdatedDatetime(new Date());
    }
}
