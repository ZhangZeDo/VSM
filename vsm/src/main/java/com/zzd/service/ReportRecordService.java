package com.zzd.service;

import com.zzd.model.TReportRecord;

import java.util.List;

//举报记录接口服务
public interface ReportRecordService {
    List<TReportRecord> listReport();

    TReportRecord queryReportById(String id);

    int addReportRecord(TReportRecord reportRecord);

    int handleReportRecord(TReportRecord reportRecord, String loginName);
}
