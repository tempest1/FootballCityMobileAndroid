package com.footballcitymobileandroid.Entity.Base;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/7.
 * 异常汇总
 * （1）类型（e_Type）：1
 说明（e_Msg）：验证码错误
 （2）类型（e_Type）：2
 说明（e_Msg）：手机号已经被注册
 （3）类型（e_Type）：3
 说明（e_Msg）：系统内部错误
 （4）类型（e_Type）：4
 说明（e_Msg）：手机号码格式有误
 （5）类型（e_Type）：5
 说明（e_Msg）：用户名不存在或是密码错误
 （6）类型（e_Type）：6
 说明（e_Msg）：该用户没有登入
 （7）类型（e_Type）：7
 说明（e_Msg）：修改的值为非法数据
 （8）类型（e_Type）：8
 说明（e_Msg）：原手机号验证码错误
 （9）类型（e_Type）：9
 说明（e_Msg）：新手机号验证码错误
 （10）类型（e_Type）：10
 说明（e_Msg）：该账号密码错误
 */
public class exception implements Serializable {
    private String e_Type;    //错误类型编码
    private String e_Msg;     //错误说明
    public void setE_Type(String e_Type)
    {
        this.e_Type=e_Type;
    }
    public String getE_Type()
    {
        return e_Type;
    }
    public void setE_Msg(String e_Msg)
    {
        this.e_Msg=e_Msg;
    }
    public String getE_Msg()
    {
        return e_Msg;
    }

    /**
     * 应用错误消息,不属于服务器回应
     */
    static final public String PARAM_NULL = "PARAM_NULL"; // 参数为空
    static final public String PARAM_ILLEGAL = "PARAM_ILLEGAL"; // 参数不合法
    static final public String TIME_OUT_EVENT = "TIME_OUT_EVENT";
    static final public String TIME_OUT_EVENT_MSG = "网络连接超时";
    static final public String ERROR_REQUEST = "ERROR_REQUEST";
    static final public String ERROR_REQUEST_MSG = "请求参数有误";
    static final public String ERROR_REQUESTS = "ERROR_REQUEST";
    static final public String ERROR_REQUEST_MSGS = "获取参数有误";
    static final public String ERROR_SERVER = "ERROR_SERVER";
    static final public String ERROR_SERVER_MSG = "获取失败";
    static final public String ERROR_SERVERS = "ERROR_SERVERS";
    static final public String ERROR_SERVER_MSGS = "服务异常";
    static final public String ERROR_EVENT = "";
}
