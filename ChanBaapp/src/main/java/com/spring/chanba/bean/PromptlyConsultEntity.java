package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 立即咨询
 */
public class PromptlyConsultEntity implements Serializable {


    /**
     * message : 操作成功
     * data : [{"id":"2c9f4a8766155aa20166198ff33d0004","answerContent":"暂未回复","consultContent":"如何小额贷款","consultTime":"2018-09-27 13:45:56","answerTime":"暂未回复"}]
     * state : 1
     * totalPages : 1
     */

    private String message;
    private int state;
    private int totalPages;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2c9f4a8766155aa20166198ff33d0004
         * answerContent : 暂未回复
         * consultContent : 如何小额贷款
         * consultTime : 2018-09-27 13:45:56
         * answerTime : 暂未回复
         */

        private String id;
        private String answerContent;
        private String consultContent;
        private String consultTime;
        private String answerTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }

        public String getConsultContent() {
            return consultContent;
        }

        public void setConsultContent(String consultContent) {
            this.consultContent = consultContent;
        }

        public String getConsultTime() {
            return consultTime;
        }

        public void setConsultTime(String consultTime) {
            this.consultTime = consultTime;
        }

        public String getAnswerTime() {
            return answerTime;
        }

        public void setAnswerTime(String answerTime) {
            this.answerTime = answerTime;
        }
    }
}
