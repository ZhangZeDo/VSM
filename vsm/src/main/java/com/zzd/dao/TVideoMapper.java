package com.zzd.dao;

import com.zzd.model.TVideo;
import com.zzd.model.TVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVideoMapper {
    int countByExample(TVideoExample example);

    int deleteByExample(TVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TVideo record);

    int insertSelective(TVideo record);

    List<TVideo> selectByExample(TVideoExample example);

    TVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TVideo record, @Param("example") TVideoExample example);

    int updateByExample(@Param("record") TVideo record, @Param("example") TVideoExample example);

    int updateByPrimaryKeySelective(TVideo record);

    int updateByPrimaryKey(TVideo record);
}