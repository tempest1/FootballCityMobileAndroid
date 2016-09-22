package com.footballcitymobileandroid.Entity.Base;


import com.footballcitymobileandroid.Entity.MeEntity.ConfigInfo;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/9.
 */
public class BaseEntity<T> implements Serializable {

    private String type;
    private String cmd;
    private Response<T> response;
    private ConfigInfo info;

    public void setType(String type)
    {
        this.type=type;
    }
    public String getType(){
        return type;
    }
    public void setCmd(String cmd)
    {
        this.cmd=cmd;
    }
    public String getCmd(){
        return cmd;
    }
    public void setResponse(Response<T> response)
    {
        this.response=response;
    }
    public Response getResponse()
    {
        return response;
    }
    public void setInfo(ConfigInfo info){
        this.info=info;
    }
    public ConfigInfo  getInfo(){
        return info;
    }

    public String toString ()
    {
        return  "type"+type+"cmd"+cmd+"response"+response+"result"+response.getResult()+"T"+response.getT()+"exception"+response.getException();
    }

}
