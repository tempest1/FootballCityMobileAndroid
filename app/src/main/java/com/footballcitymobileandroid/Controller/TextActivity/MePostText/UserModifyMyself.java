package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smartlab on 16/5/16.
 */
public class UserModifyMyself extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>> {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private String name;
    int[] a={1,2,3};

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermodifymyself);
        init();
        setSpinner();
    }

    private void init(){
        spinner = (Spinner) findViewById(R.id.spinner);

        editText1= (EditText) findViewById(R.id.modifynamevalue);
        editText2= (EditText) findViewById(R.id.modifyposition);
        editText3= (EditText) findViewById(R.id.modifyatyfield);
        editText4= (EditText) findViewById(R.id.modifyatytime);
        button= (Button) findViewById(R.id.modifyqueren);
        button.setOnClickListener(this);


    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyMyself(name,editText1.getText().toString(),a,editText3.getText().toString(),a, Params.fc_modifyMyself,this);
         }

    private void setSpinner(){
        //数据
        data_list = new ArrayList<>();
        data_list.add("姓名");
        data_list.add("性别");
        data_list.add("生日");
        data_list.add("身高");
        data_list.add("体重");
        data_list.add("照片");
        //适配器
        arr_adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.modifyqueren:
                doit();
                break;
        }
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast=Toast.makeText(this,"修改成功",Toast.LENGTH_LONG);
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
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            name =data_list.get(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
