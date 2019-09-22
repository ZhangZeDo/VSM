package com.zzd.dao;

import com.zzd.model.TRewardRecord;
import com.zzd.model.TRewardRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRewardRecordMapper {
    int countByExample(TRewardRecordExample example);

    int deleteByExample(TRewardRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRewardRecord record);

    int insertSelective(TRewardRecord record);

    List<TRewardRecord> selectByExample(TRewardRecordExample example);

    TRewardRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRewardRecord record, @Param("example") TRewardRecordExample example);

    int updateByExample(@Param("record") TRewardRecord record, @Param("example") TRewardRecordExample example);

    int updateByPrimaryKeySelective(TRewardRecord record);

    int updateByPrimaryKey(TRewardRecord record);
}