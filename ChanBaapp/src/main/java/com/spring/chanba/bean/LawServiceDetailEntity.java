package com.spring.chanba.bean;

import java.io.Serializable;

/**
 * 详情
 */
public class LawServiceDetailEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241644261535100266401_h5g1u.jpg"}
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
         * picPath : https://api.madeinchanba.com/web.files/2018-08-24/201808241644261535100266401_h5g1u.jpg
         * content : <p style="text-indent: 2em;"></p>
         * id : 2c9f4a87656ad5b401656b22e7e50003
         * author : 廖群律师
         * title : 最高人民法院发出通知要求 依法妥善审理民间借贷纠纷案件
         * ftitle :
         * digest :
         * addTime : 2018-08-24
         */

        private String picPath;
        private String content;
        private String id;
        private String author;
        private String title;
        private String ftitle;
        private String digest;
        private String addTime;

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

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
