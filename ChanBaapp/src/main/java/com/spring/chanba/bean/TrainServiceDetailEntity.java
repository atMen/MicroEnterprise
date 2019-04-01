package com.spring.chanba.bean;

import java.io.Serializable;

/**
 * 培训服务:详情
 */
public class TrainServiceDetailEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241513221535094802566_c6h9s.jpg","pubTime":"2018-09-27 17:41:44","content":"<p>创业培训(Entrepreneurship training) 创业培训是对具有创办小企业意向的人员和小企业经营管理者进行企业创办能力、市场经营素质等方面的培训，并对他们在企业开办、经营过程中给予一定的政策指导，其目的是通过提高企业创办者创业的心理、管理、经营等素质，增强参与市场竞争和驾驭市场的应变能力，使小企业创办者在成功地创办企业，解决自身就业问题的同时，创造和增加社会就业岗位，帮助更多的人实现就业或再就业。<\/p>","id":"2c9f4a8765f9deab0165fa3456e4001a","author":"浐灞智造","title":"创业培训1啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦","ftitle":"副标题哈哈哈哈哈","digest":"","appCount":5,"addTime":"2018-09-28 15:12:25"}
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
         * picPath : https://api.madeinchanba.com/web.files/2018-08-24/201808241513221535094802566_c6h9s.jpg
         * pubTime : 2018-09-27 17:41:44
         * content : <p>创业培训(Entrepreneurship training) 创业培训是对具有创办小企业意向的人员和小企业经营管理者进行企业创办能力、市场经营素质等方面的培训，并对他们在企业开办、经营过程中给予一定的政策指导，其目的是通过提高企业创办者创业的心理、管理、经营等素质，增强参与市场竞争和驾驭市场的应变能力，使小企业创办者在成功地创办企业，解决自身就业问题的同时，创造和增加社会就业岗位，帮助更多的人实现就业或再就业。</p>
         * id : 2c9f4a8765f9deab0165fa3456e4001a
         * author : 浐灞智造
         * title : 创业培训1啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦
         * ftitle : 副标题哈哈哈哈哈
         * digest :
         * appCount : 5
         * addTime : 2018-09-28 15:12:25
         */

        private String picPath;
        private String pubTime;
        private String content;
        private String id;
        private String author;
        private String title;
        private String ftitle;
        private String digest;
        private int appCount;
        private String addTime;

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
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

        public int getAppCount() {
            return appCount;
        }

        public void setAppCount(int appCount) {
            this.appCount = appCount;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
