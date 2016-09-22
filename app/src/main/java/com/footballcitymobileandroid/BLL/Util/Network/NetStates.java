package com.footballcitymobileandroid.BLL.Util.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by smartlab on 16/8/3.
 */
public class NetStates extends BroadcastReceiver {
    private BRInteraction brInteraction;
    private Context context = null;
    private NetStates receiver;
    public NetStates() {
    }

    public NetStates(Context context){
        this.context = context;
        this.receiver = this;
    }
    private ConnectivityManager mConnectivityManager;

    //  网络状态信息
    private NetworkInfo netInfo;


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = mConnectivityManager.getActiveNetworkInfo();
            if(netInfo != null && netInfo.isAvailable()) {

                //  网络连接
                String name = netInfo.getTypeName();
                final int type = netInfo.getType();
                if(type == ConnectivityManager.TYPE_WIFI){
//                    networks.networksss=30;
                    //  WiFi网络
                    Toast.makeText(context, "当前为wifi网络",Toast.LENGTH_LONG).show();
                }else if(type == ConnectivityManager.TYPE_MOBILE){
//                    networks.networksss=30;
                    //  移动网络连接
//                    Toast.makeText(context,name + "网络下，将会产生流量费",Toast.LENGTH_LONG).show();
                    Toast.makeText(context,"当前为移动网络",Toast.LENGTH_LONG).show();
                }
            } else {
//                networks.networksss=0;
                //  网络断开
//                Util.setNetworkMethod(context);
                Toast.makeText(context,"当前无网络连接",Toast.LENGTH_LONG).show();


            }
        }
    }

    public interface BRInteraction {
        public void setText(String content);
    }

    public void setBRInteractionListener(BRInteraction brInteraction) {
        this.brInteraction = brInteraction;
    }
    public void registerReceiver(){
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, filter);
    }
}
