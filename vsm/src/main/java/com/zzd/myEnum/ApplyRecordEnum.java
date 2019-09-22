package com.zzd.myEnum;


public enum ApplyRecordEnum {
    Reject((byte)0,"已驳回"),
    Audit((byte)1,"审核中"),
    Pass((byte)2,"已通过");


    private byte status;
    private String decs;

    ApplyRecordEnum(byte status, String decs) {
        this.status = status;
        this.decs = decs;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }
}
