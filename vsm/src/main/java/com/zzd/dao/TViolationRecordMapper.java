package com.zzd.dao;

import com.zzd.model.TViolationRecord;
import com.zzd.model.TViolationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TViolationRecordMapper {
    int countByExample(TViolationRecordExample example);

    int deleteByExample(TViolationRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TViolationRecord record);

    int insertSelective(TViolationRecord record);

    List<TViolationRecord> selectByExample(TViolationRecordExample example);

    TViolationRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TViolationRecord record, @Param("example") TViolationRecordExample example);

    int updateByExample(@Param("record") TViolationRecord record, @Param("example") TViolationRecordExample example);

    int updateByPrimaryKeySelective(TViolationRecord record);

    int updateByPrimaryKey(TViolationRecord record);
}