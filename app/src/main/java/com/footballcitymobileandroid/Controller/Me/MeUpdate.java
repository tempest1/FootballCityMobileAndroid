package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeUpdate extends Activity implements View.OnClickListener {
    Button detail_back;
    TextView detail_title_center;
    App app;
    TextView old,now,nowcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_update);
        init();
    }

    void init()
    {
        app= (App) getIntent().getSerializableExtra("app");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("更新");
        old= (TextView) findViewById(R.id.oldcode);
        now= (TextView) findViewById(R.id.nowcode);
        nowcode= (TextView) findViewById(R.id.nowdes);
        try {
            old.setText("当前版本:"+getVersionName());
            now.setText("最新版本:"+app.getVerName());
            nowcode.setText("更新说明:"+app.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:

                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);

                break;
        }
    }
    private String getVersionName() throws Exception
    {
// 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
// getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }



}
