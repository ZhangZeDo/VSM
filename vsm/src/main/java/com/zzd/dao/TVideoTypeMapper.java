package com.zzd.dao;

import com.zzd.model.TVideoType;
import com.zzd.model.TVideoTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVideoTypeMapper {
    int countByExample(TVideoTypeExample example);

    int deleteByExample(TVideoTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TVideoType record);

    int insertSelective(TVideoType record);

    List<TVideoType> selectByExample(TVideoTypeExample example);

    TVideoType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TVideoType record, @Param("example") TVideoTypeExample example);

    int updateByExample(@Param("record") TVideoType record, @Param("example") TVideoTypeExample example);

    int updateByPrimaryKeySelective(TVideoType record);

    int updateByPrimaryKey(TVideoType record);
}