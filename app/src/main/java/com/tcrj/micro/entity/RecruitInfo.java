package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/5/6.
 */

public class RecruitInfo {

    /**
     * isSuitable : 是否合适
     * industryName : 行业名称
     * resumeName : 简历名称
     * replyDate : 回复日期
     * salary : 薪资
     * replyContent : 回复内容
     * companyName : 企业名称
     * jobName : 职位名称
     * applyDate : 申请日期
     */

    private String isSuitable;
    private String industryName;
    private String resumeName;
    private String replyDate;
    private String salary;
    private String replyContent;
    private String companyName;
    private String jobName;
    private String applyDate;

    public String getIsSuitable() {
        return isSuitable;
    }

    public void setIsSuitable(String isSuitable) {
        this.isSuitable = isSuitable;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
}
