package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;
import java.util.List;

/**
 * sender
 * Created by smartlab on 16/6/4.
 */
public class MatchingInfos implements Serializable{

    private String returnType;
    private Sender sender;
    private String matchingDate;
    private String matchingTime;
    private String matchRule;
    private String costMode;
    private List<Receiver> receiver;
    private String createTime;
    private String matchingState;
    private String seasonName;
    private String aranaName;

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
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

    public void setReceiver(List<Receiver> receiver) {
        this.receiver = receiver;
    }

    public List<Receiver> getReceiver() {
        return receiver;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
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

    public void setAranaName(String aranaName) {
        this.aranaName = aranaName;
    }

    public String getAranaName() {
        return aranaName;
    }



}
