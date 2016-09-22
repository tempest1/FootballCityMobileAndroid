package com.footballcitymobileandroid.Entity.Base;

import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smartlab on 16/5/13.
 */

public class Response<T> implements Serializable {
    private String result;
    //common
    private T userInfo;
    private exception exception;
    private List<String> success;
    private List<Fail> fail;
    private List<T> players;   //10.10接口,
    private T player;           //11接口未处理
    private List<T> playerRecord;
    private T app;


    //sport
    private String detail;
    private String currentPage;      //arena1第5接口   common第13接口
    private String totalPages;       //arena2第5接口   common第13接口
    private List<T> sportDetail;
    private List<T> matchMemb;
    private String failDetail;
    private String sign;
    //join
    private List<T> invitations;
    private List<T> joinApplys;

    //club
    private String clubID;
    private List<String> right;
    private List<T> wrong;
    private String msg;
    private List<T> clubList;
    private String total_Pages;
    private String current_Page;
    private List<T> clubs;     //包含arena1的第3个接口
    private T club;
    private List<T> ClubRecord;
    private List<T> clubMemb;

    //arena1
    private List<String> delArena;
    private List<T> updateArena;
    private Condition condition;
//    private MatchingInfos matchingInfos; //sender
//    private MatchingInfo matchingInfo;  //reciver
    private List<T> matchingInfos;         //当T为MatchingInfos时作为sender,T为MatchingInfo时作为reciver
    private List<T> aranaMatchs;

    //arena2
//    private List<T> arenaMatchMenmbs;
     private List<T> matchMembs;
    private List<T> deployInfos;

    //arena3
    private String arenaMatchDesc;
    private List<T> nomiMembs;
    private List<T> playerRankings;
    private List<T> clubRankings;



