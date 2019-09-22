package com.zzd.service;

import com.zzd.model.TVideoType;

import java.util.List;

//视频类型接口服务
public interface VideoTypeService {
    List<TVideoType> listVideoTypes();

    TVideoType queryVideoTypeById(String id);

    int addVideoType(TVideoType videoType);

    int updateVideoType(TVideoType videoType, String loginName);

    TVideoType queryVideoTypeByName(String typeName);
}
