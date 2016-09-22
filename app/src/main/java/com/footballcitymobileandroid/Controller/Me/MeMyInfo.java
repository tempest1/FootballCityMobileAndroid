package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.Cache.FileCache.FileUtils;
import com.footballcitymobileandroid.BLL.Util.Cache.PhotoCache.PhotoUtils;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.CustomView.CreateDialog;
import com.footballcitymobileandroid.BLL.Util.CustomView.Dialogs;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhoudi on 16/6/7.
 */
public class MeMyInfo extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>> {
    private static final int DIALOG_DATE_ID = 0;

    //当前系统的年月日
    private int mYear;
    private int mMonth;
    private int mDay;

    final String [] hignList = new String[91];
    final String[] weightList=new String[100];
    final String[] positionList=new String[]{"前锋","中锋","后卫","门将"};  //默认为0,1,2,3;
    final String[] PlaceList=new String[]{"金州体育场","大连市体育馆","大连市体育中心足球场","大连凌云室内足球场","奥克体育场"};  //默认为1,2,3,4,5;
    private String[] companyList=new String[]{"一","二","三","四","五","六","日"};
    private boolean[] defSel={false,false,false,false,false,false,false};
    private List<Integer> time=new ArrayList<>();


    String name= "name";
//    String namevalue;
    String TAG="0";
    String sex="sex";
    String sexvalue;
    private String height="height";
    private String weight="weight";
    String photo="photo";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
     Button detail_back_myinfo;
    CircleButton user_images;
    userInfo userInfo;
    private View contentView;
    private PopupWindow popupWindow;
    private String fileName = "";
    private File tempFile;
    int crop = 100;// 裁剪大小
    private static final String User = "user_head/";
    Bitmap bmap;
    FileOutputStream out=null;
    int[] aa={1, 2, 3,4};
    Bitmap bitmap;
    String massages;
//    String defaultPath= Params.DATA_PATH + User + "user_head_photo.jpg";
//    PhotoCrop photoCrop;
    Context context;

    private Dialogs dialog;
//    private TextView name,age,brithday,high;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.me_myinfo,null);
        setContentView(contentView);

