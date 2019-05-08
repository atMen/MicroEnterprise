package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/4/28.
 */

public class bankalInfo {

    /**
     * content : [{"apply":"0","author":"小豪","bankId":"ff8080816a629963016a629c6e64002c","bankName":"捷凯金融服务有限公司","content":"","createDate":"2019-04-28 15:32:45","createUserId":"","id":"ff8080816a62a744016a62dc4ae40009","img":"","imgName":"","optime":"2019-04-28 15:33:42","publish":"1","shareTimes":0,"state":"0","subtitle":"小标题","title":"火龙果案例"},{"apply":"0","author":"李四","bankId":"ff8080816a629963016a629c6e64002c","bankName":"捷凯金融服务有限公司","content":"","createDate":"2019-04-28 14:39:27","createUserId":"","id":"ff8080816a62a744016a62ab7e990003","img":"","imgName":"","optime":"2019-04-28 15:33:42","publish":"1","shareTimes":0,"state":"0","subtitle":"Rabbit Finance:泰国最大的金融产品比较门户","title":"互联网金融p2p"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 2
     * size : 15
     * sort : {}
     * totalElements : 2
     * totalPages : 1
     */

    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private SortBean sort;
    private int totalElements;
    private int totalPages;
    private List<ContentBean> content;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SortBean getSort() {
        return sort;
    }

    public void setSort(SortBean sort) {
        this.sort = sort;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class SortBean {
    }

    public static class ContentBean {
        /**
         * apply : 0
         * author : 小豪
         * bankId : ff8080816a629963016a629c6e64002c
         * bankName : 捷凯金融服务有限公司
         * content :
         * createDate : 2019-04-28 15:32:45
         * createUserId :
         * id : ff8080816a62a744016a62dc4ae40009
         * img :
         * imgName :
         * optime : 2019-04-28 15:33:42
         * publish : 1
         * shareTimes : 0
         * state : 0
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
}
