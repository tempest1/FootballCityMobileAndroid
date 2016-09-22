package com.footballcitymobileandroid.BLL.Interface;

/**
 * Created by smartlab on 15/11/19.
 * 消息回调
 */
public interface ActionCallBackListener<T> {
    /**
     * 处理成功
     * @param data 返回数据
     */
    void onSuccess(T data);

    /**
     * 请求失败
     * @param e_Type 错误码
     * @param e_Msg 错误详情
     */
    void onFailure(String e_Type, String e_Msg);
}
