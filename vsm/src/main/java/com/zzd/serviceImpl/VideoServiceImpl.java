package com.zzd.serviceImpl;

import com.zzd.dao.TVideoMapper;
import com.zzd.dao.TVideoTypeMapper;
import com.zzd.model.TVideo;
import com.zzd.model.TVideoExample;
import com.zzd.service.VideoService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private TVideoMapper videoMapper;

    @Override
    public List<TVideo> listVideos() {
        TVideoExample example = new TVideoExample();
        example.createCriteria();
        List<TVideo> videos = videoMapper.selectByExample(example);
        return videos;
    }

    @Override
    public int updateVideo(TVideo video, String loginName) {
        setVideoInfo(video,loginName);
        int result = videoMapper.updateByPrimaryKey(video);
        return result;
    }

    @Override
    public int addVideo(TVideo video) {
        video.setId(UniqueIdUtil.buildId("V"));
        video.setVideoClicks(0);
        video.setVideoPraises(0);
        video.setStatus((byte)1);
        setVideoInfo(video,"system");
        int result = videoMapper.insertSelective(video);
        return result;
    }

    @Override
    public List<TVideo> queryVideoByTitle(String title) {
        TVideoExample example = new TVideoExample();
        example.createCriteria().andVideoTitleLike(title);
        List<TVideo> videos = videoMapper.selectByExample(example);
        return videos;
    }

    @Override
    public TVideo queryVideoById(String id) {
        TVideo video = videoMapper.selectByPrimaryKey(id);
        return video;
    }

    private void setVideoInfo(TVideo video,String loginName){
        if (video.getCreateBy()==null){
            video.setCreateBy(loginName);
        }
        video.setCreatedDatetime(new Date());
        video.setUpdateBy(loginName);
        video.setUpdatedDatetime(new Date());
    }
}
