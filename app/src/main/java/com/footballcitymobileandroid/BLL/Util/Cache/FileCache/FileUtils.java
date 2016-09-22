package com.footballcitymobileandroid.BLL.Util.Cache.FileCache;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.DecimalFormat;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * @fileName FileUtils.java
 * @description 文件工具类
 */
public class FileUtils {
	public static final int DEFAULT_BUFFER_SIZE = 1024;
	/**
	 * 判断SD是否可以
	 * 
	 * @return
	 */
	public static boolean isSdcardExist() {
		if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return true;
		}
		return false;
	}

	/**
	 * 获取sd根路径
	 * may return null
	 * @return rootPath
	 */
	public static String getRootPath(){
		File sdDir = null;
		boolean sdCardExist = isSdcardExist();//判断sd卡是否存在
		if(sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
			return sdDir.toString();
		}
		return null;
	}
	/**
	 * 创建根目录
	 * 
	 * @param path
	 *            目录路径
	 */
	public static void createDirFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 创建的文件
	 */
	public static File createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹的路径
	 */
	public static void delFolder(String folderPath) {
		delAllFile(folderPath);
		File myFilePath = new File(folderPath);
		if (myFilePath.exists())
		myFilePath.delete();
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 *            文件的路径
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
			}
		}
	}

	/**
	 * 获取文件的Uri
	 * 
	 * @param path
	 *            文件的路径
	 * @return
	 */
	public static Uri getUriFromFile(String path) {
		File file = new File(path);
		return Uri.fromFile(file);
	}

	/**
	 * 换算文件大小
	 * 
	 * @param size
	 * @return
	 */
	public static String formatFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "未知大小";
		if (size < 1024) {
			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1048576) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			fileSizeString = df.format((double) size / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) size / 1073741824) + "G";
		}
		return fileSizeString;
	}

	private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
	public static File createNewFile(Context context, String path) {
		File file = null;
		if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
			file = new File(Environment.getExternalStorageDirectory(), path);
			LogUtils.i("new File");
		}
		//	若不存在路径创建路径
		if (file == null || (!file.exists() && !file.mkdirs())) {
			file = context.getCacheDir();
			LogUtils.i("file not exists or dir not exists");
			LogUtils.i("file path" + file.getPath());
		}
		LogUtils.i(file.getPath());
		return file;
	}

	/**
	 * 是否拥有读取Sdcard的权限
	 * @param context Context对象
	 * @return Boolean
	 */
	private static boolean hasExternalStoragePermission(Context context) {
		int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
		return perm == PackageManager.PERMISSION_GRANTED;
	}

	/**
	 * 将文件转换成字符串
	 * @param file 文件所在路径
	 * @param encoding 编码方式
	 * @return String
	 */
	public static String file2String(File file, String encoding){
		InputStreamReader reader = null;
		StringWriter writer = new StringWriter();
		//	以流的方式读取文件
		try {
			if (encoding != null || !"".equals(encoding.trim())) {
				reader = new InputStreamReader(new FileInputStream(file), encoding);
			} else {
				reader = new InputStreamReader(new FileInputStream(file));
			}
			//将输入流写入输出流
			char[] buffer = new char[DEFAULT_BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		//返回转换结果
		return writer.toString();
	}

	/**
	 *
	 * @param imageFile
	 * @return
	 */
	public static String fileToString(File imageFile){

		InputStream in = null;
		byte[] data = null;
		//读取图片字节数组
		try {
			in = new FileInputStream(imageFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageString = new String(data);
		return imageString;
	}
}
