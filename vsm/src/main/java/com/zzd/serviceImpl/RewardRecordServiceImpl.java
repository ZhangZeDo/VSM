package com.zzd.serviceImpl;

import com.zzd.dao.TRewardRecordMapper;
import com.zzd.model.TRewardRecord;
import com.zzd.model.TRewardRecordExample;
import com.zzd.service.RewardRecordService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("rewardRecordService")
public class RewardRecordServiceImpl implements RewardRecordService {
    @Resource
    private TRewardRecordMapper rewardRecordMapper;

    @Override
    public int addRewardRecord(TRewardRecord rewardRecord) {
        rewardRecord.setId(UniqueIdUtil.buildId("R"));
        setRewardRecordInfo(rewardRecord,"system");
        int result = rewardRecordMapper.insertSelective(rewardRecord);
        return result;
    }

    @Override
    public List<TRewardRecord> listReward() {
        TRewardRecordExample example = new TRewardRecordExample();
        example.createCriteria();
        List<TRewardRecord> rewardRecords = rewardRecordMapper.selectByExample(example);
        return rewardRecords;
    }

    @Override
    public int listRewardByVideoId(String id) {
        TRewardRecordExample example = new TRewardRecordExample();
        example.createCriteria().andVideoIdEqualTo(id);
        List<TRewardRecord> rewardRecords = rewardRecordMapper.selectByExample(example);
        if (rewardRecords==null || rewardRecords.size()==0){
            return 0;
        }else {
            int total=0;
            for (TRewardRecord rewardRecord : rewardRecords) {
                total = total + rewardRecord.getRewardNum();
            }
            return total;
        }
    }

    private void setRewardRecordInfo(TRewardRecord rewardRecord,String loginName){
        if (rewardRecord.getCreateBy()==null){
            rewardRecord.setCreateBy(loginName);
        }
        rewardRecord.setCreatedDatetime(new Date());
        rewardRecord.setUpdateBy(loginName);
        rewardRecord.setUpdatedDatetime(new Date());
    }
}
