package com.footballcitymobileandroid.BLL.Util.Updata;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.R;

/**
 * Created by smartlab on 16/8/3.
 */
public class CheckVersion {
//    public static final String APK_DOWNLOAD_URL = "url";
//    public static final String APK_UPDATE_CONTENT = "updateInfo";
    private static final String TAG = "Check";
//    private Thread mThread;

    App updateInfo;
    /**
     * Heart of the library. Check if an update is available for download
     * parsing the desktop Play Store page of the app
     */
    private Activity mContext;

    public CheckVersion(Activity mContext,App updateInfo) {
        this.mContext = mContext;
        this.updateInfo=updateInfo;
    }

    public  void parseJson(App updateInfo) {
//        mThread.interrupt();
//        Looper.prepare();
        LogUtils.e("new version is available");
        try {

//            Gson gson = new Gson();
//            Type type = new TypeToken<UpdateInfo>(){}.getType();
//            App updateInfo = gson.fromJson(json, type);

            String updateMessage = mContext.getResources().getString(R.string.dialog_update_msg) + updateInfo.getVerName() + "\n" + updateInfo.getDescription();
            String apkUrl = updateInfo.getUrl();
            int apkCode = Integer.parseInt(updateInfo.getVerCode());
            int versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;

            Log.e("VersionCode", versionCode + "当前版本");

            if (apkCode > versionCode) {
//                    showNotification(updateMessage,apkUrl);
                showDialog();
            } else {
                //Toast.makeText(mContext, mContext.getString(R.string.app_no_new_update), Toast.LENGTH_SHORT).show();
            }


        } catch (PackageManager.NameNotFoundException ignored) {
            Log.e(TAG, "parse json error", ignored);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Show dialog
     */
    public void showDialog() {
        UpdateDialog d = new UpdateDialog();
        Bundle args = new Bundle();
        args.putSerializable("APP",updateInfo);
        d.setArguments(args);
        d.show(mContext.getFragmentManager(), null);
    }

}
