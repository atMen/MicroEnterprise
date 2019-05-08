package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/4/2.
 */

public class JlInfo {

    /**
     * jobWills : [{"expectMoney":"15","expectProfession":"IT相关行业","id":"ff808081695aef420169801c1ce60120","jobStatus":"在职","jobType":"全职","optime":"2019-03-15 14:48:03","resumeId":"ff808081695aef420169801ab342011e","userId":"ff808081695aef42016976055d0400f8","username":"小曲"}]
     * resume : {"birthday":"1998-11-08","educationBackground":"博士","email":"123@qq.com","evaluate":"","hobby":"","id":"ff808081695aef420169801ab342011e","marry":"未婚","mobile":"18409208732","optime":"2019-03-15 14:47:00","politicsFace":"党员","resumeName":"AI工程师","sex":"男","skill":"","userId":"ff808081695aef42016976055d0400f8","username":"小曲"}
     * projectExperiences : [{"endTime":"2019-03-15","id":"ff808081695aef420169801cbbc80121","optime":"2019-03-15 14:49:02","projectDescribe":"无人汽车制造","projectName":"百度无人汽车","resumeId":"ff808081695aef420169801ab342011e","startTime":"2017-01-15","userId":"ff808081695aef42016976055d0400f8","username":"小曲"}]
     * educationBackgrounds : [{"educationBackground":"博士","graduateTime":"2019-07-15","id":"ff808081695aef420169801b5e94011f","optime":"2019-03-15 14:47:05","professionName":"人工智能","resumeId":"ff808081695aef420169801ab342011e","schoolName":"西北工业大学","startStudyTime":"2017-09-15","userId":"ff808081695aef42016976055d0400f8","username":"小曲"}]
     */

    private ResumeBean resume;
    private List<JobWillsBean> jobWills;
    private List<ProjectExperiencesBean> projectExperiences;
    private List<EducationBackgroundsBean> educationBackgrounds;

    public ResumeBean getResume() {
        return resume;
    }

    public void setResume(ResumeBean resume) {
        this.resume = resume;
    }

    public List<JobWillsBean> getJobWills() {
        return jobWills;
    }

    public void setJobWills(List<JobWillsBean> jobWills) {
        this.jobWills = jobWills;
    }

    public List<ProjectExperiencesBean> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperiencesBean> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }

    public List<EducationBackgroundsBean> getEducationBackgrounds() {
        return educationBackgrounds;
    }

    public void setEducationBackgrounds(List<EducationBackgroundsBean> educationBackgrounds) {
        this.educationBackgrounds = educationBackgrounds;
    }

    public static class ResumeBean {
        /**
         * birthday : 1998-11-08
         * educationBackground : 博士
         * email : 123@qq.com
         * evaluate :
         * hobby :
         * id : ff808081695aef420169801ab342011e
         * marry : 未婚
         * mobile : 18409208732
         * optime : 2019-03-15 14:47:00
         * politicsFace : 党员
         * resumeName : AI工程师
         * sex : 男
         * skill :
         * userId : ff808081695aef42016976055d0400f8
         * username : 小曲
         */

        private String birthday;
        private String educationBackground;
        private String email;
        private String evaluate;
        private String hobby;
        private String id;
        private String marry;
        private String mobile;
        private String optime;
        private String politicsFace;
        private String resumeName;
        private String sex;
        private String skill;
        private String userId;
        private String username;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEducationBackground() {
            return educationBackground;
        }

        public void setEducationBackground(String educationBackground) {
            this.educationBackground = educationBackground;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMarry() {
            return marry;
        }

        public void setMarry(String marry) {
            this.marry = marry;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public String getPoliticsFace() {
            return politicsFace;
        }

        public void setPoliticsFace(String politicsFace) {
            this.politicsFace = politicsFace;
        }

        public String getResumeName() {
            return resumeName;
        }

        public void setResumeName(String resumeName) {
            this.resumeName = resumeName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSkill() {
            return skill;
        }

        public void setSkill(String skill) {
            this.skill = skill;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class JobWillsBean {
        /**
         * expectMoney : 15
         * expectProfession : IT相关行业
         * id : ff808081695aef420169801c1ce60120
         * jobStatus : 在职
         * jobType : 全职
         * optime : 2019-03-15 14:48:03
         * resumeId : ff808081695aef420169801ab342011e
         * userId : ff808081695aef42016976055d0400f8
         * username : 小曲
         */

        private String expectMoney;
        private String expectProfession;
        private String id;
        private String jobStatus;
        private String jobType;
        private String optime;
        private String resumeId;
        private String userId;
        private String username;

        public String getExpectMoney() {
            return expectMoney;
        }

        public void setExpectMoney(String expectMoney) {
            this.expectMoney = expectMoney;
        }

        public String getExpectProfession() {
            return expectProfession;
        }

        public void setExpectProfession(String expectProfession) {
            this.expectProfession = expectProfession;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(String jobStatus) {
            this.jobStatus = jobStatus;
        }

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public String getResumeId() {
            return resumeId;
        }

        public void setResumeId(String resumeId) {
            this.resumeId = resumeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ProjectExperiencesBean {
        /**
         * endTime : 2019-03-15
         * id : ff808081695aef420169801cbbc80121
         * optime : 2019-03-15 14:49:02
         * projectDescribe : 无人汽车制造
         * projectName : 百度无人汽车
         * resumeId : ff808081695aef420169801ab342011e
         * startTime : 2017-01-15
         * userId : ff808081695aef42016976055d0400f8
         * username : 小曲
         */

        private String endTime;
        private String id;
        private String optime;
        private String projectDescribe;
        private String projectName;
        private String resumeId;
        private String startTime;
        private String userId;
        private String username;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public String getProjectDescribe() {
            return projectDescribe;
        }

        public void setProjectDescribe(String projectDescribe) {
            this.projectDescribe = projectDescribe;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getResumeId() {
            return resumeId;
        }

        public void setResumeId(String resumeId) {
            this.resumeId = resumeId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class EducationBackgroundsBean {
        /**
         * educationBackground : 博士
         * graduateTime : 2019-07-15
         * id : ff808081695aef420169801b5e94011f
         * optime : 2019-03-15 14:47:05
         * professionName : 人工智能
         * resumeId : ff808081695aef420169801ab342011e
         * schoolName : 西北工业大学
         * startStudyTime : 2017-09-15
         * userId : ff808081695aef42016976055d0400f8
         * username : 小曲
         */

        private String educationBackground;
        private String graduateTime;
        private String id;
        private String optime;
        private String professionName;
        private String resumeId;
        private String schoolName;
        private String startStudyTime;
        private String userId;
        private String username;

        public String getEducationBackground() {
            return educationBackground;
        }

        public void setEducationBackground(String educationBackground) {
            this.educationBackground = educationBackground;
        }

        public String getGraduateTime() {
            return graduateTime;
        }

        public void setGraduateTime(String graduateTime) {
            this.graduateTime = graduateTime;
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

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }

        public String getResumeId() {
            return resumeId;
        }

        public void setResumeId(String resumeId) {
            this.resumeId = resumeId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getStartStudyTime() {
            return startStudyTime;
        }

        public void setStartStudyTime(String startStudyTime) {
            this.startStudyTime = startStudyTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