    //common
    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }
    public void setT(T userInfo) {
        this.userInfo = userInfo;
    }
    public T getT() {
        return userInfo;
    }
    public void setException(exception exception) {
        this.exception = exception;
    }
    public exception getException() {
        return exception;
    }
    public void setSuccess(List<String> success){
        this.success= success;
    }
    public List<String> getSuccess(){
        return success;
    }
    public void setFail(List<Fail> fail){
        this.fail=fail;
    }
    public List<Fail> getFail(){
        return fail;
    }

    public void setPlayers(List<T> players) {
        this.players = players;
    }

    public List<T> getPlayers() {
        return players;
    }

    public void setPlayer(T player) {
        this.player = player;
    }

    public T getPlayer() {
        return player;
    }

    public void setPlayerRecord(List<T> playerRecord) {
        this.playerRecord = playerRecord;
    }

    public List<T> getPlayerRecord() {
        return playerRecord;
    }

    public void setApp(T app) {
        this.app = app;
    }

    public T getApp() {
        return app;
    }

    //sport
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
    public String getCurrentPage() {
        return currentPage;
    }
    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }
    public String getTotalPages() {
        return totalPages;
    }
    public void setSportDetail(List<T> sportDetail) {
        this.sportDetail = sportDetail;
    }
    public List<T> getSportDetail() {
        return sportDetail;
    }
    public void setMatchMemb(List<T> matchMemb) {
        this.matchMemb = matchMemb;
    }
    public List<T> getMatchMemb() {
        return matchMemb;
    }
    public void setFailDetail(String failDetail) {
        this.failDetail = failDetail;
    }
    public String getFailDetail() {
        return failDetail;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    //join
    public void setInvitations(List<T> invitations) {
        this.invitations = invitations;
    }
    public List<T> getInvitations() {
        return invitations;
    }
    public void setJoinApplys(List<T> joinApplys) {
        this.joinApplys = joinApplys;
    }
    public List<T> getJoinApplys() {
        return joinApplys;
    }

    //club
    public void setClubID(String clubID) {
        this.clubID = clubID;
    }
    public String getClubID() {
        return clubID;
    }
    public void setRight(List<String> right) {
        this.right = right;
    }
    public List<String> getRight() {
        return right;
    }
    public void setWrong(List<T> wrong) {
        this.wrong = wrong;
    }
    public List<T> getWrong() {
        return wrong;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    public void setClubList(List<T> clubList) {
        this.clubList = clubList;
    }
    public List<T> getClubList() {
        return clubList;
    }
    public void setTotal_Pages(String total_Pages) {
        this.total_Pages = total_Pages;
    }
    public String getTotal_Pages() {
        return total_Pages;
    }
    public void setCurrent_Page(String current_Page) {
        this.current_Page = current_Page;
    }
    public String getCurrent_Page() {
        return current_Page;
    }
    public void setClubs(List<T> clubs) {
        this.clubs = clubs;
    }
    public List<T> getClubs() {
        return clubs;
    }
    public void setClub(T club) {
        this.club = club;
    }
    public T getClub() {
        return club;
    }
//    public void setClubRecord(List<T> clubRecord) {
//        this.clubRecord = clubRecord;
//    }
//    public List<T> getClubRecord() {
//        return clubRecord;
//    }

    public void setClubRecord(List<T> clubRecord) {
        ClubRecord = clubRecord;
    }

    public List<T> getClubRecord() {
        return ClubRecord;
    }

    public void setClubMemb(List<T> clubMemb) {
        this.clubMemb = clubMemb;
    }
    public List<T> getClubMemb() {
        return clubMemb;
    }


    //arena1
    public void setDelArena(List<String> delArena) {
        this.delArena = delArena;
    }

    public List<String> getDelArena() {
        return delArena;
    }

    public void setUpdateArena(List<T> updateArena) {
        this.updateArena = updateArena;
    }

    public List<T> getUpdateArena() {
        return updateArena;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setMatchingInfos(List<T> matchingInfos) {
        this.matchingInfos = matchingInfos;
    }

    public List<T> getMatchingInfos() {
        return matchingInfos;
    }
    //    public void setMatchingInfos(MatchingInfos matchingInfos) {
//        this.matchingInfos = matchingInfos;
//    }
//
//    public MatchingInfos getMatchingInfos() {
//        return matchingInfos;
//    }
//
//    public void setMatchingInfo(MatchingInfo matchingInfo) {
//        this.matchingInfo = matchingInfo;
//    }
//
//    public MatchingInfo getMatchingInfo() {
//        return matchingInfo;
//    }

    public void setAranaMatchs(List<T> aranaMatchs) {
        this.aranaMatchs = aranaMatchs;
    }

    public List<T> getAranaMatchs() {
        return aranaMatchs;
    }

    //arena2
//    public void setArenaMatchMenmbs(List<T> arenaMatchMenmbs) {
//        this.arenaMatchMenmbs = arenaMatchMenmbs;
//    }
//
//    public List<T> getArenaMatchMenmbs() {
//        return arenaMatchMenmbs;
//    }


    public void setMatchMembs(List<T> matchMembs) {
        this.matchMembs = matchMembs;
    }

    public List<T> getMatchMembs() {
        return matchMembs;
    }

    public void setDeployInfos(List<T> deployInfos) {
        this.deployInfos = deployInfos;
    }

    public List<T> getDeployInfos() {
        return deployInfos;
    }

    //arena3
    public void setArenaMatchDesc(String arenaMatchDesc) {
        this.arenaMatchDesc = arenaMatchDesc;
    }

    public String getArenaMatchDesc() {
        return arenaMatchDesc;
    }

    public void setNomiMembs(List<T> nomiMembs) {
        this.nomiMembs = nomiMembs;
    }

    public List<T> getNomiMembs() {
        return nomiMembs;
    }

    public void setPlayerRankings(List<T> playerRankings) {
        this.playerRankings = playerRankings;
    }

    public List<T> getPlayerRankings() {
        return playerRankings;
    }

    public void setClubRankings(List<T> clubRankings) {
        this.clubRankings = clubRankings;
    }

    public List<T> getClubRankings() {
        return clubRankings;
    }
}
