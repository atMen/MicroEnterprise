package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

public class ServiceApplyListEntity implements Serializable {
    /**
     * message : 操作成功
     * data : [{"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241527191535095639150_b1i3j.jpg","content":"<p style=\"text-indent: 2em;\">金融服务是指金融机构运用货币交易手段融通有价物品，向金融活动参与者和顾客提供的共同受益、获得满足的活动。按照世界贸易组织附件的内容，金融服务的提供者包括下列类型机构：保险及其相关服务，还包括所有银行和其他金融服务（保险除外）。<\/p><p style=\"text-indent: 2em;\">广义上的金融服务，是指整个金融业发挥其多种功能以促进经济与社会的发展。具体来说，金融服务是指金融机构通过开展业务活动为客户提供包括融资投资、储蓄、信贷、结算、证券买卖、商业保险和金融信息咨询等多方面的服务。增强金融服务意识，提高金融服务水平，对于加快推进我国的现代金融制度建设，改进金融机构经营管理，增强金融业竞争力，更好地促进经济和社会发展，具有十分重要的意义。<br/><\/p><p>&nbsp;<\/p>","id":"2c9f4a8765f9deab0165fac83b17002d","author":"浐灞智造","title":"金融服务2","state":"0","applyTime":"2018-10-11","digest":"","type":"1"}]
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
         * picPath : https://api.madeinchanba.com/web.files/2018-08-24/201808241527191535095639150_b1i3j.jpg
         * content : <p style="text-indent: 2em;">金融服务是指金融机构运用货币交易手段融通有价物品，向金融活动参与者和顾客提供的共同受益、获得满足的活动。按照世界贸易组织附件的内容，金融服务的提供者包括下列类型机构：保险及其相关服务，还包括所有银行和其他金融服务（保险除外）。</p><p style="text-indent: 2em;">广义上的金融服务，是指整个金融业发挥其多种功能以促进经济与社会的发展。具体来说，金融服务是指金融机构通过开展业务活动为客户提供包括融资投资、储蓄、信贷、结算、证券买卖、商业保险和金融信息咨询等多方面的服务。增强金融服务意识，提高金融服务水平，对于加快推进我国的现代金融制度建设，改进金融机构经营管理，增强金融业竞争力，更好地促进经济和社会发展，具有十分重要的意义。<br/></p><p>&nbsp;</p>
         * id : 2c9f4a8765f9deab0165fac83b17002d
         * author : 浐灞智造
         * title : 金融服务2
         * state : 0
         * applyTime : 2018-10-11
         * digest :
         * type : 1
         */

        private String picPath;
        private String content;
        private String id;
        private String author;
        private String title;
        private String state;
        private String applyTime;
        private String digest;
        private String type;

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
