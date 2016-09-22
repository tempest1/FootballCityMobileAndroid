package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ChattingAdapter;
import com.footballcitymobileandroid.Controller.TestData.Chat;
import com.footballcitymobileandroid.Controller.TestData.ChatInfo;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class Chatting extends Activity implements View.OnClickListener{
    TextView detail_title_center;

    Button detail_back;

    private ListView list;
    ChattingAdapter adapter;
    List<Chat> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting);
        init();
    }

    private void init()
    {
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("聊天室");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        list=(ListView)findViewById(R.id.pull_list);

        data= ChatInfo.ChatInfoDate();
        adapter=new ChattingAdapter(this,data);

        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}
