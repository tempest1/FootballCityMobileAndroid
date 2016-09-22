package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.RefuseAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.NomiMembs;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChallengeEndMatchInfo extends FragmentActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<NomiMembs>> {

    ClubList clubList;
    AranaMatchs aranaMatchses;
    List<NomiMembs> nomiMembs;
    private FragmentManager fragmentManager;
    //1
    private ChallengeEndMatchInfoFragment challengeEndMatchInfoFragment;
    private LoadingDialog loadingDialog;


    private ChallengeEndMatchplayerFragment challengeEndMatchplayerFragment;

    private RadioButton radio_info;
    private RadioButton radio_join;

    Button detail_back,detail_title;
    TextView detail_title_center,recode,best,look_place,compain,vote,setname;

    private RelativeLayout list;
    List<String> data=new ArrayList<>();
    RefuseAdapter adapter;
    Intent intent = new Intent();


    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.head);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            switch (checkId) {
                case R.id.radio_join:
                    setTabSelection(0);
                    selcet();
                    radio_join.setCompoundDrawables(null, null, null, rightDrawable);
                    break;
                case R.id.radio_info:
                    setTabSelection(1);
                    selcet();
                    radio_info.setCompoundDrawables(null, null, null, rightDrawable);

                    break;


            }
        }
    };


    private void selcet()
    {
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.agenull);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_info.setCompoundDrawables(null, null, null, rightDrawable);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_join.setCompoundDrawables(null, null, null, rightDrawable);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_info_new);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();

    }
