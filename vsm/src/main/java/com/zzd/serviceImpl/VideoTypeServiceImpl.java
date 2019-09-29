package com.zzd.serviceImpl;

import com.zzd.dao.TVideoTypeMapper;
import com.zzd.model.TVideoType;
import com.zzd.model.TVideoTypeExample;
import com.zzd.service.VideoTypeService;
import com.zzd.utils.UniqueIdUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("videoTypeService")
public class VideoTypeServiceImpl implements VideoTypeService {
    @Resource
    private TVideoTypeMapper videoTypeMapper;


    @Override
    public List<TVideoType> listVideoTypes() {
        TVideoTypeExample example = new TVideoTypeExample();
        example.createCriteria();
        List<TVideoType> videoTypes = videoTypeMapper.selectByExample(example);
        return videoTypes;
    }

    @Override
    public List<TVideoType> getVideoTypes() {
        TVideoTypeExample example = new TVideoTypeExample();
        example.createCriteria().andStatusEqualTo((byte)1);
        List<TVideoType> videoTypes = videoTypeMapper.selectByExample(example);
        return videoTypes;
    }

    @Override
    public TVideoType queryVideoTypeById(String id) {
        TVideoType videoType = videoTypeMapper.selectByPrimaryKey(id);
        return videoType;
    }

    @Override
    public int addVideoType(TVideoType videoType) {
        videoType.setId(UniqueIdUtil.buildId("V"));
        videoType.setStatus((byte)1);
        setVideoTypeInfo(videoType,"system");
        int result = videoTypeMapper.insertSelective(videoType);
        return result;
    }

    @Override
    public int updateVideoType(TVideoType videoType, String loginName) {
        setVideoTypeInfo(videoType,loginName);
        TVideoTypeExample example = new TVideoTypeExample();
        example.createCriteria().andVideoTypeNameEqualTo(videoType.getVideoTypeName());
        int result = videoTypeMapper.updateByExample(videoType,example);
        return result;
    }

    @Override
    public TVideoType queryVideoTypeByName(String typeName) {
        TVideoTypeExample example = new TVideoTypeExample();
        example.createCriteria().andVideoTypeNameEqualTo(typeName);
        List<TVideoType> videoTypes = videoTypeMapper.selectByExample(example);
        if (videoTypes==null || videoTypes.size()==0){
            return null;
        }
        return videoTypes.get(0);
    }

    private void setVideoTypeInfo(TVideoType videoType,String loginName){
        if (videoType.getCreateBy()==null){
            videoType.setCreateBy(loginName);
        }
        videoType.setCreatedDatetime(new Date());
        videoType.setUpdateBy(loginName);
        videoType.setUpdatedDatetime(new Date());
    }
}
