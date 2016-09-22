package com.footballcitymobileandroid.Entity.ClubEntity.join;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/23.
 */
public class JoinApplys implements Serializable {
    private String joinApply_ID;
    private SenderInfo senderInfo;
    private String sendTime;
    private String describe;

    public void setJoinApply_ID(String joinApply_ID) {
        this.joinApply_ID = joinApply_ID;
    }

    public String getJoinApply_ID() {
        return joinApply_ID;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