        init();
//        photoCrop=new PhotoCrop(context,popupWindow,fileName,crop,User,bmap,out,user_images,bitmap,contentView,this,defaultPath);
//        setContentView(R.layout.me_myinfo);
        initFile();
        if (userInfo != null) {
            initDate();
        } else {
            Toast toast = Toast.makeText(this, "未登录", Toast.LENGTH_LONG);
            toast.show();
        }
         initPhotoDate();
        Log.i("userinfo",userInfo.toString());
    }
    private void init()
    {
        textView1 = (TextView) findViewById(R.id.fc_queryMyself_name);
        textView2 = (TextView) findViewById(R.id.fc_queryMyself_sex);
        textView3 = (TextView) findViewById(R.id.fc_queryMyself_birthday);
        textView4 = (TextView) findViewById(R.id.fc_queryMyself_height);
        textView5 = (TextView) findViewById(R.id.fc_queryMyself_weight);
        textView6 = (TextView) findViewById(R.id.fc_queryMyselfatyTime);
        textView7 = (TextView) findViewById(R.id.fc_queryMyself_atyField);
        textView8 = (TextView) findViewById(R.id.fc_queryMyself_position);
        textView9 = (TextView) findViewById(R.id.fc_queryMyself_myCity);
        user_images= (CircleButton) findViewById(R.id.user_images);
        user_images.setOnClickListener(this);
//        userInfo userInfo=new userInfo();
//        MainApplication.setUserInfo(userInfo);
        userInfo = MainApplication.getUserInfo();


        detail_back_myinfo=(Button)findViewById(R.id.detail_back_myinfo);
        detail_back_myinfo.setOnClickListener(this);
//        name=(TextView)findViewById(R.id.name);
        textView1.setOnClickListener(this);
//        age=(TextView)findViewById(R.id.age);
        textView2.setOnClickListener(this);
//        brithday=(TextView)findViewById(R.id.brithday);
        textView3.setOnClickListener(this);
//        high=(TextView)findViewById(R.id.high);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView6.setOnClickListener(this);
        dialog=new Dialogs();

        int flag=140;
        for (int i=0;i<hignList.length;i++){
            hignList[i]=flag+"";//+"cm";
            flag++;
//            LogUtils.e(""+hignList[i]);
        }
        flag=40;
        for (int i=0;i<weightList.length;i++){
            weightList[i]=flag+"";//+"kg";
            flag++;
//            LogUtils.e(""+weightList[i]);
        }


        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        //updateDiaplay()把日期显示到TextView上
//        updateDiaplay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back_myinfo:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.fc_queryMyself_name:
                TAG="1";
                MainApplication.dialog="name";
                LogUtils.e("name");
                showEditDialog(contentView);
                break;
            case R.id.fc_queryMyself_sex:
                TAG="2";
                MainApplication.dialog="sex";
                LogUtils.e("sex");
                showEditDialog(contentView);
                break;
            case R.id.fc_queryMyself_birthday:
                TAG="3";
                MainApplication.dialog="brithday";
                setDate();
                break;

            case R.id.fc_queryMyself_height:
                TAG="4";
                selectdialog("请选择您的身高？",textView4,hignList);
                break;

            case R.id.user_images:
                TAG="10";
                pickPhoto();
                break;
            case R.id.camera:
                LogUtils.e("打开相机");
                openCamera();
                popupWindow.dismiss();
                break;
            case R.id.gallery:
                LogUtils.e("选择本机照片");
                PhotoUtils.selectPhoto(this);
                popupWindow.dismiss();
                break;
            case R.id.cancel:
                if (popupWindow != null) popupWindow.dismiss();
                break;
            case R.id.fc_queryMyself_weight:
                TAG="5";
                selectdialog("请选择您的体重？",textView5,weightList);
                break;

            case R.id.fc_queryMyself_position:
                TAG="8";
                selectdialog("请选择您的位置？",textView8,positionList);
                break;
            case R.id.fc_queryMyself_atyField:
                selectdialog("请选择活动场地？",textView7,PlaceList);

                break;
            case R.id.fc_queryMyselfatyTime:
                time.removeAll(time);
                Dialog dialog=new AlertDialog.Builder(MeMyInfo.this)
                        .setTitle("选择日期")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                textView6.setText("周");
                                for (int i=0;i<defSel.length;i++) {
                                    if (defSel[i]) {
                                        MeMyInfo.this.textView6.append(MeMyInfo.this.companyList[i] + "、");
                                        int x=i+1;
                                        time.add(x);
                                        //time.removeAll(time);
                                        doits("atyTime",time);
                                    }
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setMultiChoiceItems(companyList, defSel, new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                for(int i=0;i<companyList.length;i++){

                                    if(i==which&&isChecked){
                                        defSel[i]=true;
                                    }
                                    LogUtils.e(""+defSel[i]);
                                }
                            }
                        })
                        .create();
                dialog.show();
                break;

            default:break;

        }
    }
    int[] indx;
    private void selectdialog(final String text, final TextView textView, final String[] a){
        android.app.Dialog alertDialog_position = new AlertDialog.Builder(this).
                setTitle(text)
                .setItems(a, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MeMyInfo.this, hignList[which], Toast.LENGTH_SHORT).show();
                        textView.setText(a[which]);

                        if(text.equals("请选择您的身高？")){
                            doits("height",a[which]);

//                            doIt("height",a[which],aa,"9",aa);
                        }
                        if(text.equals("请选择您的体重？")){
                            doits("weight",a[which]);

//                            doIt("weight",a[which],aa,"9",aa);
                        }
                        if(text.equals("请选择您的位置？")){
                            indx=new int[]{which+1};
                            doits("position",indx);

//                            doIt("sex",sexvalue,indx,"9",aa);
                            Log.i("position:", String.valueOf(indx[0]));
                            userInfo.setPosition(indx);
                        } if(text.equals("请选择活动场地？")){
//                            indx=new int[]{which+1};
                            doits("atyField",which+1);

//                            doIt("sex",sexvalue,indx,"9",aa);
//                            Log.i("position:", String.valueOf(indx[0]));
                            userInfo.setAtyField(String.valueOf(which+1));//.setPosition(which+1);
                        }
                    }
                }).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).
                        create();
        alertDialog_position.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }


    CreateDialog createDialog;
    String names;
    public void showEditDialog(View view) {
        createDialog = new CreateDialog(this,R.style.popuAnim,onClickListener);
        createDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_sure:
                    if (MainApplication.dialog.equals("name"))
                    {
                        names = createDialog.text_name.getText().toString().trim();
                            doits(name,names);

                    }else if(MainApplication.dialog.equals("sex"))
                    {
                        switch (createDialog.choose_radio.getCheckedRadioButtonId()){
                            case R.id.grade_one:
                                LogUtils.e("男");
                                sexvalue="0";
//                                userInfo.setSex("0");
//                                doIt(sex,"0",aa,"9",aa);
                                doits(sex,"0");
                                break;
                            case R.id.grade_two:
                                LogUtils.e("女");
                                sexvalue="1";
//                                userInfo.setSex("1");
                                doits(sex,"1");
//                                doIt(sex,"1",aa,"9",aa);
                                break;
                            default:break;
                        }
                    }
                    break;

            }
            createDialog.dismiss();
        }
    };

    private void initDate() {
        if (userInfo.getPhoto() != null&&!userInfo.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(userInfo.getPhoto()));
            user_images.setImageBitmap(bitmap);
        }
        else {
            user_images.setImageResource(R.mipmap.personhead);
        }
