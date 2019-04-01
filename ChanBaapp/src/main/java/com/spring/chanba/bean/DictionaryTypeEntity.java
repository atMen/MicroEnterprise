package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目分类
 */
public class DictionaryTypeEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"checked":false,"code":"","desc":"","id":"10601","isParent":true,"name":"商业","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118},"parentid":"106","parentname":"","path":"#1#106#10601#","sort":1,"value":""},{"checked":false,"code":"","desc":"","id":"10602","isParent":true,"name":"励志","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118},"parentid":"106","parentname":"","path":"#1#106#10602#","sort":2,"value":""},{"checked":false,"code":"","desc":"","id":"10603","isParent":true,"name":"金融","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118},"parentid":"106","parentname":"","path":"#1#106#10603#","sort":3,"value":""},{"checked":false,"code":"","desc":"","id":"10604","isParent":true,"name":"市场","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118},"parentid":"106","parentname":"","path":"#1#106#10604#","sort":4,"value":""},{"checked":false,"code":"","desc":"","id":"10605","isParent":true,"name":"职场","optime":{"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118},"parentid":"106","parentname":"","path":"#1#106#10605#","sort":5,"value":""}]
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
         * checked : false
         * code :
         * desc :
         * id : 10601
         * isParent : true
         * name : 商业
         * optime : {"date":20,"day":4,"hours":17,"minutes":0,"month":8,"nanos":0,"seconds":34,"time":1537434034000,"timezoneOffset":-480,"year":118}
         * parentid : 106
         * parentname :
         * path : #1#106#10601#
         * sort : 1
         * value :
         */

        private boolean checked;
        private String code;
        private String desc;
        private String id;
        private boolean isParent;
        private String name;
        private OptimeBean optime;
        private String parentid;
        private String parentname;
        private String path;
        private int sort;
        private String value;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OptimeBean getOptime() {
            return optime;
        }

        public void setOptime(OptimeBean optime) {
            this.optime = optime;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getParentname() {
            return parentname;
        }

        public void setParentname(String parentname) {
            this.parentname = parentname;
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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static class OptimeBean {
            /**
             * date : 20
             * day : 4
             * hours : 17
             * minutes : 0
             * month : 8
             * nanos : 0
             * seconds : 34
             * time : 1537434034000
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