//
//    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//            //  LogUtils.e(clubTest.getClubname());
//            showInfoDetail(position);
//        }
//    };
//    private void showInfoDetail(int index) {
//        if (index==0)
//        {
//            list.setVisibility(View.GONE);
//            intent = new Intent();
//            intent.setClass(this, ChallengeEndMatchInfoMessage.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);        }else if(index==1)
//        {
//            intent = new Intent();
////		Bundle bundle=new Bundle();
////		bundle.putString("type", "forget");
////		intent.putExtras(bundle);
//            intent.setClass(this, ChallengeCompain.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//            list.setVisibility(View.GONE);
//
//        }else if(index==2){
//            intent = new Intent();
//            intent.setClass(this, NormalMatchReStartSet.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//        }else if(index==3){
//            list.setVisibility(View.GONE);
//            intent = new Intent();
//            intent.setClass(this, ChallengeEndMatchInfoGreatNumber.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//
//        }
//    }

    private void init()
    {
        loadingDialog = new LoadingDialog(this, R.drawable.loading);
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);

        radio_info = (RadioButton) findViewById(R.id.radio_info);
        radio_join = (RadioButton) findViewById(R.id.radio_join);
        radio_tab.setOnCheckedChangeListener(listener);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配请求");

        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        list=(RelativeLayout)findViewById(R.id.list);

        recode=(TextView)findViewById(R.id.recode);
        recode.setOnClickListener(this);

        best=(TextView)findViewById(R.id.best);
        best.setOnClickListener(this);

        look_place=(TextView)findViewById(R.id.look_place);
        look_place.setOnClickListener(this);

        compain=(TextView)findViewById(R.id.compain);
        compain.setOnClickListener(this);

        setname=(TextView)findViewById(R.id.setname);
        setname.setOnClickListener(this);

        vote=(TextView)findViewById(R.id.vote);
        vote.setOnClickListener(this);

    }

    private void inData()
    {

        setTabSelection(1);


    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        String FRAGMENT_TAG_HOME = "home";
        String FRAGMENT_TAG_INFO = "infoPage";
        String FRAGMENT_TAG_FORUM = "forum";
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (challengeEndMatchplayerFragment == null) {
                    challengeEndMatchplayerFragment = new ChallengeEndMatchplayerFragment(clubList,aranaMatchses);
                    transaction.add(R.id.frame, challengeEndMatchplayerFragment, FRAGMENT_TAG_HOME);

                }
                transaction.show(challengeEndMatchplayerFragment);
                radio_join.setChecked(true);

                break;
            case 1:
                if (challengeEndMatchInfoFragment == null) {
                    challengeEndMatchInfoFragment = new ChallengeEndMatchInfoFragment(clubList,aranaMatchses);
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, challengeEndMatchInfoFragment, FRAGMENT_TAG_INFO);

                }
                transaction.show(challengeEndMatchInfoFragment);
                radio_info.setChecked(true);
                break;


            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (challengeEndMatchplayerFragment != null) {
            transaction.hide(challengeEndMatchplayerFragment);
        }
        if (challengeEndMatchInfoFragment != null) {
            transaction.hide(challengeEndMatchInfoFragment);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.detail_title:
                if (list.getVisibility()==View.GONE) {
                    list.setVisibility(View.VISIBLE);
                }else {
                    list.setVisibility(View.GONE);
                }
                break;
            case R.id.recode:
                list.setVisibility(View.GONE);
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchInfoMessage.class);
                list.setVisibility(View.GONE);
                Bundle bundle=new Bundle();

                bundle.putSerializable("AranaMatchs", aranaMatchses);
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.best:
                list.setVisibility(View.GONE);
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchInfoGreatNumber.class);
                list.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.look_place:
                intent = new Intent();
                intent.setClass(this, ChallenageMatchReStartSet.class);
                doits();
                list.setVisibility(View.GONE);

                break;
            case R.id.compain:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeCompain.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                list.setVisibility(View.GONE);
                break;

            case R.id.setname:   //提名名单
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchSetName.class);
                doits();
                list.setVisibility(View.GONE);

                break;

            case R.id.vote:   //投票
                doit();

                break;

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doits() {

        AppAction appAction = Factory.createAppActionImpl(this);
        loadingDialog.show();
        appAction.fc_checkArenaMatchMemb(aranaMatchses.getAreanaMatchID(), clubList.getClubID(), Params.fc_checkArenaMatchMemb, Membslistener);
    }
    List<AranaMatchMembs> aranaMatchMembs;


    //查看签到人员
    public ActionCallBackListener<BaseEntity<AranaMatchMembs>> Membslistener = new ActionCallBackListener<BaseEntity<AranaMatchMembs>>() {
        @Override
        public void onSuccess(BaseEntity<AranaMatchMembs> data) {
            aranaMatchMembs=data.getResponse().getMatchMembs();

            Bundle bundle=new Bundle();
            bundle.putSerializable("clublist",clubList);
            bundle.putSerializable("AranaMatchs", (Serializable) aranaMatchses);
            bundle.putSerializable("AranaMatchMembs", (Serializable) aranaMatchMembs);

//                bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            loadingDialog.dismiss();
        }

        @Override
        public void onFailure(String e_Type, String e_Msg) {
            getToast(e_Msg);loadingDialog.dismiss();
        }
    };


    void getToast(String str) {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.show();
    }


    //检查提名
    private void doit(){
        AppAction appAction=Factory.createAppActionImpl(this);
        appAction.fc_checkNominationMemb(aranaMatchses.getAreanaMatchID(),clubList.getClubID(),Params.fc_checkNominationMemb,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<NomiMembs> data) {
        nomiMembs=data.getResponse().getNomiMembs();
        intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
        intent.setClass(this, ChallengeCompain.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("clublist",clubList);
        bundle.putSerializable("AranaMatchs", (Serializable) aranaMatchses);
        bundle.putSerializable("AranaMatchMembs", (Serializable) aranaMatchMembs);
        bundle.putSerializable("nomiMembs", (Serializable) nomiMembs);
//                bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        list.setVisibility(View.GONE);
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

//    private void doit(){
//        AppAction appAction=Factory.createAppActionImpl(this);
//        appAction.fc_checkArenaMatchMemb(aranaMatchses.getAreanaMatchID(),clubList.getClubID(),Params.fc_checkArenaMatchMemb,this);
//    }
//
//    /**
//     * 处理成功
//     *
//     * @param data 返回数据
//     */
//    @Override
//    public void onSuccess(BaseEntity<AranaMatchMembs> data) {
////        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
////        toast.show();
//        intent = new Intent();
////		Bundle bundle=new Bundle();
////		bundle.putString("type", "forget");
////		intent.putExtras(bundle);
//        Bundle bundle=new Bundle();
//
//        bundle.putSerializable("AranaMatchs", aranaMatchses);
//        bundle.putSerializable("clublist",clubList);
//        intent.putExtras(bundle);
//        intent.setClass(this, ChallengeEndMatchSetName.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//        list.setVisibility(View.GONE);
//    }
//
//    /**
//     * 请求失败
//     *
//     * @param e_Type 错误码
//     * @param e_Msg  错误详情
//     */
//    @Override
//    public void onFailure(String e_Type, String e_Msg) {
//        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
}
