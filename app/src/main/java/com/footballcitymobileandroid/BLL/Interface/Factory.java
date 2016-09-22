package com.footballcitymobileandroid.BLL.Interface;

import android.content.Context;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.google.gson.Gson;

/**
 * Created by smartlab on 16/5/16.
 */
public class Factory {
    public static AppAction createAppActionImpl(Context context) {
        return new AppActionImpl(context);
    }
    public static CommonImp createCommonImp()
    {
        return new CommonImp();
    }

    public static ClubImp createClubImp() {
        return new ClubImp();
    }
    public static SportImp createSportImp() {
        return new SportImp();
    }
    public static JoinImp createJoinImp() {
        return new JoinImp();
    }

    public static Arena1Imp createArena1Imp() {
        return new Arena1Imp();
    }

    public static Arena2Imp createArena2Imp() {
        return new Arena2Imp();
    }

    public static Arena3Imp createArena3Imp() {
        return new Arena3Imp();
    }
    public static Gson createGson()
    {
        return new Gson();
    }
    public static HttpBase createHttpBase()
    {
        return new HttpBase();
    }
}
