package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/31.
 */
public class ClubChallengeInfo {

    public static List<ClubChallenge> ClubChallengeInfoData()
    {
        List<ClubChallenge>listInfo=new ArrayList<>();

        ClubChallenge club1=new ClubChallenge();
        club1.setMeclub("我的1");
        club1.setYouclub("你的1");

        ClubChallenge club2=new ClubChallenge();
        club2.setMeclub("我的2");
        club2.setYouclub("你的2");

        ClubChallenge club3=new ClubChallenge();
        club3.setMeclub("我的3");
        club3.setYouclub("你的3");

        listInfo.add(club1);
        listInfo.add(club2);
        listInfo.add(club3);


        return listInfo;
    }
}
