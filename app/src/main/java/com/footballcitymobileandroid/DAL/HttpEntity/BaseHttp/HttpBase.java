package com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp;

import android.util.Log;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by smartlab on 16/5/9.
 */
public class HttpBase {

    /**
     * 获取服务端生成的BeanDefinitionReaderSid
     * @param headerFields 传入http头信息，拆包
     */
    private void getBeanDefinitionReaderSid(Map<String, List<String>> headerFields) {

        if (MainApplication.getBeanDefinitionReaderSid() == null) {
            if (headerFields != null) {
                List<String> BeanDefinitionReaderSid = headerFields.get("BeanDefinitionReaderSid");
                if (BeanDefinitionReaderSid != null){
                    String head = BeanDefinitionReaderSid.get(0);
                    MainApplication.loop();
                    MainApplication.setBeanDefinitionReaderSid(head);
                    LogUtils.e("sid" + head);
                }
            }
        } else {
            LogUtils.i(MainApplication.getBeanDefinitionReaderSid());
        }
    }
    /**
     * 得到一个HttpUrlConnection
     * @param path 地址
     * @return HttpURLConnection对象
     */
    private HttpURLConnection getConnection(String path){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(path);
            LogUtils.i(path);
            urlConnection = (HttpURLConnection) url.openConnection();//
            //  post请求必要设置允许输入
            urlConnection.setDoInput(true);
            //  post请求必要设置允许输出
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            //  设置特定的头部信息
            urlConnection.setRequestProperty("BeanDefinitionReaderSid", MainApplication.getBeanDefinitionReaderSid());
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setChunkedStreamingMode(0);

            urlConnection.setConnectTimeout(1000 * 8);
            urlConnection.setReadTimeout(300000);
            urlConnection.connect();
        } catch (IOException e) {
//            networks.networksss=10;
            e.printStackTrace();
        }
        return urlConnection;
    }

    /**
     * 得到输出流数据
     * @param connection
     * @return
     */
    private String getResult(HttpURLConnection connection) {
        String result;
//        if (connection != null) {

        try{
            /**
             * 获取并设置BeanDefinitionReaderSid，
             */
            getBeanDefinitionReaderSid(connection.getHeaderFields());
            // 获取响应的输入流对象
            InputStream is;
            try {
                is = connection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();

                result = new String(baos.toByteArray());
            } catch (IOException e) {
//                networks.networksss=10;
                e.printStackTrace();
                e.getMessage();
                return null;
            }
            connection.disconnect();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        }
//        else {
//////            networks.networksss=10;
//            return null;
//        }
    }


    public <T> T postHandle(String json,String url, Type typeOfT) throws IOException {
        Gson gson = new Gson();
        String result = null;
        Log.e("sys: ",json);
        Log.e("sys: ",url);
        LogUtils.e("request: " + json);
        HttpURLConnection connection = getConnection(url);
        if (connection != null) {

            OutputStream os = connection.getOutputStream();

            os.write(json.getBytes());
            Log.i("os",os.toString());
            os.flush();
            result = getResult(connection);
        }
        if (result == null) {
//            result = "{event:" + ErrorEvent.TIME_OUT_EVENT +",msg:" + ErrorEvent.TIME_OUT_EVENT_MSG+"}";
            // 打印出结果
            LogUtils.e("uncaught type error server is not response any massage");


//            return gson.fromJson(result, typeOfT);
        }

        try {
            // 打印出结果
            LogUtils.e("response: " + result);
//            MainApplication.MESSAGE=result;;

            return gson.fromJson(result, typeOfT);
        } catch (JsonParseException e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
//            networks.networksss=10;
//            result = "{event:" + ErrorEvent.TIME_OUT_EVENT +",msg:"+ErrorEvent.TIME_OUT_EVENT_MSG+"}";
            // 打印出结果
            LogUtils.e("response: " + result);
             return null;
//            return gson.fromJson(result, typeOfT);
        }
    }

}
