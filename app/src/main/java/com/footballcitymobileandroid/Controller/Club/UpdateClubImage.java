package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.Cache.FileCache.FileUtils;
import com.footballcitymobileandroid.BLL.Util.Cache.PhotoCache.PhotoUtils;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.Entity.ClubEntity.club.clubstatic;
import com.footballcitymobileandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhoudi on 16/5/31.
 */
public class UpdateClubImage extends Activity implements View.OnClickListener  ,ActionCallBackListener<BaseEntity<Wrong>> {
    CircleButton club_images;
    Button detail_back,detail_title;
    PopupWindow popupWindow;
    private String fileName = "";
    private File tempFile;
    int crop = 100;// 裁剪大小
    private static final String User = "club_head/";
    Bitmap bmap;
    //    FileOutputStream out=null;
//    Club club;
    String value;
    View contentView;
    ClubList clubList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(R.layout.update_club_image,null);
        setContentView(contentView);
        Log.i("新建",User);

        init();
        initFile();
        initPhotoDate();


    }

    private  void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        club_images= (CircleButton) contentView.findViewById(R.id.club_images);
        club_images.setOnClickListener(this);
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);
    }

    BaseEntity<Club> baseEntity;
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.club_images:
                pickPhoto();
                break;
            case R.id.camera:
                LogUtils.e("打开相机");
                openCamera();
                popupWindow.dismiss();
                break;
            case R.id.gallery:
                LogUtils.e("选择本机照片");
                selectPhoto();
                LogUtils.e("----------");
                popupWindow.dismiss();
                break;
            case R.id.cancel:
                if (popupWindow != null) popupWindow.dismiss();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.detail_title:
                doit();
                break;
            default:break;
        }
    }

    private void initPhotoDate() {
//        String defaultPath = Params.DATA_PATH + User + "club_head_photo.jpg";
//        File file = new File(defaultPath);
//        if (file.exists()){
//            Bitmap bitmap = BitmapFactory.decodeFile(defaultPath);
//            club_images.setImageBitmap(bitmap);


            club_images.setImageResource(R.mipmap.ic_launcher);





//            LogUtils.e("defaultPath:" + defaultPath);
//
//        }
    }
    public void initFile() {
        if(fileName.equals("")) {
            if(FileUtils.isSdcardExist()) {
                String path = Params.DATA_PATH + User;
                FileUtils.createDirFile(path);
                LogUtils.e("defaultPath:" + path);
                fileName = path + "club_head_photo.jpg";
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
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
    /**
     * 调用相机
     */
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 打开相机
        intent.putExtra("output", Uri.fromFile(tempFile));
        startActivityForResult(intent, PhotoUtils.OPEN_CAMERA_CODE);
    }
    public  void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
//		intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 11);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    Log.i("uri",uri.toString());
                    cropPhoto(uri);
                }
                break;
            case PhotoUtils.CROP_PHOTO_CODE:
                FileOutputStream out = null;
                try {
                    bmap = data.getParcelableExtra("data");

                    if (!TextUtils.isEmpty(fileName)){
                        out = new FileOutputStream(fileName);
                    }
                    if (bmap != null) {
                        value= bytewithString.bytesToHexString( bitmapwithbyte.Bitmap2Bytes(bmap));
                        club_images.setImageBitmap(bmap);
//                        MainApplication.getClub().setLogo(value);
                        bmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        Log.i("clubimage",value);
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

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyClub(clubList.getClubID(),"logo",value, Params.fc_modifyClub,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Wrong> data) {
        Toast toast = Toast.makeText(this, "修改logo成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
//        MainApplication.getClubLists().setLogo(value);
        clubstatic.clubid=clubList.getClubID();
        clubstatic.logo=value;
        clubstatic.station="1";
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

//     Button detail_back,detail_title;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.update_club_image);
//        init();
//    }
//
//    private void init()
//    {
//        detail_back=(Button) findViewById(R.id.detail_back);
//        detail_back.setOnClickListener(this);
//
//        detail_title=(Button) findViewById(R.id.detail_title);
//        detail_title.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.detail_back:
//                this.finish();
//                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
//                break;
//            case R.id.detail_title:
//                this.finish();
//                break;
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
//    }
}
