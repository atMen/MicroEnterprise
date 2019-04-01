package com.spring.chanba.bean;

import java.io.Serializable;

public class HandleEntity implements Serializable{
    private int state;
    private String message;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
