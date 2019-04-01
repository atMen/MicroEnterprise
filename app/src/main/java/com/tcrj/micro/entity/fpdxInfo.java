package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/14.
 */

public class fpdxInfo {


    /**
     * errorcode : 9999
     * message : 操作成功。
     * data : {"content":[{"id":"ff808081695ac11401695ac602870003","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"好家村","homeName":"2","poorReason":"缺乏劳动力","enable":"0","cityName":"西安市","countyName":"未央区"},{"id":"ff808081695ac11401695ac644af0004","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"李家村","homeName":"晓明","poorReason":"建陵","enable":"0","cityName":"西安市","countyName":"未央区"},{"id":"ff808081695aef4201695b256dcb0015","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"名园社区","homeName":"西西","poorReason":"无劳动力\n","enable":"0","cityName":"西安市","countyName":"未央区"}],"last":true,"totalPages":1,"totalElements":3,"first":true,"sort":[{"direction":"ASC","property":"optime","ignoreCase":false,"nullHandling":"NATIVE","ascending":true}],"numberOfElements":3,"size":20,"number":0}
     */

    private String errorcode;
    private String message;
    private DataBean data;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

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

    public static class DataBean {
        /**
         * content : [{"id":"ff808081695ac11401695ac602870003","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"好家村","homeName":"2","poorReason":"缺乏劳动力","enable":"0","cityName":"西安市","countyName":"未央区"},{"id":"ff808081695ac11401695ac644af0004","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"李家村","homeName":"晓明","poorReason":"建陵","enable":"0","cityName":"西安市","countyName":"未央区"},{"id":"ff808081695aef4201695b256dcb0015","optime":"2019-03-29T00:59:52.175+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"名园社区","homeName":"西西","poorReason":"无劳动力\n","enable":"0","cityName":"西安市","countyName":"未央区"}]
         * last : true
         * totalPages : 1
         * totalElements : 3
         * first : true
         * sort : [{"direction":"ASC","property":"optime","ignoreCase":false,"nullHandling":"NATIVE","ascending":true}]
         * numberOfElements : 3
         * size : 20
         * number : 0
         */

        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
            /**
             * id : ff808081695ac11401695ac602870003
             * optime : 2019-03-29T00:59:52.175+0000
             * cityId : 3abb1c09ea4d4da29e0e431ad2d6f76c
             * countyId : 57134b7a55fb4d0db2b7283abc9733f1
             * townName : 好家村
             * homeName : 2
             * poorReason : 缺乏劳动力
             * enable : 0
             * cityName : 西安市
             * countyName : 未央区
             */

            private String id;
            private String optime;
            private String cityId;
            private String countyId;
            private String townName;
            private String homeName;
            private String poorReason;
            private String enable;
            private String cityName;
            private String countyName;

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

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCountyId() {
                return countyId;
            }

            public void setCountyId(String countyId) {
                this.countyId = countyId;
            }

            public String getTownName() {
                return townName;
            }

            public void setTownName(String townName) {
                this.townName = townName;
            }

            public String getHomeName() {
                return homeName;
            }

            public void setHomeName(String homeName) {
                this.homeName = homeName;
            }

            public String getPoorReason() {
                return poorReason;
            }

            public void setPoorReason(String poorReason) {
                this.poorReason = poorReason;
            }

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCountyName() {
                return countyName;
            }

            public void setCountyName(String countyName) {
                this.countyName = countyName;
            }
        }

        public static class SortBean {
            /**
             * direction : ASC
             * property : optime
             * ignoreCase : false
             * nullHandling : NATIVE
             * ascending : true
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean ascending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }
        }
    }
}
