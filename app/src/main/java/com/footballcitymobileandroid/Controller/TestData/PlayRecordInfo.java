package com.footballcitymobileandroid.Controller.TestData;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class PlayRecordInfo {
    public static List<PlayRecord> PlayRecordInfoData()
    {
        List<PlayRecord>listInfo=new ArrayList<>();

        PlayRecord record1=new PlayRecord();
        record1.setClub_me("我的俱乐部");
        record1.setClub_you("你的");
        record1.setPoint_me(1);
        record1.setPint_you(2);
        record1.setDate("5月13日");
        record1.setPayways("8v8");
        record1.setPlace("PA社");

        PlayRecord record2=new PlayRecord();
        record2.setClub_me("我的俱乐部");
        record2.setClub_you("你的");
        record2.setPoint_me(11);
        record2.setPint_you(0);
        record2.setDate("5月17日");
        record2.setPayways("8v8");
        record2.setPlace("PB社");

        PlayRecord record3=new PlayRecord();
        record3.setClub_me("我的俱乐部");
        record3.setClub_you("你的");
        record3.setPoint_me(3);
        record3.setPint_you(2);
        record3.setDate("5月13日");
        record3.setPayways("8v8");
        record3.setPlace("PC社");



        listInfo.add(record1);
        listInfo.add(record2);
        listInfo.add(record3);


        return listInfo;
    }
}
