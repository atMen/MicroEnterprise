package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/4/26.
 */

public class sendPicInfo {

    /**
     * errorcode : 9999
     * message : 操作成功。
     * data : /uploadfile/2019-04-23/20190423022901872.jpg
     */

    private String errorcode;
    private String message;
    private String data;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
