package com.footballcitymobileandroid.Controller.Me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Util.Cache.FileCache.FileUtils;
import com.footballcitymobileandroid.BLL.Util.Cache.PhotoCache.PhotoUtils;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhoudi on 16/5/23.
 */
public class  MeSetClubImage extends Fragment implements View.OnClickListener {
     View view,dot;
    CircleButton club_images;

    PopupWindow popupWindow;
    private String fileName = "";
    private File tempFile;
     int crop = 100;// 裁剪大小
    private static final String User = "club_head/";
    Bitmap bmap;
//    FileOutputStream out=null;
//    Club club;
    String value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_image, container,false);
        Log.i("新建",User);

        init();
        initFile();
        initPhotoDate();
        return view;

    }
    private  void init()
    {
        dot=view.findViewById(R.id.v_dot0);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        club_images= (CircleButton) view.findViewById(R.id.club_images);
        club_images.setOnClickListener(this);
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
            default:break;
        }
    }

    private void initPhotoDate() {
//        String defaultPath = Params.DATA_PATH + User + "club_head_photo.jpg";
//        File file = new File(defaultPath);
//        if (file.exists()){
//            Bitmap bitmap = BitmapFactory.decodeFile(defaultPath);
//            club_images.setImageBitmap(bitmap);

        if(MainApplication.getClub()!=null){
           club_images.setImageBitmap(bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(MainApplication.getClub().getLogo())));
            Log.i("setclubimage_clublogo",MainApplication.getClub().getLogo());
        }else {
            club_images.setImageResource(R.mipmap.term_sign);

        }



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
                Toast toast = Toast.makeText(getActivity(), "请插入sd卡", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    /**
     * 相册选择照片或者拍照上传，这里的上传只保留在本地的文件的某个路径下，再次进入则从指定的位置读取即可
     */
    private void pickPhoto() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
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
        if (resultCode != getActivity().RESULT_OK) {
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
                        MainApplication.getClub().setLogo(value);
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
}
