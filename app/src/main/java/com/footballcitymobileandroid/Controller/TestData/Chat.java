package com.footballcitymobileandroid.Controller.TestData;

/**
 * Created by zhoudi on 16/7/2.
 */
public class Chat {
    private String time;
    private String name;
    private String context;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
