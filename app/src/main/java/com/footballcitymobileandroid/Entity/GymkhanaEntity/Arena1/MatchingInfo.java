package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;

/**
 * receiver
 * Created by smartlab on 16/6/4.
 */
public class MatchingInfo implements Serializable{
    private String returnType;   //比赛类型
    private String receiver;      //接受者
    private String matchingDate; //比赛日期
    private String matchingTime; //比赛时间
    private String matchRule;  //比赛规则
    private String costMode;//类型
    private Sender sender;
    private String createTime;   //创建时间
    private String receiverState;
    private String matchingState;
    private String seasonName;  //赛季名
    private String arenaName;   //竞技场名
    private String arenaMatchID;

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setMatchingDate(String matchingDate) {
        this.matchingDate = matchingDate;
    }

    public String getMatchingDate() {
        return matchingDate;
    }

    public void setMatchingTime(String matchingTime) {
        this.matchingTime = matchingTime;
    }

    public String getMatchingTime() {
        return matchingTime;
    }

    public void setMatchRule(String matchRule) {
        this.matchRule = matchRule;
    }

    public String getMatchRule() {
        return matchRule;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setMatchingState(String matchingState) {
        this.matchingState = matchingState;
    }

    public String getMatchingState() {
        return matchingState;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setAranaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getAranaName() {
        return arenaName;
    }

    public void setArenaMatchID(String arenaMatchID) {
        this.arenaMatchID = arenaMatchID;
    }

    public String getArenaMatchID() {
        return arenaMatchID;
    }
}
