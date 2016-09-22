package com.footballcitymobileandroid.BLL.Util.CustomView;

import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;

import java.util.Comparator;

/**
 * Created by zhoudi on 16/7/28.
 */
public class ComparatorRanks implements Comparator {

    public int compare(Object arg0, Object arg1) {
        ClubRankings user0=(ClubRankings)arg0;
        ClubRankings user1=(ClubRankings)arg1;

        //首先比较年龄，如果年龄相同，则比较名字

        int flag=user0.getRankings().compareTo(user1.getRankings());
        if(flag==0){
            return user0.getClubName().compareTo(user1.getClubName());
        }else{
            return flag;
        }
    }
}
