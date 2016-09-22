package com.footballcitymobileandroid.Controller.TestData;

import java.util.List;

/**
 * Created by zhoudi on 16/6/14.
 */
public class MessageInfo {
    public static List<Message>listInfo;
    public static List<Message> MessageInfoData()
    {
/*        List<Message>listInfo=new ArrayList<>();

        Message player1=new Message();
        player1.setMessage("皇家俱乐部发起投票");
        player1.setTime("11:8");

        Message player2=new Message();
        player2.setMessage("皇家俱乐部发起邀请");
        player2.setTime("9:18");

        Message player3=new Message();
        player3.setMessage("皇家俱乐部撤销了6月1日与一举成名的比赛!!!!!!!!!!");
        player3.setTime("11:18");


        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);*/

        return listInfo;
    }
    public static void addMessage(Message msg){
        listInfo.add(msg);

    }
}
