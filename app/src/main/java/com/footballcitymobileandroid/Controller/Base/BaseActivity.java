package com.footballcitymobileandroid.Controller.Base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.Network.NetStates;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Created by zhoudi on 16/6/7.
 */
public abstract class BaseActivity extends FragmentActivity {
    public Gson gson;

    //  广播接受者
    private NetStates myReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication application = (MainApplication) this.getApplication();

        myReceiver = new NetStates(this);

        gson = getGson();

        ActivityCollector.addActivity(this);
        ActivityCollector.addActivityNew(this);
    }

    public Gson getGson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }


    public void doBack(View view){
        onBackPressed();
    }

    //    public void showToast(String str){
//        if (!Util.isEmptyString(str)){
//            Util.showToast(this,str, Toast.LENGTH_LONG);
//        }
//    }
//
//    public void showLongToast(String str){
//        if (!Util.isEmptyString(str)){
//            Util.showToast(this,str,Toast.LENGTH_SHORT);
//        }
//    }
    public void close(Activity context){
        context.finish();
        overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
    }

    /**
     * Handler 静态内部类
     * @param <T>
     */
    public static class MyHandler<T> extends Handler {
        Resolve resolve;
        @Override
        public void handleMessage(Message msg) {
            T the = reference.get();
            if (the != null){
                resolve.resolveData(msg);
            }
        }
        private WeakReference<T> reference;

        public MyHandler(T reference,Resolve resolve){
            this.reference = new WeakReference< >(reference);
            this.resolve = resolve;

        }
        public interface Resolve{
            /**
             * 消息回调
             * @param msg Message
             */
            void resolveData(Message msg);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        myReceiver.registerReceiver();
        LogUtils.i("MainContent is start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        ActivityCollector.removeActivityNew(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver); //  注销广播
        super.onStop();
        LogUtils.i("MainContent is onStop");
    }

}
