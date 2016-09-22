package com.footballcitymobileandroid.BLL.Util.CustomView;

import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;

import java.util.Comparator;

/**
 * Created by zhoudi on 16/7/28.
 */
public class ComparatorPlace implements Comparator {

    public int compare(Object arg0, Object arg1) {
        PlayerRankings user0=(PlayerRankings)arg0;
        PlayerRankings user1=(PlayerRankings)arg1;

        //首先比较年龄，如果年龄相同，则比较名字

        int flag=user0.getRankings().compareTo(user1.getRankings());
        if(flag==0){
            return flag;
        }else{
            return user0.getPlayerName().compareTo(user1.getPlayerName());
        }
    }
}
