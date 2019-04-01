package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Banner实体类
 */
public class BannerListEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"pubTime":"2018-09-24","id":"25e8e68f475d4702a6126727f14e8c47","author":"","title":"轮播2","linkUrl":"","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-09-24/201809241721311537780891813_i3w7o.png","digest":"","type":"0","digest0":"","digest1":""},{"pubTime":"2018-09-24","id":"d31f7df5994b4bf3bc7a8dc2f597fd14","author":"","title":"轮播1","linkUrl":"","astitle":"","filePath":"https://api.madeinchanba.com/web.files/2018-09-24/201809241720481537780848075_o3t4n.png","digest":"","type":"0","digest0":"","digest1":""}]
     * state : 1
     * subData : [{"code":"CompanyNews","id":"7VVmS","isParent":true,"isShow":"0","keywords":"","modelIds":"","name":"企业动态","note":"","optime":{"date":28,"day":4,"hours":16,"minutes":27,"month":5,"nanos":0,"seconds":30,"time":1530174450000,"timezoneOffset":-480,"year":118},"parentId":"2","path":"#0#2#7VVmS#","sort":1},{"code":"","id":"2","isParent":true,"isShow":"0","keywords":"","modelIds":"","name":"文章","note":"","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":30,"time":1537434030000,"timezoneOffset":-480,"year":118},"parentId":"0","path":"#0#2#","sort":2},{"code":"IndustryNews","id":"uuPWl","isParent":true,"isShow":"0","keywords":"","modelIds":"","name":"行业资讯","note":"","optime":{"date":28,"day":4,"hours":16,"minutes":29,"month":5,"nanos":0,"seconds":51,"time":1530174591000,"timezoneOffset":-480,"year":118},"parentId":"2","path":"#0#2#uuPWl#","sort":2},{"code":"BusinessKnowledge","id":"KxSHy","isParent":true,"isShow":"0","keywords":"","modelIds":"","name":"创业知识","note":"","optime":{"date":28,"day":4,"hours":16,"minutes":29,"month":5,"nanos":0,"seconds":10,"time":1530174550000,"timezoneOffset":-480,"year":118},"parentId":"2","path":"#0#2#KxSHy#","sort":3},{"code":"PolicyComment","id":"zseDz","isParent":true,"isShow":"0","keywords":"","modelIds":"","name":"政策解读","note":"","optime":{"date":28,"day":4,"hours":16,"minutes":31,"month":5,"nanos":0,"seconds":35,"time":1530174695000,"timezoneOffset":-480,"year":118},"parentId":"2","path":"#0#2#zseDz#","sort":4}]
     */

    private String message;
    private int state;
    private List<DataBean> data;
    private List<SubDataBean> subData;

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

    public List<SubDataBean> getSubData() {
        return subData;
    }

    public void setSubData(List<SubDataBean> subData) {
        this.subData = subData;
    }

    public static class DataBean {
        /**
         * pubTime : 2018-09-24
         * id : 25e8e68f475d4702a6126727f14e8c47
         * author :
         * title : 轮播2
         * linkUrl :
         * astitle :
         * filePath : https://api.madeinchanba.com/web.files/2018-09-24/201809241721311537780891813_i3w7o.png
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

    public static class SubDataBean {
        /**
         * code : CompanyNews
         * id : 7VVmS
         * isParent : true
         * isShow : 0
         * keywords :
         * modelIds :
         * name : 企业动态
         * note :
         * optime : {"date":28,"day":4,"hours":16,"minutes":27,"month":5,"nanos":0,"seconds":30,"time":1530174450000,"timezoneOffset":-480,"year":118}
         * parentId : 2
         * path : #0#2#7VVmS#
         * sort : 1
         */

        private String code;
        private String id;
        private boolean isParent;
        private String isShow;
        private String keywords;
        private String modelIds;
        private String name;
        private String note;
        private OptimeBean optime;
        private String parentId;
        private String path;
        private int sort;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsParent() {
            return isParent;
        }

        public void setIsParent(boolean isParent) {
            this.isParent = isParent;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getModelIds() {
            return modelIds;
        }

        public void setModelIds(String modelIds) {
            this.modelIds = modelIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public OptimeBean getOptime() {
            return optime;
        }

        public void setOptime(OptimeBean optime) {
            this.optime = optime;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public static class OptimeBean {
            /**
             * date : 28
             * day : 4
             * hours : 16
             * minutes : 27
             * month : 5
             * nanos : 0
             * seconds : 30
             * time : 1530174450000
             * timezoneOffset : -480
             * year : 118
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
