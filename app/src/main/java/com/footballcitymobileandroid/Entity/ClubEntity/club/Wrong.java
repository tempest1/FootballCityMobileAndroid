package com.footballcitymobileandroid.Entity.ClubEntity.club;

/**
 * Created by smartlab on 16/5/30.
 */
public class Wrong {
    private String modifyType;
    private String e_Type;    //错误类型编码
    private String e_Msg;     //错误说明

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setE_Type(String e_Type) {
        this.e_Type = e_Type;
    }

    public String getE_Type() {
        return e_Type;
    }

    public void setE_Msg(String e_Msg) {
        this.e_Msg = e_Msg;
    }

    public String getE_Msg() {
        return e_Msg;
    }
}
