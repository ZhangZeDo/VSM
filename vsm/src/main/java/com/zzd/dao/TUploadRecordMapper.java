package com.zzd.dao;

import com.zzd.model.TUploadRecord;
import com.zzd.model.TUploadRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUploadRecordMapper {
    int countByExample(TUploadRecordExample example);

    int deleteByExample(TUploadRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TUploadRecord record);

    int insertSelective(TUploadRecord record);

    List<TUploadRecord> selectByExample(TUploadRecordExample example);

    TUploadRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TUploadRecord record, @Param("example") TUploadRecordExample example);

    int updateByExample(@Param("record") TUploadRecord record, @Param("example") TUploadRecordExample example);

    int updateByPrimaryKeySelective(TUploadRecord record);

    int updateByPrimaryKey(TUploadRecord record);
}