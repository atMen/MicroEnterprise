package com.tcrj.micro.entity;

/**
 * Created by leict on 2018/7/13.
 */

public class MessageEvent {

    private String message;
    private int type;


    public MessageEvent(String message, int type){
        this.message=message;
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
