package com.spring.chanba.bean;

import java.io.Serializable;

public class AptitudeDetailEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"pubTime":"","picPath":"https://api.madeinchanba.com/web.files/2018-08-27/201808270953061535334786207_f4q9q.jpg","content":"","id":"2c9f4a87661a61c401661f1f3920004f","author":"浐灞智造","title":"金融服务4","ftitle":"","digest":"","type":"1"}
     * state : 1
     */

    private String message;
    private DataBean data;
    private int state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * pubTime :
         * picPath : https://api.madeinchanba.com/web.files/2018-08-27/201808270953061535334786207_f4q9q.jpg
         * content :
         * id : 2c9f4a87661a61c401661f1f3920004f
         * author : 浐灞智造
         * title : 金融服务4
         * ftitle :
         * digest :
         * type : 1
         */

        private String pubTime;
        private String picPath;
        private String content;
        private String id;
        private String author;
        private String title;
        private String ftitle;
        private String digest;
        private String type;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFtitle() {
            return ftitle;
        }

        public void setFtitle(String ftitle) {
            this.ftitle = ftitle;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
