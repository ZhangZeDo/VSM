package com.zzd.service;

import com.zzd.dto.VideoDTO;
import com.zzd.model.TVideo;

import java.util.List;

public interface VideoService {
    List<TVideo> listVideos();

    List<TVideo> listVideosByType(String type);

    int updateVideo(TVideo video,String loginName);

    int addVideo(TVideo video);

    List<TVideo> queryVideoByTitle(String title);

    VideoDTO queryVideoById(String id);

    List<TVideo> getBigClickVideo();

    List<TVideo> getBigPraises();

    List<TVideo> listVideoByType(String type);

    List<VideoDTO> listMyVideos(String userId);


}
