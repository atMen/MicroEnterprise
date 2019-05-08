package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/27.
 */

public class grzpListInfo {

    /**
     * errorCode : SUCCESS
     * data : {"content":[{"id":"ff808081695aef420169801e47a70124","optime":"2019-03-15T07:13:37.467+0000","userId":"ff808081695aef42016976055d0400f8","resumeId":"ff808081695aef420169801ab342011e","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"小曲","jobId":"ff808081690dce960169466b11070018","jobName":"2","applyDate":"2019-03-15 14:51:01","replyContent":"合适人选","replyDate":"2019-03-15 15:13:17","status":"1","isSuitable":"1"},{"id":"ff80808169467c60016950a2f0a60046","optime":"2019-03-15T04:00:03.219+0000","userId":"ff808081673022e201675823d38a0000","resumeId":"ff808081690dce96016928915e8a0001","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"测试1","jobId":"ff808081690dce960169466b11070018","jobName":"2","applyDate":"2019-03-06 09:34:09","replyContent":"lk ","replyDate":"2019-03-13 16:31:41","status":"1","isSuitable":"1"},{"id":"ff80808169467c6001694b6a98ca0039","optime":"2019-03-15T03:59:28.159+0000","userId":"ff808081673022e201675823d38a0000","resumeId":"ff808081690dce96016928915e8a0001","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"测试1","jobId":"ff80808169467c60016946807d460002","jobName":"测试3.4","applyDate":"2019-03-05 09:14:30","replyContent":"合适","replyDate":"2019-03-05 09:18:46","status":"2","isSuitable":"2"},{"id":"ff808081695aef42016979bd69e40105","optime":"2019-03-14T01:07:30.404+0000","userId":"ff8080816488a9e1016488af8ed00024","resumeId":"ff808081695aef42016979bc35ed0102","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"董涛","jobId":"ff80808169467c60016946807d460002","jobName":"测试3.4","applyDate":"2019-03-14 09:07:30","status":"0","hasSend":"1","isSuitable":"0"},{"id":"ff808081695aef42016979bd29070103","optime":"2019-03-14T01:07:13.798+0000","userId":"ff8080816488a9e1016488af8ed00024","resumeId":"ff808081695aef42016979bc35ed0102","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"董涛","jobId":"ff808081690dce960169466b11070018","jobName":"2","applyDate":"2019-03-14 09:07:13","status":"0","hasSend":"1","isSuitable":"0"},{"id":"ff80808169467c6001694c8427530040","optime":"2019-03-05T06:22:03.090+0000","userId":"ff808081673022e201675823d38a0000","resumeId":"ff808081690dce96016928915e8a0001","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"测试1","jobId":"ff80808169467c60016946807d460002","jobName":"测试3.4","applyDate":"2019-03-05 14:22:03","status":"0","hasSend":"1","isSuitable":"0"},{"id":"ff80808169467c6001694683c7630005","optime":"2019-03-04T02:30:37.283+0000","userId":"ff808081673022e201675823d38a0000","resumeId":"ff808081690dce96016928915e8a0001","enterpriseId":"ff8080816488a9e1016488ae7a450003","sendName":"测试1","jobId":"ff80808169467c60016946807d460002","jobName":"测试3.4","applyDate":"2019-03-04 10:23:55","replyContent":"请与明天上午9点来我公司面试","replyDate":"2019-03-04 10:29:37","status":"1","isSuitable":"1"}],"last":true,"totalPages":1,"totalElements":7,"sort":[{"direction":"DESC","property":"optime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}],"first":true,"numberOfElements":7,"size":20,"number":0}
     */

    private String errorCode;
    private DataBean data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
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
             * applyDate : 2019-03-15 14:51:01
             * enterpriseId : ff8080816488a9e1016488ae7a450003
             * hasSend :
             * id : ff808081695aef420169801e47a70124
             * isSuitable : 1
             * jobId : ff808081690dce960169466b11070018
             * jobName : 2
             * optime : 2019-04-02 09:28:43
             * replyContent : 合适人选
             * replyDate : 2019-03-15 15:13:17
             * resumeId : ff808081695aef420169801ab342011e
             * sendName : 小曲
             * status : 1
             * userId : ff808081695aef42016976055d0400f8
             */

            private String applyDate;
            private String enterpriseId;
            private String hasSend;
            private String id;
            private String isSuitable;
            private String jobId;
            private String jobName;
            private String optime;
            private String replyContent;
            private String replyDate;
            private String resumeId;
            private String sendName;
            private String status;
            private String userId;

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
            }

            public String getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(String enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public String getHasSend() {
                return hasSend;
            }

            public void setHasSend(String hasSend) {
                this.hasSend = hasSend;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsSuitable() {
                return isSuitable;
            }

            public void setIsSuitable(String isSuitable) {
                this.isSuitable = isSuitable;
            }

            public String getJobId() {
                return jobId;
            }

            public void setJobId(String jobId) {
                this.jobId = jobId;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getOptime() {
                return optime;
            }

            public void setOptime(String optime) {
                this.optime = optime;
            }

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }

            public String getReplyDate() {
                return replyDate;
            }

            public void setReplyDate(String replyDate) {
                this.replyDate = replyDate;
            }

            public String getResumeId() {
                return resumeId;
            }

            public void setResumeId(String resumeId) {
                this.resumeId = resumeId;
            }

            public String getSendName() {
                return sendName;
            }

            public void setSendName(String sendName) {
                this.sendName = sendName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
