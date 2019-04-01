package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/3/27.
 */

public class zwInfo {

    /**
     * code : 10701
     * deleted : 0
     * id : 10701
     * isParent : true
     * name : 销售|客服|市场
     * newStyle : 1
     * note :
     * optime : {"date":8,"day":5,"hours":11,"minutes":16,"month":2,"nanos":561000000,"seconds":25,"time":1552014985561,"timezoneOffset":-480,"year":119}
     * parentId : 107
     * path : #1#107#10701#
     * sort : 1
     */

    private String code;
    private String deleted;
    private String id;
    private boolean isParent;
    private String name;
    private String newStyle;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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

    public String getNewStyle() {
        return newStyle;
    }

    public void setNewStyle(String newStyle) {
        this.newStyle = newStyle;
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
         * date : 8
         * day : 5
         * hours : 11
         * minutes : 16
         * month : 2
         * nanos : 561000000
         * seconds : 25
         * time : 1552014985561
         * timezoneOffset : -480
         * year : 119
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
