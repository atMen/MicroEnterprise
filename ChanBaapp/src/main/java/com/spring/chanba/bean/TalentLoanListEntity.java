package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

public class TalentLoanListEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"id":"2c9f4a87662945820166864a038e0016","goodsId":"931604692869074506","stateName":"审核中","state":"0","applyTime":"2018-10-18 16:28:09","goodsName":"人才贷"}]
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
         * id : 2c9f4a87662945820166864a038e0016
         * goodsId : 931604692869074506
         * stateName : 审核中
         * state : 0
         * applyTime : 2018-10-18 16:28:09
         * goodsName : 人才贷
         */

        private String id;
        private String goodsId;
        private String stateName;
        private String state;
        private String applyTime;
        private String goodsName;

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

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }
}