//        Log.i("photo",userInfo.getPhoto());
        textView1.setText(userInfo.getName());
        if(userInfo.getSex().equals("0")){
            textView2.setText("男");
        }
        if(userInfo.getSex().equals("1"))
        {
            textView2.setText("女");
        }
        sexvalue=userInfo.getSex();
//        textView2.setText(userInfo.getSex());
        textView3.setText(userInfo.getBrithday());
//        Log.i("sssss",userInfo.getBrithday());
        textView4.setText(userInfo.getHeight());
//        textView4.append("cm");
        textView5.setText(userInfo.getWeight());
//        textView5.append("kg");
        if (userInfo.getAtyTime().length==0)
        {
            textView6.setText("设置活动时间");
        }
        if(userInfo.getAtyTime().length!=0){
//            textView6.setText();                            //活动时间初始化

            int a[]=userInfo.getAtyTime();
            String str="";
            textView6.append("周");
            for (int i=0;i<a.length;i++)
            {
                LogUtils.e(""+a[i]);

                if (a[i]==1)
                {
                    textView6.append("一 ");
                }else if (a[i]==2)
                {
                    textView6.append("二 ");

                }else if (a[i]==3)
                {
                    textView6.append("三 ");

                }else if (a[i]==4)
                {
                    textView6.append("四 ");

                }else if (a[i]==5)
                {
                    textView6.append("五 ");

                }else if (a[i]==6)
                {
                    textView6.append("六 ");

                }else if (a[i]==7)
                {
                    textView6.append("日 ");

                }
            }

        }
//        textView6.setText(userInfo.getAtyTime().get(0));
        textView7.setText(userInfo.getAtyField());
        Log.e("atyfild",userInfo.getAtyField()+"");
