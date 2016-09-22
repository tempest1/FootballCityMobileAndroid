package com.footballcitymobileandroid.Controller.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.media.MediaService;
import com.alibaba.wxlib.util.SysUtil;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.openimui.sample.InitHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by smartlab on 16/5/7.
 * 实现对全局变量和对象的引用   用户信息对象 接口对象的使用
 */

public class MainApplication extends android.app.Application{

    private static final String TAG = "DemoApplication";
    public static final String NAMESPACE = "openimdemo";
    //云旺OpenIM的DEMO用到的Application上下文实例
    private static Context sContext;
    public static Context getContext(){
        return sContext;
    }




    private static userInfo userInfo;
    private static Club club;
    private static List<ClubList> clubList;

    private static ClubList clubLists;

    public static String BeanDefinitionReaderSid;
    public static String FindClub="";
    public static SharedPreferences SP_Sign;//签到
    public static String BTN_SP_Sign="";//签到
    public static List<String>StaffType=new ArrayList<>();
    public static List<String> mEmoticons = new ArrayList<>();
    public static Map<String, Integer> mEmoticonsId = new HashMap< >();
    public static List<String> mEmoticons_Zem = new ArrayList<>();
    public static List<String> mEmoticons_Zemoji = new ArrayList<>();
    public static String dialog="";
    public static int[] week;
    public static int Height;
    public static int Width;


//    public static List<Integer> PK_REL_Number=new ArrayList<>();
//    public static List<Integer> PK_Start_Number=new ArrayList<>();

    public static int[] getTop=new int[14];
    public static int[] getLeft=new int[14];

    //球员声价
    public static String PLAYERPRICE="0";

    //预选球员编号
    public static int[] PK_REL_NUMBER;
    //预选球员
    public static List<String> PK_REL_NUMBER_SET = new ArrayList<>();


    //球员编号
    public static int[] PK_NUMBER;
    //球员
    public static List<String> PK_NUMBER_SET = new ArrayList<>();


    //球员选择
    public static int[] CHOOSE_NUMBER;

    //赛季
    public static String SEASON_NAME="";

    //选择球员

    public static double []getNewTop=new double[14];
    public static double []getNewLef=new double[14];


    //球员提名
    public static int[] NUMBER_SETNAME;

    public static SharedPreferences sharedPreferences;

    public static int RULE=1;  //规则 1: 8v8 2:10v10 3:11v11

    @Override
    public void onCreate() {
        super.onCreate();

        //todo Application.onCreate中，首先执行这部分代码，以下代码固定在此处，不要改动，这里return是为了退出Application.onCreate！！！
        if(mustRunFirstInsideApplicationOnCreate()){
            //todo 如果在":TCMSSevice"进程中，无需进行openIM和app业务的初始化，以节省内存
            return;
        }

        //初始化云旺SDK
        InitHelper.initYWSDK(this);

        //初始化反馈功能(未使用反馈功能的用户无需调用该初始化)

        //InitHelper.initFeedBack(this);

        //初始化多媒体SDK，小视频和阅后即焚功能需要使用多媒体SDK
        AlibabaSDK.asyncInit(this, new InitResultCallback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "-----initTaeSDK----onSuccess()-------" );
                MediaService mediaService = AlibabaSDK.getService(MediaService.class);
                mediaService.enableHttpDNS(); //果用户为了避免域名劫持，可以启用HttpDNS
                mediaService.enableLog(); //在调试时，可以打印日志。正式上线前可以关闭
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e(TAG, "-------onFailure----msg:" + msg + "  code:" + code);
            }
        });








        SP_Sign = getSharedPreferences("sign",
                Context.MODE_PRIVATE);
        // 初始化日志打印级别，及保存的路径
        LogUtils.isSaveLog = false;
        LogUtils.ROOT = Params.DATA_PATH;//日志根目录
        //表情图片
        initEomtFace();

        //获得手机分变率


        float s;
        s= (float) (1.0*10/24);
        LogUtils.e(""+s);

    }

    public static userInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(userInfo userInfo) {
        MainApplication.userInfo = userInfo;
    }
    public static void setClub(Club club){
        MainApplication.club=club;
    }
    public static Club getClub(){
        return club;
    }

    public static void setClubList(List<ClubList> clubList) {
        MainApplication.clubList = clubList;
    }

    public static List<ClubList> getClubList() {
        return clubList;
    }

    public static void setClubLists(ClubList clubLists) {
        MainApplication.clubLists = clubLists;
    }

    public static ClubList getClubLists() {
        return clubLists;
    }

    public static SharedPreferences getPreferences() {
        return SP_Sign;
    }

    public static synchronized String getBeanDefinitionReaderSid() {
        return BeanDefinitionReaderSid;
    }

    public static synchronized void setBeanDefinitionReaderSid(String beanDefinitionReaderSid) {
        BeanDefinitionReaderSid = beanDefinitionReaderSid;
    }
    public static void loop(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void initEomtFace() {
        for (int i = 1; i < 64; i++) {
            String emoticonsName = "[zem" + i + "]";
            int emoticonsId = getResources().getIdentifier("zem" + i,
                    "mipmap", getPackageName());
            mEmoticons.add(emoticonsName);
            mEmoticons_Zem.add(emoticonsName);
            mEmoticonsId.put(emoticonsName, emoticonsId);
        }

        for (int i = 1; i < 59; i++) {
            String emoticonsName = "[zemoji" + i + "]";
            int emoticonsId = getResources().getIdentifier("zemoji_e" + i,
                    "mipmap", getPackageName());
            mEmoticons.add(emoticonsName);
            mEmoticons_Zemoji.add(emoticonsName);
            mEmoticonsId.put(emoticonsName, emoticonsId);
        }
    }

    public static int getHeight() {
        return Height;
    }

    public static void setHeight(int height) {
        Height = height;
    }

    public static int getWidth() {
        return Width;
    }

    public static void setWidth(int width) {
        Width = width;
    }

    /**
     * 延时执行操作
     *
     * @param layout 需要延时的layout
     */
    public static void runDelay(final PullToRefreshLayout layout) {
        TimerTask task = new TimerTask() {

            public void run() {
                //execute the task
//                layout.autoRefresh();           实现自动刷新
            }

        };
        Timer timer = new Timer();

        timer.schedule(task, 600);
    }


    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        SysUtil.setApplication(this);
        sContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(sContext);
    }



}
