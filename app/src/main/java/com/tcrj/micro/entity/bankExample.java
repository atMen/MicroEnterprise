package com.tcrj.micro.entity;

import java.io.Serializable;

/**
 * Created by leict on 2019/4/28.
 */

public class bankExample implements Serializable{

    /**
     * apply : 1
     * author : 小豪
     * bankId : ff8080816a629963016a629c6e64002c
     * bankName : 捷凯金融服务有限公司
     * content : <p><span style="text-align: left; color: rgb(25, 25, 25); text-transform: none; text-indent: 0px; letter-spacing: normal; font-family: &quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif; font-size: 16px; font-style: normal; font-weight: 400; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; background-color: rgb(255, 255, 255); font-variant-ligatures: normal; font-variant-caps: normal; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;">玩转理财通 粉丝主要为腾讯理财通用户，账号前期以活动为主，活跃度不高，后台几乎无人问津。账号自2015年5月运营以来，以激活老粉和吸引新粉为目的。主要以财经热点解析、理财干货、产品类目等内容帮助粉丝完成“让理财生活更轻松”的目标</span>&nbsp;</p>
     * createDate : 2019-04-28 15:32:45
     * createUserId : 1
     * id : ff8080816a62a744016a62dc4ae40009
     * img : /uploadfile//2019-04-28/20190428033231368.png
     * imgName : 天诚云app下载码.png
     * optime : 2019-04-28 15:32:45
     * publish : 1
     * shareTimes : 0
     * state : 2
     * subtitle : 小标题
     * title : 火龙果案例
     */

    private String apply;
    private String author;
    private String bankId;
    private String bankName;
    private String content;
    private String createDate;
    private String createUserId;
    private String id;
    private String img;
    private String imgName;
    private String optime;
    private String publish;
    private int shareTimes;
    private String state;
    private String subtitle;
    private String title;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
