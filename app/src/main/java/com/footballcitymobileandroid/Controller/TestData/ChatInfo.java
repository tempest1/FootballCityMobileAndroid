package com.footballcitymobileandroid.Controller.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChatInfo  {

    public static List<Chat> ChatInfoDate()
    {
        List<Chat>listInfo=new ArrayList<>();

        Chat player1=new Chat();
        player1.setContext("内容1");
        player1.setTime("1-1");
        player1.setName("1");

        Chat player2=new Chat();
        player2.setContext("内容2");
        player2.setTime("1-2");
        player2.setName("2");

        Chat player3=new Chat();
        player3.setContext("内容3");
        player3.setTime("1-3");
        player3.setName("1");


        listInfo.add(player1);
        listInfo.add(player2);
        listInfo.add(player3);

        return listInfo;
    }
}
