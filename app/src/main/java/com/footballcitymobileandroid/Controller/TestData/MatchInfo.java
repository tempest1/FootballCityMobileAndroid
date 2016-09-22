package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class MatchInfo implements Serializable {
    public static List<MatchNormal> InvestInfoDate()
    {
        List<MatchNormal>listInfo=new ArrayList<>();

        MatchNormal player1=new MatchNormal();
        player1.setMeClub("我的1");
        player1.setYouClub("你的1");
        player1.setTime("2001-1-1");
        player1.setType("未开始");

        MatchNormal player2=new MatchNormal();
        player2.setMeClub("我的2");
        player2.setYouClub("你的2");
        player2.setTime("2001-1-1");
        player2.setType("已开始");

        MatchNormal player3=new MatchNormal();
        player3.setMeClub("我的3");
        player3.setYouClub("你的3");
        player3.setTime("2001-1-1");
        player3.setType("已结束");


        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);

        return listInfo;
    }
}
