package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/20.
 */
public class PlayerInfo implements Serializable {
    public static List<PlayerTest> ClubInfoData()
    {
        List<PlayerTest>listInfo=new ArrayList<>();

        PlayerTest player1=new PlayerTest();
        player1.setPlayname("一号球员");
        player1.setPaice(1000);

        PlayerTest player2=new PlayerTest();
        player2.setPlayname("2号球员");
        player2.setPaice(10000);

        PlayerTest player3=new PlayerTest();
        player3.setPlayname("3号球员");
        player3.setPaice(100000);


        for (int i=0;i<listInfo.size();i++)
        {
            LogUtils.e(listInfo.get(i).getPlayname());
        }
        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);
        listInfo.add(player3);

        return listInfo;
    }
}
