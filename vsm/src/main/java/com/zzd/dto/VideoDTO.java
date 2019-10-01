package com.zzd.dto;

import com.zzd.model.TVideo;


public class VideoDTO extends TVideo {
    private String typeName;

    private int rewardTotal;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getRewardTotal() {
        return rewardTotal;
    }

    public void setRewardTotal(int rewardTotal) {
        this.rewardTotal = rewardTotal;
    }
}
