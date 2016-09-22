package com.footballcitymobileandroid.Entity.ClubEntity.sport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smartlab on 16/5/23.
 */
public class SportDetail implements Serializable{
    private String  s_record_id;
    private String home_club_id;
    private String guest_club_name;
    private String join_num;
    private String start_time;
    private String end_time;
    private String field_name;
    private String h_club_goal;
    private String g_club_goal;
    private String sport_state;
    private String photo;

    private List<SignInfo> signInfo;

    public static String signresult="false";
    public static String homescore;
    public static String visitingscore;

    public void setS_record_id(String s_record_id) {
        this.s_record_id = s_record_id;
    }

    public String getS_record_id() {
        return s_record_id;
    }

    public void setHome_club_id(String home_club_id) {
        this.home_club_id = home_club_id;
    }

    public String getHome_club_id() {
        return home_club_id;
    }

    public void setGuest_club_name(String guest_club_name) {
        this.guest_club_name = guest_club_name;
    }

    public String getGuest_club_name() {
        return guest_club_name;
    }

    public void setJoin_num(String join_num) {
        this.join_num = join_num;
    }

    public String getJoin_num() {
        return join_num;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setG_club_goal(String g_club_goal) {
        this.g_club_goal = g_club_goal;
    }

    public String getG_club_goal() {
        return g_club_goal;
    }

    public void setH_club_goal(String h_club_goal) {
        this.h_club_goal = h_club_goal;
    }

    public String getH_club_goal() {
        return h_club_goal;
    }

    public void setSport_state(String sport_state) {
        this.sport_state = sport_state;
    }

    public String getSport_state() {
        return sport_state;
    }

    public void setSignInfo(List<SignInfo> signInfo) {
        this.signInfo = signInfo;
    }

    public List<SignInfo> getSignInfo() {
        return signInfo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
    //    private String sportID;
//    private String startDate;
//    private String startTime;
//    private String homeTeam;
//    private String visitingTeam;
//    private String joinNum;
//    private String matchField;
//    private String score;
//
//    public void setSportID(String sportID) {
//        this.sportID = sportID;
//    }
//
//    public String getSportID() {
//        return sportID;
//    }
//
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setHomeTeam(String homeTeam) {
//        this.homeTeam = homeTeam;
//    }
//
//    public String getHomeTeam() {
//        return homeTeam;
//    }
//
//    public void setVisitingTeam(String visitingTeam) {
//        this.visitingTeam = visitingTeam;
//    }
//
//    public String getVisitingTeam() {
//        return visitingTeam;
//    }
//
//    public void setJoinNum(String joinNum) {
//        this.joinNum = joinNum;
//    }
//
//    public String getJoinNum() {
//        return joinNum;
//    }
//
//    public void setMatchField(String matchField) {
//        this.matchField = matchField;
//    }
//
//    public String getMatchField() {
//        return matchField;
//    }
//
//    public void setScore(String score) {
//        this.score = score;
//    }
//
//    public String getScore() {
//        return score;
//    }

}