//        textView8.setText(userInfo.getPosition().get(0));
        if(userInfo.getPosition().length!=0){
            textView8.setText(positionList[userInfo.getPosition()[0]-1]);
            Log.i("positionaa",positionList[userInfo.getPosition()[0]-1]);
        }
        textView9.setText("中国大连");//userInfo.getMyCity()
        Log.i("userinfo",userInfo.toString());
    }
    private void initPhotoDate() {
//        String defaultPath = Params.DATA_PATH + User + "user_head_photo.jpg";
//        File file = new File(defaultPath);
//        if (file.exists()){
//            Bitmap bitmap = BitmapFactory.decodeFile(defaultPath);
//            user_images.setImageBitmap(bitmap);
//
//            LogUtils.e("defaultPath:" + defaultPath);
//        }
//        if(!file.exists()){
            try {
                bmap =bitmap;
                if (!TextUtils.isEmpty(fileName)){
                    out = new FileOutputStream(fileName);
                }
                if (bmap != null) {
                    bmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//    }
    public void initFile() {
        if(fileName.equals("")) {
            if(FileUtils.isSdcardExist()) {
                String path = Params.DATA_PATH + User;
                FileUtils.createDirFile(path);
                LogUtils.e("defaultPath:" + path);
                fileName = path + "user_head_photo.jpg";
                tempFile = new File(fileName);
            } else {
                Toast toast = Toast.makeText(this, "请插入sd卡", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    /**
     * 相册选择照片或者拍照上传，这里的上传只保留在本地的文件的某个路径下，再次进入则从指定的位置读取即可
     */
    private void pickPhoto() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.bottom_pop_carmer,null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popuAnim);
        view.findViewById(R.id.camera).setOnClickListener(this);
        view.findViewById(R.id.gallery).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }
    /**
     * 调用相机
     */
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 打开相机
        intent.putExtra("output", Uri.fromFile(tempFile));
        startActivityForResult(intent, PhotoUtils.OPEN_CAMERA_CODE);
    }
    /**
     * 裁剪图片
     * @param uri Uri
     */
    public void cropPhoto(Uri uri) {
        LogUtils.e("-------------");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");// mUri是已经选择的图片Uri
//        intent.putExtra("output", Uri.fromFile(tempFile));
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", crop);
        intent.putExtra("outputY", crop);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PhotoUtils.CROP_PHOTO_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode){
            case PhotoUtils.OPEN_CAMERA_CODE:
                LogUtils.e("----------");
                cropPhoto(Uri.fromFile(tempFile));
                break;
            case PhotoUtils.OPEN_GALLERY_CODE:
                Uri uri = data.getData();
                if(uri != null) {
                    cropPhoto(uri);
                }
                break;
            case PhotoUtils.CROP_PHOTO_CODE:
//                FileOutputStream out = null;
                try {
                    bmap = data.getParcelableExtra("data");

                    if (!TextUtils.isEmpty(fileName)){
                        out = new FileOutputStream(fileName);
                    }
                    if (bmap != null) {
                        String value= bytewithString.bytesToHexString( bitmapwithbyte.Bitmap2Bytes(bmap));
                        doits(photo,value);

//                        doIt(photo,value,aa,"9",aa);
                        bmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }

    }


    private void setDate() {
        showDialog(DIALOG_DATE_ID);
    }

    @Override
    protected android.app.Dialog onCreateDialog(int id) {
        switch(id){
            case DIALOG_DATE_ID:
                //返回一个日期对话框
                return new DatePickerDialog(this, setDateCallBack, mYear, mMonth, mDay);
        }
        return super.onCreateDialog(id);
    }

    //回调函数，int year, int monthOfYear,int dayOfMonth三个参数为日期对话框设置的日期
    private DatePickerDialog.OnDateSetListener setDateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDiaplay();
        }
    };
    StringBuffer stringBuffer = new StringBuffer();
    String bir;
    private void updateDiaplay(){
//        StringBuffer stringBuffer = new StringBuffer();
        bir="";

        stringBuffer.append(mYear).append("年").append(mMonth+1).append("月").append(mDay).append("日");
        LogUtils.e(""+stringBuffer);
        String mo= String.valueOf(mMonth+1);
        bir=mYear+"-"+mo+"-"+mDay;
        Log.i("bir",bir);
        String birthday = "birthday";
        doits(birthday,bir);
//        doIt(birthday,bir,aa,"9",aa);
//        textView3.setText(stringBuffer);
    }

//    private void doIt(String type,String typevalue,int [] atyPositionvalue,String fieldID,int [] atyTimevalue){
//        AppAction appAction= Factory.createAppActionImpl(this);
//        appAction.fc_modifyMyself(type,typevalue,atyPositionvalue,fieldID,atyTimevalue, Params.fc_modifyMyself,this);
//    }

    private void doits(String type,Object typevalue){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyMyselfs(type,typevalue,Params.fc_modifyMyself,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast=Toast.makeText(this,"修改成功",Toast.LENGTH_LONG);//成功后直接获取控件数据
        toast.show();
        if(TAG.equals("10")){
            if(bmap!=null){
                String value= bytewithString.bytesToHexString( bitmapwithbyte.Bitmap2Bytes(bmap));
                MainApplication.getUserInfo().setPhoto(value);
                user_images.setImageBitmap(bmap);
                Log.i("photo",userInfo.toString());
            }
        }
        if(TAG.equals("1")){
            textView1.setText(names);
            MainApplication.getUserInfo().setName(names);
        }
        if(TAG.equals("2")){
            if(sexvalue.equals("0")){
                textView2.setText("男");
            }
            else if(sexvalue.equals("1"))
            {
                textView2.setText("女");
            }
            MainApplication.getUserInfo().setSex(sexvalue);

        }

//        userInfo.setBrithday(textView3.getText().toString());
        if(TAG.equals("3")){
            userInfo.setBrithday(bir);
            textView3.setText(userInfo.getBrithday());

            MainApplication.getUserInfo().setBrithday(userInfo.getBrithday());

        }
        if(TAG.equals("4")){
            userInfo.setHeight(textView4.getText().toString());
            textView4.setText("");
            textView4.setText(userInfo.getHeight());

            MainApplication.getUserInfo().setHeight(userInfo.getHeight());

        }
        if(TAG.equals("5")){
            userInfo.setWeight(textView5.getText().toString());
            textView5.setText("");
            textView5.setText(userInfo.getWeight());

            MainApplication.getUserInfo().setWeight(userInfo.getWeight());

        }
//        textView4.append("cm");
//        textView5.append("kg");
//        textView6.setText(userInfo.getAtyTime().get(0));
//        userInfo.setAtyField(textView7.getText().toString());
        if(TAG.equals("8")){
            userInfo.setPosition(indx);
            if(userInfo.getPosition().length!=0||userInfo.getPosition()!=null){
                userInfo.setPosition(indx);
                textView8.setText(positionList[userInfo.getPosition()[0]-1]);
                Log.i("positionss",positionList[userInfo.getPosition()[0]-1]);
            }
        }
        if(TAG.equals("7")){
            textView7.setText(userInfo.getAtyField());
        }
        if(TAG.equals("9")){
            userInfo.setMyCity(textView9.getText().toString());
            textView9.setText(userInfo.getMyCity());
        }
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
}

