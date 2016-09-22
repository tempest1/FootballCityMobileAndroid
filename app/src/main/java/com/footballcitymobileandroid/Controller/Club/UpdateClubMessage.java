package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/31.
 */
public class UpdateClubMessage extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Wrong>> {

     Button detail_back,detail_title;
    private Spinner year,mouth,day;  //年

    ClubList clubList;
    String y="2016",m="1",d="1";
    String createTime;
    private boolean isRuen = true;
    private boolean isTwo = false;

    ArrayAdapter<String> yearAdapter = null;  //年适配器
    ArrayAdapter<String> mouthAdapter = null;  //年适配器

    ArrayAdapter<String> dayAdapter = null;  //年适配器

    //年选项值
    private String[] years = new String[] {"2016","2017","2018","2019"};
    //月选项值
    private String[] mouths= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
    //日选项值
    private String[][] days = new String[][] {
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_club_message);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        Log.i("clubnamedddddd",clubList.getClubName());
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);
        //绑定适配器和值
        yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        year = (Spinner) findViewById(R.id.year);
        year.setAdapter(yearAdapter);
        year.setSelection(0,true);  //设置默认选中项，此处为默认选中第4个值

        mouthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mouths);

        mouth = (Spinner) findViewById(R.id.month);
        mouth.setAdapter(mouthAdapter);
        mouth.setSelection(0,true);

        dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days[3]);

        day = (Spinner) findViewById(R.id.day);
        day.setAdapter(dayAdapter);
        day.setSelection(0,true);


        createTime="2016"+"-"+"1"+"-"+"1";
        Log.i("timessss",createTime);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //为year添加监听器，判断是否为闰年。
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mYear = years[position];
                y= (String) year.getSelectedItem();
                createTime=y+"-"+"1"+"-"+"1";
                Log.i("timessss",createTime);
                int i = Integer.parseInt(mYear);
                if((i%4==0&&i%100!=0)||(i%400==0)){
                    isRuen = true;

                }else{
                    isRuen = false;

                }
                mouth.setSelection(0,true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mouth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //为month添加监听器，根据月份以及是否为闰年来为day添加适配器
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = Integer.parseInt(mouths[position]);
                m=(String) mouth.getSelectedItem();
                if(i==2){
                    if(isRuen) {
                        dayAdapter = new ArrayAdapter<String>(UpdateClubMessage.this, android.R.layout.simple_spinner_item, days[0]);
                        day.setAdapter(dayAdapter);
                        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.i("day", (String) day.getSelectedItem());
                                d=(String) day.getSelectedItem();
                                createTime=y+"-"+m+"-"+d;
                                Log.i("timessss",createTime);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else{
                        dayAdapter = new ArrayAdapter<String>(UpdateClubMessage.this, android.R.layout.simple_spinner_item, days[1]);
                        day.setAdapter(dayAdapter);
                        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.i("day", (String) day.getSelectedItem());
                                d=(String) day.getSelectedItem();
                                createTime=y+"-"+m+"-"+d;
                                Log.i("timessss",createTime);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }else if(i==1||i==3||i==5||i==7||i==8||i==10||i==12){
                    dayAdapter = new ArrayAdapter<String>(UpdateClubMessage.this, android.R.layout.simple_spinner_item, days[3]);
                    day.setAdapter(dayAdapter);
                    day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("day", (String) day.getSelectedItem());
                            d=(String) day.getSelectedItem();
                            createTime=y+"-"+m+"-"+d;
                            Log.i("timessss",createTime);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else{
                    dayAdapter = new ArrayAdapter<String>(UpdateClubMessage.this, android.R.layout.simple_spinner_item, days[2]);
                    day.setAdapter(dayAdapter);
                    day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("day", (String) day.getSelectedItem());
                            d=(String) day.getSelectedItem();
                            createTime=y+"-"+m+"-"+d;
                            Log.i("timessss",createTime);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //year.getSelectedItem().toString();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;

            case R.id.detail_title:
                doit();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyClub(clubList.getClubID(),"createTime",createTime, Params.fc_modifyClub,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Wrong> data) {
        Toast toast = Toast.makeText(this, "修改日期成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
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
