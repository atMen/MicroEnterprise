package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 培训报名
 */
public class TrainEnrolmentEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241513221535094802566_c6h9s.jpg","pubTime":"2018-09-27 17:41:44","id":"2c9f4a876624e10e0166282809b20008","goodsId":"2c9f4a8765f9deab0165fa3456e4001a","title":"创业培训1啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦","stateName":"审核中","state":"0"},{"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241519511535095191768_n1e6i.jpg","pubTime":"2018-09-27 17:41:51","id":"2c9f4a876624e10e0166283a961c0009","goodsId":"2c9f4a8765f9deab0165fa3b9262001b","title":"创业培训2","stateName":"审核中","state":"0"}]
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picPath : https://api.madeinchanba.com/web.files/2018-08-24/201808241513221535094802566_c6h9s.jpg
         * pubTime : 2018-09-27 17:41:44
         * id : 2c9f4a876624e10e0166282809b20008
         * goodsId : 2c9f4a8765f9deab0165fa3456e4001a
         * title : 创业培训1啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦
         * stateName : 审核中
         * state : 0
         */

        private String picPath;
        private String pubTime;
        private String id;
        private String goodsId;
        private String title;
        private String stateName;
        private String state;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
