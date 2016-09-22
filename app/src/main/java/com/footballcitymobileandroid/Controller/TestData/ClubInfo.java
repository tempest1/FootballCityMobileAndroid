package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/19.
 */
 public   class ClubInfo {

    public static List<ClubTest> ClubInfoData()
    {
        List<ClubTest>listInfo=new ArrayList<>();

        ClubTest club1=new ClubTest();
        club1.setClubname("一号球队");
        club1.setPaice(1000);

        ClubTest club2=new ClubTest();
        club2.setClubname("二号球队");
        club2.setPaice(900);

        ClubTest club3=new ClubTest();
        club3.setClubname("三号球队");
        club3.setPaice(10000);

        listInfo.add(club1);
        listInfo.add(club2);
        listInfo.add(club3);
        listInfo.add(club3);

        for (int i=0;i<listInfo.size();i++)
        {
            LogUtils.e(listInfo.get(i).getClubname());
        }

        return listInfo;
    }

}
