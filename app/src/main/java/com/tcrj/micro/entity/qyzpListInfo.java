package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/27.
 */

public class qyzpListInfo {
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

        public static class SortBean {}

        public static class ContentBean {
            /**
             * ageRequire : 15-30
             * auditStatus : 1
             * createTime : 2019-03-21 14:04:18
             * educationBackgroundRequire : 本科
             * enterpriseId : ff8080816998acd501699e0ca0890009
             * enterpriseName : 陕西涤玉阁书画社
             * enterpriseRecruitId : ff8080816998acd501699e2db547000c
             * hasLike :
             * hasSend :
             * id : ff8080816998acd501699ed9a8f0000e
             * industry : 10401
             * jobCity : 3abb1c09ea4d4da29e0e431ad2d6f76c
             * jobCityName : 西安市
             * jobCounty : 57134b7a55fb4d0db2b7283abc9733f1
             * jobCountyName : 未央区
             * jobName : 产品销售
             * jobTimeRequire : 3
             * lastHandleTime : 2019-04-02 09:26:37
             * optime : 2019-03-21 16:59:00
             * publishStatus : 1
             * publishTime : 15
             * recruitNum : 3
             * remainingTime : 15
             * requireDeatil : <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: " microsoft="" font-size:="" white-space:="" background-color:="">1、市场营销或计算机相关专业，大专及以上学历；</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: " microsoft="" font-size:="" white-space:="" background-color:="">2、推广经理要求本行业销售或市场部工作经验2年以上，有管理团队经验；</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: " microsoft="" font-size:="" white-space:="" background-color:="">3、具有平台推广经验者优先；</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: " microsoft="" font-size:="" white-space:="" background-color:="">4、年龄22-35岁；</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: " microsoft="" font-size:="" white-space:="" background-color:="">5、性格开朗，有上进心，口齿伶俐。</p><p><br/></p>
             * salarRange : 面议
             * sexRequire : 不限
             * targetIndustry : 销售代表
             * targetIndustryId : 107010101
             */

            private String ageRequire;
            private String auditStatus;
            private String createTime;
            private String educationBackgroundRequire;
            private String enterpriseId;
            private String enterpriseName;
            private String enterpriseRecruitId;
            private String hasLike;
            private String hasSend;
            private String id;
            private String industry;
            private String jobCity;
            private String jobCityName;
            private String jobCounty;
            private String jobCountyName;
            private String jobName;
            private String jobTimeRequire;
            private String lastHandleTime;
            private String optime;
            private String publishStatus;
            private String publishTime;
            private String recruitNum;
            private String remainingTime;
            private String requireDeatil;
            private String salarRange;
            private String sexRequire;
            private String targetIndustry;
            private String targetIndustryId;

            public String getAgeRequire() {
                return ageRequire;
            }

            public void setAgeRequire(String ageRequire) {
                this.ageRequire = ageRequire;
            }

            public String getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(String auditStatus) {
                this.auditStatus = auditStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEducationBackgroundRequire() {
                return educationBackgroundRequire;
            }

            public void setEducationBackgroundRequire(String educationBackgroundRequire) {
                this.educationBackgroundRequire = educationBackgroundRequire;
            }

            public String getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(String enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public String getEnterpriseName() {
                return enterpriseName;
            }

            public void setEnterpriseName(String enterpriseName) {
                this.enterpriseName = enterpriseName;
            }

            public String getEnterpriseRecruitId() {
                return enterpriseRecruitId;
            }

            public void setEnterpriseRecruitId(String enterpriseRecruitId) {
                this.enterpriseRecruitId = enterpriseRecruitId;
            }

            public String getHasLike() {
                return hasLike;
            }

            public void setHasLike(String hasLike) {
                this.hasLike = hasLike;
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

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getJobCity() {
                return jobCity;
            }

            public void setJobCity(String jobCity) {
                this.jobCity = jobCity;
            }

            public String getJobCityName() {
                return jobCityName;
            }

            public void setJobCityName(String jobCityName) {
                this.jobCityName = jobCityName;
            }

            public String getJobCounty() {
                return jobCounty;
            }

            public void setJobCounty(String jobCounty) {
                this.jobCounty = jobCounty;
            }

            public String getJobCountyName() {
                return jobCountyName;
            }

            public void setJobCountyName(String jobCountyName) {
                this.jobCountyName = jobCountyName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getJobTimeRequire() {
                return jobTimeRequire;
            }

            public void setJobTimeRequire(String jobTimeRequire) {
                this.jobTimeRequire = jobTimeRequire;
            }

            public String getLastHandleTime() {
                return lastHandleTime;
            }

            public void setLastHandleTime(String lastHandleTime) {
                this.lastHandleTime = lastHandleTime;
            }

            public String getOptime() {
                return optime;
            }

            public void setOptime(String optime) {
                this.optime = optime;
            }

            public String getPublishStatus() {
                return publishStatus;
            }

            public void setPublishStatus(String publishStatus) {
                this.publishStatus = publishStatus;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getRecruitNum() {
                return recruitNum;
            }

            public void setRecruitNum(String recruitNum) {
                this.recruitNum = recruitNum;
            }

            public String getRemainingTime() {
                return remainingTime;
            }

            public void setRemainingTime(String remainingTime) {
                this.remainingTime = remainingTime;
            }

            public String getRequireDeatil() {
                return requireDeatil;
            }

            public void setRequireDeatil(String requireDeatil) {
                this.requireDeatil = requireDeatil;
            }

            public String getSalarRange() {
                return salarRange;
            }

            public void setSalarRange(String salarRange) {
                this.salarRange = salarRange;
            }

            public String getSexRequire() {
                return sexRequire;
            }

            public void setSexRequire(String sexRequire) {
                this.sexRequire = sexRequire;
            }

            public String getTargetIndustry() {
                return targetIndustry;
            }

            public void setTargetIndustry(String targetIndustry) {
                this.targetIndustry = targetIndustry;
            }

            public String getTargetIndustryId() {
                return targetIndustryId;
            }

            public void setTargetIndustryId(String targetIndustryId) {
                this.targetIndustryId = targetIndustryId;
            }
        }

    }

}
