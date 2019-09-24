package com.zzd.service;

import com.zzd.model.TVideo;

import java.util.List;

public interface VideoService {
    List<TVideo> listVideos();

    int updateVideo(TVideo video,String loginName);

    int addVideo(TVideo video);

    List<TVideo> queryVideoByTitle(String title);

    TVideo queryVideoById(String id);



}
