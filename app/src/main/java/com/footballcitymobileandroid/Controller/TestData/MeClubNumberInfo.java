package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/26.
 */
public class MeClubNumberInfo {
    public static List<MeClubNumbers> NumberInfoData()
    {
        List<MeClubNumbers>listInfo=new ArrayList<>();

        MeClubNumbers player1=new MeClubNumbers();
        player1.setName("球员1");
        player1.setAge("42");
        player1.setGoodplace("前锋");
        player1.setMoney("6.1");
        player1.setTime("2012-1-1");


        MeClubNumbers player2=new MeClubNumbers();
        player2.setName("球员2");
        player2.setAge("43");
        player2.setGoodplace("前锋");
        player2.setMoney("6.12");
        player2.setTime("2012-1-3");

        MeClubNumbers player3=new MeClubNumbers();
        player3.setName("球员3");
        player3.setAge("22");
        player3.setGoodplace("前锋");
        player3.setMoney("6.21");
        player3.setTime("2012-1-5");

        MeClubNumbers player4=new MeClubNumbers();
        player4.setName("球员4");
        player4.setAge("22");
        player4.setGoodplace("前锋");
        player4.setMoney("6.21");
        player4.setTime("2012-1-5");

        MeClubNumbers player5=new MeClubNumbers();
        player5.setName("球员5");
        player5.setAge("22");
        player5.setGoodplace("前锋");
        player5.setMoney("6.21");
        player5.setTime("2012-1-5");


        MeClubNumbers player6=new MeClubNumbers();
        player6.setName("球员6");
        player6.setAge("22");
        player6.setGoodplace("前锋");
        player6.setMoney("6.21");
        player6.setTime("2012-1-5");

        MeClubNumbers player7=new MeClubNumbers();
        player7.setName("球员7");
        player7.setAge("22");
        player7.setGoodplace("前锋");
        player7.setMoney("6.21");
        player7.setTime("2012-1-5");


        MeClubNumbers player8=new MeClubNumbers();
        player8.setName("球员8");
        player8.setAge("22");
        player8.setGoodplace("前锋");
        player8.setMoney("6.21");
        player8.setTime("2012-1-5");


        MeClubNumbers player9=new MeClubNumbers();
        player9.setName("球员9");
        player9.setAge("22");
        player9.setGoodplace("前锋");
        player9.setMoney("6.21");
        player9.setTime("2012-1-5");

        MeClubNumbers player10=new MeClubNumbers();
        player10.setName("球员10");
        player10.setAge("22");
        player10.setGoodplace("前锋");
        player10.setMoney("6.21");
        player10.setTime("2012-1-5");

        MeClubNumbers player11=new MeClubNumbers();
        player11.setName("球员11");
        player11.setAge("22");
        player11.setGoodplace("前锋");
        player11.setMoney("6.21");
        player11.setTime("2012-1-5");

        MeClubNumbers player12=new MeClubNumbers();
        player12.setName("球员12");
        player12.setAge("22");
        player12.setGoodplace("前锋");
        player12.setMoney("6.21");
        player12.setTime("2012-1-5");

        MeClubNumbers player13=new MeClubNumbers();
        player13.setName("球员13");
        player13.setAge("22");
        player13.setGoodplace("前锋");
        player13.setMoney("6.21");
        player13.setTime("2012-1-5");

        MeClubNumbers player14=new MeClubNumbers();
        player14.setName("球员14");
        player14.setAge("22");
        player14.setGoodplace("前锋");
        player14.setMoney("6.21");
        player14.setTime("2012-1-5");


        MeClubNumbers player15=new MeClubNumbers();
        player15.setName("球员15");
        player15.setAge("22");
        player15.setGoodplace("前锋");
        player15.setMoney("6.21");
        player15.setTime("2012-1-5");

        for (int i=0;i<listInfo.size();i++)
        {
            LogUtils.e(listInfo.get(i).getName());
        }
        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);
        listInfo.add(player4);
        listInfo.add(player5);
        listInfo.add(player6);
        listInfo.add(player7);
        listInfo.add(player8);
        listInfo.add(player9);
        listInfo.add(player10);
        listInfo.add(player11);
        listInfo.add(player12);
        listInfo.add(player13);
        listInfo.add(player14);
        listInfo.add(player15);


        return listInfo;
    }
}
