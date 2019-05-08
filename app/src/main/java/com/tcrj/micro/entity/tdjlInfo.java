package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/4/30.
 */

public class tdjlInfo {

    /**
     * content : [{"applyDate":"2019-04-28 16:33:52","enterpriseId":"ff8080816488a9e1016488ae7a450003","hasSend":"1","id":"402894b36a62feda016a63143f1e0058","isSuitable":"0","jobId":"ff8080816a2e20a6016a2f33a3dc0008","jobName":"课程顾问（电话销售）","optime":"2019-04-28 16:33:52","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"1","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-28 16:33:22","enterpriseId":"ff8080816488a9e1016488ae7a450003","hasSend":"1","id":"402894b36a62feda016a6313c99a0052","isSuitable":"0","jobId":"ff8080816998acd5016998c128bb0003","jobName":".NET开发工程师","optime":"2019-04-28 16:33:22","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"1","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-28 16:27:40","enterpriseId":"1205000136","hasSend":"1","id":"402894b36a62feda016a630e9076004c","isSuitable":"0","jobId":"ff8080816998acd5016998c128bb0003","jobName":".NET开发工程师","optime":"2019-04-28 16:27:40","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"1","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-28 16:25:28","enterpriseId":"1205000136","hasSend":"1","id":"402894b36a62feda016a630c900a0046","isSuitable":"0","jobId":"ff8080816998acd5016998c128bb0003","jobName":".NET开发工程师","optime":"2019-04-28 16:25:28","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"1","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-24 17:32:08","enterpriseId":"ff8080816488a9e1016488ae7a450003","hasSend":"1","id":"ff8080816a4e33d5016a4eb026980008","isSuitable":"0","jobId":"ff8080816a2e20a6016a2f33a3dc0008","jobName":"课程顾问（电话销售）","optime":"2019-04-24 17:32:08","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"0","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-12 09:13:25","enterpriseId":"ff8080816998acd501699e0ca0890009","hasSend":"1","id":"ff8080816a0bd6b5016a0f1b40ac003f","isSuitable":"0","jobId":"ff8080816998acd501699ed9a8f0000e","jobName":"产品销售","optime":"2019-04-12 09:13:25","replyContent":"","replyDate":"","resumeId":"ff8080816a05f4cb016a09f1f55a000f","sendName":"郝彬彬","status":"0","userId":"ff80808169d8cc8f0169dba108490001"},{"applyDate":"2019-04-12 08:58:59","enterpriseId":"ff8080816488a9e1016488ae7a450003","hasSend":"1","id":"ff8080816a0bd6b5016a0f0e0be40035","isSuitable":"0","jobId":"ff8080816998acd5016998c128bb0003","jobName":".NET开发工程师","optime":"2019-04-12 08:58:59","replyContent":"","replyDate":"","resumeId":"ff8080816a0a1127016a0a2c1317000a","sendName":"郝彬彬","status":"0","userId":"ff80808169d8cc8f0169dba108490001"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 7
     * size : 10
     * sort : {}
     * totalElements : 7
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
         * applyDate : 2019-04-28 16:33:52
         * enterpriseId : ff8080816488a9e1016488ae7a450003
         * hasSend : 1
         * id : 402894b36a62feda016a63143f1e0058
         * isSuitable : 0
         * jobId : ff8080816a2e20a6016a2f33a3dc0008
         * jobName : 课程顾问（电话销售）
         * optime : 2019-04-28 16:33:52
         * replyContent :
         * replyDate :
         * resumeId : ff8080816a05f4cb016a09f1f55a000f
         * sendName : 郝彬彬
         * status : 1
         * userId : ff80808169d8cc8f0169dba108490001
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
