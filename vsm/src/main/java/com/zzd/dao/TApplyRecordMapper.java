package com.zzd.dao;

import com.zzd.model.TApplyRecord;
import com.zzd.model.TApplyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TApplyRecordMapper {
    int countByExample(TApplyRecordExample example);

    int deleteByExample(TApplyRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TApplyRecord record);

    int insertSelective(TApplyRecord record);

    List<TApplyRecord> selectByExample(TApplyRecordExample example);

    TApplyRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TApplyRecord record, @Param("example") TApplyRecordExample example);

    int updateByExample(@Param("record") TApplyRecord record, @Param("example") TApplyRecordExample example);

    int updateByPrimaryKeySelective(TApplyRecord record);

    int updateByPrimaryKey(TApplyRecord record);
}