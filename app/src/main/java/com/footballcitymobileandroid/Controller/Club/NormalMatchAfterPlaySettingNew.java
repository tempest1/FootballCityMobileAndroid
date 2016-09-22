package com.footballcitymobileandroid.Controller.Club;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.BaseDialog;
import com.footballcitymobileandroid.Controller.Base.BaseChangeActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.DeployDetail;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/31.
 */
public class NormalMatchAfterPlaySettingNew extends BaseChangeActivity implements View.OnTouchListener,View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    private Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number10,number11,number12,number13,number14,sure;
    private BaseDialog mBackDialog;


    List<Button> number=new ArrayList<>();

    List<MatchMemb> matchMemb;
    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_after_play_setting_new);
        init();
    }
    private void initBackDialog() {
        mBackDialog = BaseDialog.getDialog(NormalMatchAfterPlaySettingNew.this, "提示",
                "确认要部署完成了么?", "确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        for (int i=0;i<MainApplication.PK_NUMBER_SET.size();i++) {
                            LogUtils.e(""+MainApplication.getNewLef[i]);

                            LogUtils.e(""+MainApplication.getNewTop[i]);

                            DeployDetail deployDetail=new DeployDetail();
                            deployDetail.setCoordX(""+MainApplication.getNewLef[i]);
                            deployDetail.setCoordY(""+MainApplication.getNewTop[i]);
                            deployDetail.setIs_main(""+DeployDetail.ISMAIN.get(i));
                            deployDetail.setUser_id(""+DeployDetail.USERID.get(i));
                            detail.add(deployDetail);
                        }
                        doit();
                        ActivityCollector.finishAll();
                        dialog.dismiss();
                        finish();
                    }
                }, "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        mBackDialog.setButton1Background(R.drawable.btn_default_popsubmit);

    }
    private  void init()
    {
        matchMemb= (List<MatchMemb>) getIntent().getSerializableExtra("matchMemb");
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clubList");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;


        number1=(Button)findViewById(R.id.number1);
        number2=(Button)findViewById(R.id.number2);
        number3=(Button)findViewById(R.id.number3);
        number4=(Button)findViewById(R.id.number4);
        number5=(Button)findViewById(R.id.number5);
        number6=(Button)findViewById(R.id.number6);
        number7=(Button)findViewById(R.id.number7);
        number8=(Button)findViewById(R.id.number8);
        number9=(Button)findViewById(R.id.number9);
        number10=(Button)findViewById(R.id.number10);
        number11=(Button)findViewById(R.id.number11);
        number12=(Button)findViewById(R.id.number12);
        number13=(Button)findViewById(R.id.number13);
        number14=(Button)findViewById(R.id.number14);
        sure=(Button)findViewById(R.id.sure);

        number1.setOnTouchListener(this);
        number2.setOnTouchListener(this);
        number3.setOnTouchListener(this);
        number4.setOnTouchListener(this);
        number5.setOnTouchListener(this);
        number6.setOnTouchListener(this);
        number7.setOnTouchListener(this);
        number8.setOnTouchListener(this);
        number9.setOnTouchListener(this);
        number10.setOnTouchListener(this);
        number11.setOnTouchListener(this);
        number12.setOnTouchListener(this);
        number13.setOnTouchListener(this);
        number14.setOnTouchListener(this);
        sure.setOnClickListener(this);

        number.add(number1);
        number.add(number2);
        number.add(number3);
        number.add(number4);
        number.add(number5);
        number.add(number6);
        number.add(number7);
        number.add(number8);
        number.add(number9);
        number.add(number10);
        number.add(number11);
        number.add(number12);
        number.add(number13);
        number.add(number14);

        int x=0;
        for (int i=0;i<MainApplication.PK_NUMBER_SET.size();i++) {//获得姓名和图片
            number.get(i).setText(MainApplication.PK_NUMBER_SET.get(i));
            x++;
            LogUtils.e(MainApplication.PK_NUMBER_SET.get(i));
        }

        LogUtils.e(""+x);
        for (int i=13;i>x-1;i--)
        {
            number.get(i).setVisibility(View.INVISIBLE);
            LogUtils.e("i="+i);
        }


        set();
        setPlace();

     //   LogUtils.e(""+number1.getTop());
        showPlace();
        initBackDialog();
    }

    private void set()
    {
        setLayout(number1,0,45);
        setLayout(number2,90,45);
        setLayout(number3,180,45);
        setLayout(number4,270,45);
        setLayout(number5,360,45);
        setLayout(number6,450,45);
        setLayout(number7,540,45);
        setLayout(number8,630,45);

        setLayout(number9,0,160);
        setLayout(number10,90,160);
        setLayout(number11,180,160);
        setLayout(number12,270,160);
        setLayout(number13,360,160);
        setLayout(number14,450,160);
        int n=0;
        for (int j=0;j<MainApplication.getTop.length;j++) {
            if (MainApplication.getTop[j] == 0)
            {
                for(int i=0;i<8;i++)
                {
                    MainApplication.getTop[i]=45;
                    MainApplication.getLeft[i]=n;

                    MainApplication.getNewTop[i]=1.00*45/MainApplication.getHeight();
                    MainApplication.getNewLef[i]=1.00*n/MainApplication.getWidth();

                    n=n+90;
                }
                n=0;
                for (int i=0;i<6;i++)
                {
                    MainApplication.getTop[8+i]=160;
                    MainApplication.getLeft[8+i]=n;

                    MainApplication.getNewTop[i+8]=1.00*160/MainApplication.getHeight();
                    MainApplication.getNewLef[i+8]=1.00*n/MainApplication.getWidth();
                    n=n+90;
                }
            }

        }
    }


    private void setPlace()
    {
        for (int i=0;i<number.size();i++)
        {
            setLayout(number.get(i),MainApplication.getLeft[i],MainApplication.getTop[i]);
            LogUtils.e("a"+MainApplication.getLeft[i]);

            LogUtils.e("rx="+MainApplication.getNewLef[i]);
            LogUtils.e("ry="+MainApplication.getNewTop[i]);

        }
    }

    public static void setLayout(View view,int x,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,y, x+margin.width, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

       // overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        int action=event.getAction();
        Log.i("@@@@@@", "Touch:"+action);
        switch(action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + v.getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                Log.i("@@@@@@", "position" + left +", " + top + ", " + right + ", " + bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                LogUtils.e("getTop="+v.getTop());
                LogUtils.e("getLeft="+v.getLeft());
                for(int i=0;i<number.size();i++)
                {
                    if (v==number.get(i))
                    {
                        LogUtils.e("number="+i);
                        MainApplication.getTop[i]=v.getTop();
                        MainApplication.getLeft[i]=v.getLeft();

                        MainApplication.getNewTop[i]=1.00*v.getTop()/MainApplication.getHeight();
                        MainApplication.getNewLef[i]=1.00*v.getLeft()/MainApplication.getWidth();

                        LogUtils.e(""+MainApplication.getNewTop[i]);
                        LogUtils.e(""+MainApplication.getNewLef[i]);

                        Log.i("last", String.valueOf(MainApplication.getNewTop[i]));
                        Log.i("last", String.valueOf(MainApplication.getNewLef[i]));

                        LogUtils.e(""+MainApplication.getNewTop[i]*MainApplication.getHeight());
                        LogUtils.e(""+MainApplication.getNewLef[i]*MainApplication.getWidth());
                    }
                }
                break;
        }
        return false;
    }

    /*
    * 查看位置
     */

    private void showPlace()
    {
        for (int i=0;i<MainApplication.getNewTop.length;i++)
        {
            LogUtils.e(""+MainApplication.getNewTop[i]);
            LogUtils.e(""+MainApplication.getNewLef[i]);
            Log.i("last", String.valueOf(MainApplication.getNewTop[i]));
            Log.i("last", String.valueOf(MainApplication.getNewLef[i]));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sure:
                /*
                   *对话框
                 */
                mBackDialog.show();

                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            mBackDialog.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    List<DeployDetail> detail=new ArrayList<>();

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_deploySport(clubList.getClubID(),sportDetail.getS_record_id(),detail, Params.fc_deploySport,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "部署成功", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }
}



