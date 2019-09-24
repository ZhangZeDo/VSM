package com.zzd.service;

import com.zzd.model.TUploadRecord;

import java.util.List;

public interface UploadRecordService {
    List<TUploadRecord> listUploads();

    int approvalUpload(TUploadRecord uploadRecord,String loginName);

    TUploadRecord queryUploadById(String id);

    int addUploadRecord(TUploadRecord uploadRecord);
}
