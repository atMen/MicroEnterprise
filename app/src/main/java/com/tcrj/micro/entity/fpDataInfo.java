package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/29.
 */

public class fpDataInfo {


    /**
     * content : [{"cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","cityName":"西安市","countyId":"57134b7a55fb4d0db2b7283abc9733f1","countyName":"未央区","enable":"0","homeName":"2","id":"ff808081695ac11401695ac602870003","optime":"2019-03-29 15:35:49","poorReason":"缺乏劳动力","townName":"好家村","userId":""}]
     * first : true
     * last : false
     * number : 0
     * numberOfElements : 1
     * size : 1
     * sort : {}
     * totalElements : 3
     * totalPages : 3
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
         * cityId : 3abb1c09ea4d4da29e0e431ad2d6f76c
         * cityName : 西安市
         * countyId : 57134b7a55fb4d0db2b7283abc9733f1
         * countyName : 未央区
         * enable : 0
         * homeName : 2
         * id : ff808081695ac11401695ac602870003
         * optime : 2019-03-29 15:35:49
         * poorReason : 缺乏劳动力
         * townName : 好家村
         * userId :
         */

        private String cityId;
        private String cityName;
        private String countyId;
        private String countyName;
        private String enable;
        private String homeName;
        private String id;
        private String optime;
        private String poorReason;
        private String townName;
        private String userId;

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCountyId() {
            return countyId;
        }

        public void setCountyId(String countyId) {
            this.countyId = countyId;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public String getHomeName() {
            return homeName;
        }

        public void setHomeName(String homeName) {
            this.homeName = homeName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public String getPoorReason() {
            return poorReason;
        }

        public void setPoorReason(String poorReason) {
            this.poorReason = poorReason;
        }

        public String getTownName() {
            return townName;
        }

        public void setTownName(String townName) {
            this.townName = townName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
