package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 通知公告/轮播图/推荐
 */
public class NoticeBannerEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"slider":[{"pubTime":"2018-06-28","id":"4d96365cdaf74a8c9f99fc9797db9f30","author":"","title":"创业大咖秀","linkUrl":"4d96365cdaf74a8c9f99fc9797db9f30","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-06-30/201806301600361530345636484_u7p8a.png","digest":"","type":"2","digest0":"","digest1":""}],"index_tj":[{"picPath":"https://api.madeinchanba.com/web.files/2018-06-28/201806281745411530179141569_y3c6u.png","id":"931604692869074506","description":"","type":"0","productName":"人才贷"}],"platformNotice":[{"pubTime":"2018-06-28","id":"bc162a80b25d4e098535815c3fe43b13","author":"","title":"让我们争做新时代的亚当·斯密","linkUrl":"","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-06-28/201806281743361530179016779_x1t9w.jpg","digest":"","type":"0","digest0":"","digest1":""},{"pubTime":"2018-06-28","id":"7977ea744a604b0b91c81de9d2f4ca73","author":"","title":"浐灞智造小程序上线了","linkUrl":"https://mp.weixin.qq.com/s/e2luwTV4f4uWYGECxRWUGg","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-06-28/201806281742041530178924204_t7i9q.jpg","digest":"","type":"1","digest0":"","digest1":""},{"pubTime":"2018-06-28","id":"eb4093343da34871987f6531267b3fce","author":"","title":"2018年公益合作伙伴招募","linkUrl":"https://mp.weixin.qq.com/s/nn7Fk1SsUNQzUpa09D305A","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-06-28/201806281738221530178702961_v3p3c.jpg","digest":"","type":"1","digest0":"","digest1":""}]}
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
        private List<SliderBean> slider;
        private List<IndexTjBean> index_tj;
        private List<PlatformNoticeBean> platformNotice;

        public List<SliderBean> getSlider() {
            return slider;
        }

        public void setSlider(List<SliderBean> slider) {
            this.slider = slider;
        }

        public List<IndexTjBean> getIndex_tj() {
            return index_tj;
        }

        public void setIndex_tj(List<IndexTjBean> index_tj) {
            this.index_tj = index_tj;
        }

        public List<PlatformNoticeBean> getPlatformNotice() {
            return platformNotice;
        }

        public void setPlatformNotice(List<PlatformNoticeBean> platformNotice) {
            this.platformNotice = platformNotice;
        }

        public static class SliderBean {
            /**
             * pubTime : 2018-06-28
             * id : 4d96365cdaf74a8c9f99fc9797db9f30
             * author :
             * title : 创业大咖秀
             * linkUrl : 4d96365cdaf74a8c9f99fc9797db9f30
             * astitle :
             * filePath : https://api.madeinchanba.com/web.files/2018-06-30/201806301600361530345636484_u7p8a.png
             * digest :
             * type : 2
             * digest0 :
             * digest1 :
             */

            private String pubTime;
            private String id;
            private String author;
            private String title;
            private String linkUrl;
            private String astitle;
            private String filePath;
            private String digest;
            private String type;
            private String digest0;
            private String digest1;

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

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getAstitle() {
                return astitle;
            }

            public void setAstitle(String astitle) {
                this.astitle = astitle;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
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

            public String getDigest0() {
                return digest0;
            }

            public void setDigest0(String digest0) {
                this.digest0 = digest0;
            }

            public String getDigest1() {
                return digest1;
            }

            public void setDigest1(String digest1) {
                this.digest1 = digest1;
            }
        }

        public static class IndexTjBean {
            /**
             * picPath : https://api.madeinchanba.com/web.files/2018-06-28/201806281745411530179141569_y3c6u.png
             * id : 931604692869074506
             * description :
             * type : 0
             * productName : 人才贷
             */

            private String picPath;
            private String id;
            private String description;
            private String type;
            private String productName;

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
        }

        public static class PlatformNoticeBean {
            /**
             * pubTime : 2018-06-28
             * id : bc162a80b25d4e098535815c3fe43b13
             * author :
             * title : 让我们争做新时代的亚当·斯密
             * linkUrl :
             * astitle :
             * filePath : https://api.madeinchanba.com/web.files/2018-06-28/201806281743361530179016779_x1t9w.jpg
             * digest :
             * type : 0
             * digest0 :
             * digest1 :
             */

            private String pubTime;
            private String id;
            private String author;
            private String title;
            private String linkUrl;
            private String astitle;
            private String filePath;
            private String digest;
            private String type;
            private String digest0;
            private String digest1;

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

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getAstitle() {
                return astitle;
            }

            public void setAstitle(String astitle) {
                this.astitle = astitle;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
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

            public String getDigest0() {
                return digest0;
            }

            public void setDigest0(String digest0) {
                this.digest0 = digest0;
            }

            public String getDigest1() {
                return digest1;
            }

            public void setDigest1(String digest1) {
                this.digest1 = digest1;
            }
        }
    }
}
