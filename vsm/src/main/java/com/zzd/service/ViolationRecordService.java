package com.zzd.service;

import com.zzd.model.TViolationRecord;

import java.util.List;

//用户违规接口服务
public interface ViolationRecordService {
    List<TViolationRecord> listViolations();

    TViolationRecord queryViolationById(String id);

    int addViolationRecord(TViolationRecord violationRecord, String loginName);

    int closeViolationRecord(TViolationRecord violationRecord, String loginName);
}
