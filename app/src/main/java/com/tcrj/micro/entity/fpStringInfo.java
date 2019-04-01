package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/3/29.
 */

public class fpStringInfo {

    /**
     * errorcode : 9999
     * message : 操作成功。
     * data : {"content":[{"cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","cityName":"西安市","countyId":"57134b7a55fb4d0db2b7283abc9733f1","countyName":"未央区","enable":"0","homeName":"2","id":"ff808081695ac11401695ac602870003","optime":"2019-03-29 14:55:19","poorReason":"缺乏劳动力","townName":"好家村","userId":""},{"cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","cityName":"西安市","countyId":"57134b7a55fb4d0db2b7283abc9733f1","countyName":"未央区","enable":"0","homeName":"晓明","id":"ff808081695ac11401695ac644af0004","optime":"2019-03-29 14:55:19","poorReason":"建陵","townName":"李家村","userId":""}],"first":true,"last":false,"number":0,"numberOfElements":2,"size":2,"sort":{},"totalElements":3,"totalPages":2}
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
