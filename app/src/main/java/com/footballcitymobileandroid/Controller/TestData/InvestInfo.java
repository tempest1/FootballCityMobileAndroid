package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/27.
 */
public class InvestInfo {
    public static List<Invest> InvestInfoDate()
    {
        List<Invest>listInfo=new ArrayList<>();

        Invest player1=new Invest();
        player1.setClub("1号俱乐部");
        player1.setLeader("1号领队");

        Invest player2=new Invest();
        player2.setClub("2号俱乐部");
        player2.setLeader("2号领队");

        Invest player3=new Invest();
        player3.setClub("3号俱乐部");
        player3.setLeader("3号领队");


        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);

        return listInfo;
    }
}
