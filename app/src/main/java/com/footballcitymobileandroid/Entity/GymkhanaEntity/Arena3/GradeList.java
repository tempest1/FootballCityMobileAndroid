package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3;

/**
 * Created by smartlab on 16/8/3.
 */
public class GradeList {
    private String  clubMembID;
    private String score;          //得分
    private String nomination;     //是否提名

    public void setClubMembID(String clubMembID) {
        this.clubMembID = clubMembID;
    }

    public String getClubMembID() {
        return clubMembID;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getNomination() {
        return nomination;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }
}
