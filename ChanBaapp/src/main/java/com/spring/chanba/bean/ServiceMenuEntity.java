package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 工商服务
 */
public class ServiceMenuEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"picPath":"https://api.madeinchanba.com/web.files/2018-09-27/201809270941511538012511658_j0u2w.png","content":"","id":"2c9f4a87661a61c401661f1dbe56004e","author":"浐灞智造","title":"工商服务5","digest":"","type":"0","addTime":"2018-09-28"},{"picPath":"https://api.madeinchanba.com/web.files/2018-09-27/201809270944181538012658332_c2z8e.png","content":"","id":"2c9f4a8766155aa2016618b2c73d0001","author":"浐灞智造","title":"资质办理哈哈哈哈或或或或或或或或或","digest":"","type":"0","addTime":"2018-09-28"},{"picPath":"https://api.madeinchanba.com/web.files/2018-09-27/201809270943371538012617930_f8n3z.png","content":"","id":"2c9f4a8766155aa2016618b229070000","author":"浐灞智造","title":"三证合一","digest":"","type":"0","addTime":"2018-09-27"},{"picPath":"https://api.madeinchanba.com/web.files/2018-09-27/201809270942271538012547996_q8r4c.png","content":"<p>1.代理工商注册（新公司注册、垫资注册、个体工商注册、分公司注册、旧公司买卖、公司变更登记、大额企业注册、各项资质审批、大额增资变更、过桥资金证明、摆帐显帐保函、特色服务注册、企业疑难年检、疑难名称核准）；<br/>2.代理企业变更（公司注册设立、年检、增资、变更、转让、注销）；<br/>3.会计代理（兼职会计、会计代理记账、申报纳税代办、办理一般纳税人日常税务、清算审计代办）；<br/>4.财会服务（企业纳税申请、企业年检手续、企业注销、企业破产清算、税务减免审批、财会人员培训）；<br/>5.验资、审计、资产评估；<br/>6.代刻各类财务上网公章。<\/p>","id":"2c9f4a8765f9deab0165fa05d1b0000b","author":"浐灞智造","title":"名称变更","digest":"","type":"0","addTime":"2018-09-27"},{"picPath":"https://api.madeinchanba.com/web.files/2018-09-27/201809270941511538012511658_j0u2w.png","content":"<p>1.代理工商注册（新公司注册、垫资注册、个体工商注册、分公司注册、旧公司买卖、公司变更登记、大额企业注册、各项资质审批、大额增资变更、过桥资金证明、摆帐显帐保函、特色服务注册、企业疑难年检、疑难名称核准）；<br/>2.代理企业变更（公司注册设立、年检、增资、变更、转让、注销）；<br/>3.会计代理（兼职会计、会计代理记账、申报纳税代办、办理一般纳税人日常税务、清算审计代办）；<br/>4.财会服务（企业纳税申请、企业年检手续、企业注销、企业破产清算、税务减免审批、财会人员培训）；<br/>5.验资、审计、资产评估；<br/>6.代刻各类财务上网公章。<\/p>","id":"2c9f4a8765f9deab0165fa0642f7000c","author":"浐灞智造","title":"公司注册","digest":"","type":"0","addTime":"2018-09-27"}]
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
         * picPath : https://api.madeinchanba.com/web.files/2018-09-27/201809270941511538012511658_j0u2w.png
         * content :
         * id : 2c9f4a87661a61c401661f1dbe56004e
         * author : 浐灞智造
         * title : 工商服务5
         * digest :
         * type : 0
         * addTime : 2018-09-28
         */

        private String picPath;
        private String content;
        private String id;
        private String author;
        private String title;
        private String digest;
        private String type;
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

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
