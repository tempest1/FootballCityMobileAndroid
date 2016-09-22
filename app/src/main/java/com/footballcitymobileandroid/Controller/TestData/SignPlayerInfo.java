package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class SignPlayerInfo {

    public static List<SignPlayer> SignPlayerInfoData()
    {
        List<SignPlayer>listInfo=new ArrayList<>();

        SignPlayer player1=new SignPlayer();
        player1.setName("成员1");
        player1.setClub("一号俱乐部");

        SignPlayer player2=new SignPlayer();
        player2.setName("成员2");
        player2.setClub("一号俱乐部");

        SignPlayer player3=new SignPlayer();
        player3.setName("成员3");
        player3.setClub("2号俱乐部");
        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);



        return listInfo;
    }
}
