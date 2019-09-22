package com.zzd.service;

import com.zzd.model.TApplyRecord;

import java.util.List;

//申请账号解封接口服务
public interface ApplyRecordService {
    TApplyRecord queryApplyRecordById(String id);

    int addApplyRecord(TApplyRecord applyRecord);

    int handleApplyRecord(TApplyRecord applyRecord, String loginName);

    List<TApplyRecord> listApplyRecords();

}
