package com.footballcitymobileandroid.Controller.Me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetClubPerson extends Fragment implements View.OnClickListener {
    private View view,dot;
    private Spinner year,mouth,day;  //年

    String y="2016",m="1",d="1";
    ArrayAdapter<String> yearAdapter = null;  //年适配器
    ArrayAdapter<String> mouthAdapter = null;  //年适配器

    ArrayAdapter<String> dayAdapter = null;  //年适配器

    private boolean isRuen = true;
    private boolean isTwo = false;

    //年选项值
    private String[] years = new String[] {"2016","2017","2018","2019"};
    //月选项值
    private String[] mouths= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
    //日选项值
//    private String[] days = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String[][] days = new String[][] {
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
            {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_person, container,false);
        init();
        initselect();
        return view;

    }
    private  void init() {
        dot = view.findViewById(R.id.v_dot4);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        year = (Spinner) view.findViewById(R.id.year);
        mouth = (Spinner) view.findViewById(R.id.month);
        day = (Spinner) view.findViewById(R.id.day);

    }
    private void initselect(){
        //绑定适配器和值
        yearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);

        year.setAdapter(yearAdapter);
        year.setSelection(0,true);  //设置默认选中项，此处为默认选中第4个值

        mouthAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mouths);


        mouth.setAdapter(mouthAdapter);
        mouth.setSelection(0,true);

        dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[3]);


        day.setAdapter(dayAdapter);
        day.setSelection(0,true);
        MainApplication.getClub().setCreateTime("2016"+"-"+"1"+"-"+"1");
        MainApplication.getClub().setCityID("1");
        Log.i("timessss",MainApplication.getClub().getCreateTime());
        //year.getSelectedItem().toString();
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //为year添加监听器，判断是否为闰年。
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mYear = years[position];
                y= (String) year.getSelectedItem();

                MainApplication.getClub().setCreateTime(y+"-"+"1"+"-"+"1");
                MainApplication.getClub().setCityID("1");
                Log.i("timessss",MainApplication.getClub().getCreateTime());
                int i = Integer.parseInt(mYear);
                if ((i % 4 == 0 && i % 100 != 0) || (i % 400 == 0)) {
                    isRuen = true;

                } else {
                    isRuen = false;
                }

                mouth.setSelection(0,true);
            }

            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mouth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = Integer.parseInt(mouths[position]);
                Log.i("month", (String) mouth.getSelectedItem());
                m=(String) mouth.getSelectedItem();
                if(i==2){
                    if(isRuen) {
                        dayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days[0]);
                        day.setAdapter(dayAdapter);
                        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.i("day", (String) day.getSelectedItem());
                                d=(String) day.getSelectedItem();
                                Log.i("timessss",y+m+d);
                                MainApplication.getClub().setCreateTime(y+"-"+m+"-"+d);
                                MainApplication.getClub().setCityID("1");
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
                                Log.i("day", (String) day.getSelectedItem());
                                d=(String) day.getSelectedItem();
                                Log.i("timessss",y+m+d);
                                MainApplication.getClub().setCreateTime(y+"-"+m+"-"+d);
                                MainApplication.getClub().setCityID("1");
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
                            Log.i("day", (String) day.getSelectedItem());
                            d=(String) day.getSelectedItem();
                            Log.i("timessss",y+m+d);
                            MainApplication.getClub().setCreateTime(y+"-"+m+"-"+d);
                            MainApplication.getClub().setCityID("1");
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
                            Log.i("day", (String) day.getSelectedItem());
                            d=(String) day.getSelectedItem();
                            Log.i("timessss",y+m+d);
                            MainApplication.getClub().setCreateTime(y+"-"+m+"-"+d);
                            MainApplication.getClub().setCityID("1");
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


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
