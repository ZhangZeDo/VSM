package com.zzd.serviceImpl;

import com.zzd.dao.TVideoMapper;
import com.zzd.dto.VideoDTO;
import com.zzd.model.TRewardRecord;
import com.zzd.model.TVideo;
import com.zzd.model.TVideoExample;
import com.zzd.model.TVideoType;
import com.zzd.service.RewardRecordService;
import com.zzd.service.VideoService;
import com.zzd.service.VideoTypeService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private TVideoMapper videoMapper;
    @Resource
    private VideoTypeService videoTypeService;
    @Resource
    private RewardRecordService rewardRecordService;

    @Override
    public List<TVideo> listVideos() {
        TVideoExample example = new TVideoExample();
        example.createCriteria();
        List<TVideo> videos = videoMapper.selectByExample(example);
        return videos;
    }

    @Override
    public List<TVideo> listVideosByType(String type) {
        TVideoExample example = new TVideoExample();
        if (type.isEmpty()){
            example.createCriteria().andStatusEqualTo((byte)1);
        }else{
            example.createCriteria().andStatusEqualTo((byte)1).andVideoTypeEqualTo(type);
        }
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
    public VideoDTO queryVideoById(String id) {
        TVideo video = videoMapper.selectByPrimaryKey(id);
        VideoDTO videoDTO = new VideoDTO();
        BeanUtils.copyProperties(video,videoDTO);
        //设置视频类型名称
        TVideoType videoType = videoTypeService.queryVideoTypeById(video.getVideoType());
        videoDTO.setTypeName(videoType.getVideoTypeName());
        return videoDTO;
    }

    @Override
    public List<TVideo> getBigClickVideo() {
        TVideoExample example = new TVideoExample();
        example.createCriteria().andStatusEqualTo((byte)1);
        example.setOrderByClause("video_clicks DESC");
        List<TVideo> videos = videoMapper.selectByExample(example);
        List<TVideo> list = new ArrayList<>();
        for (int i=0;i<videos.size();i++){
            list.add(videos.get(i));
            if (i==3){
                break;
            }
        }
        return list;
    }

    @Override
    public List<TVideo> getBigPraises() {
        TVideoExample example = new TVideoExample();
        example.createCriteria().andStatusEqualTo((byte)1);
        example.setOrderByClause("video_praises DESC");
        List<TVideo> videos = videoMapper.selectByExample(example);
        List<TVideo> list = new ArrayList<>();
        for (int i=0;i<videos.size();i++){
            list.add(videos.get(i));
            if (i==2){
                break;
            }
        }
        return list;
    }

    @Override
    public List<TVideo> listVideoByType(String type) {
        TVideoExample example = new TVideoExample();
        if (type==null || type.length()==0){
            example.createCriteria().andStatusEqualTo((byte)1);
        }else {
            example.createCriteria().andStatusEqualTo((byte)1).andVideoTypeEqualTo(videoTypeService.queryVideoTypeByName(type).getId());
        }
        List<TVideo> videos = videoMapper.selectByExample(example);
        return videos;
    }

    @Override
    public List<VideoDTO> listMyVideos(String userId) {
        TVideoExample example = new TVideoExample();
        example.createCriteria().andStatusEqualTo((byte)1).andUserIdEqualTo(userId);
        List<TVideo> myVideos = videoMapper.selectByExample(example);
        List<VideoDTO> videoDTOS = new ArrayList<>();
        for (TVideo myVideo : myVideos) {
            VideoDTO videoDTO = new VideoDTO();
            BeanUtils.copyProperties(myVideo,videoDTO);
            //设置视频类型名称
            TVideoType videoType = videoTypeService.queryVideoTypeById(myVideo.getVideoType());
            videoDTO.setTypeName(videoType.getVideoTypeName());
            //设置该视频共收获打赏量
            int total =  rewardRecordService.listRewardByVideoId(myVideo.getId());
            videoDTO.setRewardTotal(total);
            videoDTOS.add(videoDTO);
        }
        return videoDTOS;
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
