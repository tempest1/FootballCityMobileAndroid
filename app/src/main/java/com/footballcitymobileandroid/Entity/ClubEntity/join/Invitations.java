package com.footballcitymobileandroid.Entity.ClubEntity.join;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/23.
 */
public class Invitations implements Serializable{
    private String invitationID;
    private SenderInfo senderInfo;
    private String sendTime;
    private String describe;

    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    public String getInvitationID() {
        return invitationID;
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
