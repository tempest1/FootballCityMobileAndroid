package com.footballcitymobileandroid.BLL.Util.ImageUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Util.Cache.FileCache.FileUtils;
import com.footballcitymobileandroid.BLL.Util.Cache.PhotoCache.PhotoUtils;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * Created by smartlab on 16/6/24.
 */
public class PhotoCrop implements View.OnClickListener {

    private PopupWindow popupWindow;
    private String fileName = "";
    private File tempFile;
    private int crop = 300;// 裁剪大小
    private static  String User = "user_head/";
    Bitmap bmap;             //本地sdcard图片
    FileOutputStream out=null;
    CircleButton user_images;
    int[] a={1, 2, 3};
    Bitmap bitmap;          //服务器图片需要在初始化时赋值 bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(userInfo.getPhoto()));
    private View contentView;
     Activity activity;
    String defaultPath;

    Context context;
    public PhotoCrop(Context context, PopupWindow popupWindow, String fileName, int crop, String user, Bitmap bmap, FileOutputStream out, CircleButton user_images, Bitmap bitmap, View contentView, Activity activity,String defaultPath){
        this.context=context;
        this.popupWindow=popupWindow;
        this.fileName=fileName;
        this.crop=crop;
        User=user;
        this.bmap=bmap;
        this.out=out;
        this.user_images=user_images;
        this.bitmap=bitmap;
        this.contentView=contentView;
        this.activity=activity;
        this.defaultPath=defaultPath;

    }
    public  void initPhotoDate() {
//        String defaultPath = Params.DATA_PATH + User + "user_head_photo.jpg";
        File file = new File(defaultPath);
        if (file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(defaultPath);

            user_images.setImageBitmap(bitmap);

            LogUtils.e("defaultPath:" + defaultPath);
        }
        if(!file.exists()){
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
    }
    public void initFile() {
        if(fileName.equals("")) {
            if(FileUtils.isSdcardExist()) {
                String path = Params.DATA_PATH + User;
                FileUtils.createDirFile(path);
                LogUtils.e("defaultPath:" + path);
                fileName = path + "user_head_photo.jpg";
                tempFile = new File(fileName);
            } else {
                Toast toast = Toast.makeText(activity, "请插入sd卡", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    /**
     * 相册选择照片或者拍照上传，这里的上传只保留在本地的文件的某个路径下，再次进入则从指定的位置读取即可
     */
    public  void pickPhoto() {
        LayoutInflater inflater = LayoutInflater.from(activity);
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
        activity.startActivityForResult(intent, PhotoUtils.OPEN_CAMERA_CODE);
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
        activity.startActivityForResult(intent, PhotoUtils.CROP_PHOTO_CODE);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode !=RESULT_OK) {
//            return;
//        }
//        switch (requestCode){
//            case PhotoUtils.OPEN_CAMERA_CODE:
//                LogUtils.e("----------");
//                cropPhoto(Uri.fromFile(tempFile));
//                break;
//            case PhotoUtils.OPEN_GALLERY_CODE:
//                Uri uri = data.getData();
//                if(uri != null) {
//                    cropPhoto(uri);
//                }
//                break;
//            case PhotoUtils.CROP_PHOTO_CODE:
////                FileOutputStream out = null;
//                try {
//                    bmap = data.getParcelableExtra("data");
//
//                    if (!TextUtils.isEmpty(fileName)){
//                        out = new FileOutputStream(fileName);
//                    }
//                    if (bmap != null) {
//                        String value= bytewithString.bytesToHexString( bitmapwithbyte.Bitmap2Bytes(bmap));
//                        doIt(photo,value,a,"9",a);
//
//                        bmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (out != null) {
//                        try {
//                            out.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                break;
//        }
//
//    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {


            case R.id.user_images:
                pickPhoto();
                break;
            case R.id.camera:
                LogUtils.e("打开相机");
                openCamera();
                popupWindow.dismiss();
                break;
            case R.id.gallery:
                LogUtils.e("选择本机照片");
                PhotoUtils.selectPhoto(activity);
                popupWindow.dismiss();
                break;
            case R.id.cancel:
                if (popupWindow != null) popupWindow.dismiss();
                break;

            default:break;

        }
    }
}
