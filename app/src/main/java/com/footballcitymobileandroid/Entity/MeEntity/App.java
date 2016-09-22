package com.footballcitymobileandroid.Entity.MeEntity;

import java.io.Serializable;

/**
 * Created by smartlab on 16/7/20.
 */
public class App implements Serializable{
    private String appId;
    private String verCode;
    private String verName;
    private String description;
    private String url;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getVerName() {
        return verName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "App{" +
                "appId='" + appId + '\'' +
                ", verCode='" + verCode + '\'' +
                ", verName='" + verName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
