package com.footballcitymobileandroid.BLL.Util.ImageUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by smartlab on 16/5/20.
 */
public class bitmapwithbyte {
    //Bitmap → byte[]
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
//    byte[]->Bitmap
    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b!=null&&b.length!= 0 ) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    //从资源中获取
      private Bitmap drawableToBitamp(Drawable drawable)
       {
               BitmapDrawable bd = (BitmapDrawable) drawable;
                return bd.getBitmap();
            }
}
