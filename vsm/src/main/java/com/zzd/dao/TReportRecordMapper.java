package com.zzd.dao;

import com.zzd.model.TReportRecord;
import com.zzd.model.TReportRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReportRecordMapper {
    int countByExample(TReportRecordExample example);

    int deleteByExample(TReportRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TReportRecord record);

    int insertSelective(TReportRecord record);

    List<TReportRecord> selectByExample(TReportRecordExample example);

    TReportRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TReportRecord record, @Param("example") TReportRecordExample example);

    int updateByExample(@Param("record") TReportRecord record, @Param("example") TReportRecordExample example);

    int updateByPrimaryKeySelective(TReportRecord record);

    int updateByPrimaryKey(TReportRecord record);
}