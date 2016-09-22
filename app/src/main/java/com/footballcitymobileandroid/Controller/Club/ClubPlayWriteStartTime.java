package com.footballcitymobileandroid.Controller.Club;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubPlayWriteStartTime extends Fragment implements View.OnClickListener{
    private View view,dot;
    ArrayAdapter<String> yearAdapter = null;  //年适配器
    ArrayAdapter<String> mouthAdapter = null;  //年适配器

    ArrayAdapter<String> dayAdapter = null;  //年适配器
    Spinner year,mouth,day,hour,minute;  //年
    ArrayAdapter<String> hourAdapter = null;  //小时适配器
    ArrayAdapter<String> minAdapter = null;  //分钟适配器
    private boolean isRuen = true;
    private boolean isTwo = false;

    String y="2016",m="1",d="1",h="00",mi="00";
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

    //分选项值
    private String[] hour1 = new String[] {"00","01","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    //秒选项值
    private String[] min= new String[] {"00","01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23","24","25","26","27","28","29",
            "30","31","32","33","34","35","36","37","38","39",
            "40","41","42","43","44","45","46","47","48","49",
            "50","51","52","53","54","55","56","57","58","59", };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_play_write_start_time, container,false);
        init();

        return view;

    }
    private  void init()
    {
//        dot=view.findViewById(R.id.v_dot0);
//        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
        Log.i("sporttime",AddSport.startTime);
        //绑定适配器和值
        yearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);
        year = (Spinner)view.findViewById(R.id.year);
        year.setAdapter(yearAdapter);
        year.setSelection(0,true);  //设置默认选中项，此处为默认选中第4个值

        mouthAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mouths);

        mouth = (Spinner)view.findViewById(R.id.month);
        mouth.setAdapter(mouthAdapter);
        mouth.setSelection(0,true);

        dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[3]);

        day = (Spinner)view.findViewById(R.id.day);
        day.setAdapter(dayAdapter);
        day.setSelection(0,true);
        // year.getSelectedItem().toString();
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //为year添加监听器，判断是否为闰年。
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mYear = years[position];
                y=mYear;
                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                Log.i("sporttime",AddSport.startTime);
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
                m= (String) mouth.getSelectedItem();
                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                Log.i("sporttime",AddSport.startTime);
                if(i==2){
                    if(isRuen) {
                        dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[0]);
                        day.setAdapter(dayAdapter);
                        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                d= (String) day.getSelectedItem();
                                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                                Log.i("sporttime",AddSport.startTime);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else{
                        dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[1]);
                        day.setAdapter(dayAdapter);
                        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                d= (String) day.getSelectedItem();
                                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                                Log.i("sporttime",AddSport.startTime);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }else if(i==1||i==3||i==5||i==7||i==8||i==10||i==12){
                    dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[3]);
                    day.setAdapter(dayAdapter);
                    day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            d= (String) day.getSelectedItem();
                            AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                            Log.i("sporttime",AddSport.startTime);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else{
                    dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[2]);
                    day.setAdapter(dayAdapter);
                    day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            d= (String) day.getSelectedItem();
                            AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                            Log.i("sporttime",AddSport.startTime);

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

        //绑定适配器和值
        hourAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, hour1);
        hour = (Spinner)view.findViewById(R.id.hour);
        hour.setAdapter(hourAdapter);
        hour.setSelection(0,true);  //
        hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                h= (String) hour.getSelectedItem();
                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                Log.i("sporttime",AddSport.startTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        minAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, min);
        minute = (Spinner)view.findViewById(R.id.minute);
        minute.setAdapter(minAdapter);
        minute.setSelection(0,true);
        minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mi= (String) minute.getSelectedItem();
                AddSport.startTime=y+"-"+m+"-"+d+" "+h+":"+mi+":"+"00";
                Log.i("sporttime",AddSport.startTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

        }
    }
}
