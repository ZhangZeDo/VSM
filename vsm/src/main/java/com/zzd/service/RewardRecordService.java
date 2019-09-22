package com.zzd.service;

import com.zzd.model.TRewardRecord;

import java.util.List;

//打赏记录接口服务
public interface RewardRecordService {
    int addRewardRecord(TRewardRecord rewardRecord);

    List<TRewardRecord> listReward();
}
