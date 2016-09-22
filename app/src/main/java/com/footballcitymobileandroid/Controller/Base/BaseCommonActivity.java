package com.footballcitymobileandroid.Controller.Base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.R;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Created by zhoudi on 16/6/6.
 */
public abstract class BaseCommonActivity extends Activity {
    public Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication application = (MainApplication) this.getApplication();
        gson = getGson();


        ActivityCollector.addInvestmentActivity(this);
    }

    public Gson getGson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }
    protected abstract void initData();

    protected abstract void initView();

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
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.addInvestmentActivity(this);
    }
}
