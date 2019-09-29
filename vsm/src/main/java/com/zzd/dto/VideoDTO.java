package com.zzd.dto;

import com.zzd.model.TVideo;


public class VideoDTO extends TVideo {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
