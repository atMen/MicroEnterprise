package com.tcrj.micro.entity;

import java.io.Serializable;

/**
 * Created by leict on 2019/3/29.
 */

public class LoginInfo implements Serializable{

    /**
     * token : 638680dc732d0d390c1fee4694186bcf
     * user : {"address":"","cname":"陕西蓝马广告实业有限公司","code":"","deleted":"0","departmentId":"3","departmentName":"","email":"","enable":"1","ename":"QY_17802902206","id":"ff8080816488a9e1016488ae7a1a0002","mobile":"17802902206","note":"","optime":"2018-07-11 17:31:33","password":"e10adc3949ba59abbe56e057f20f883e","registrationID":"26a0cf005b495b62039c87d7cdcbd92d6332fe3678a23e5b30f157a8a35786b7","registrationType":"ios","sex":"","sort":0,"type":"2"}
     */

    private String token;
    private UserBean user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable{
        /**
         * address :
         * cname : 陕西蓝马广告实业有限公司
         * code :
         * deleted : 0
         * departmentId : 3
         * departmentName :
         * email :
         * enable : 1
         * ename : QY_17802902206
         * id : ff8080816488a9e1016488ae7a1a0002
         * mobile : 17802902206
         * note :
         * optime : 2018-07-11 17:31:33
         * password : e10adc3949ba59abbe56e057f20f883e
         * registrationID : 26a0cf005b495b62039c87d7cdcbd92d6332fe3678a23e5b30f157a8a35786b7
         * registrationType : ios
         * sex :
         * sort : 0
         * type : 2
         */

        private String address;
        private String cname;
        private String code;
        private String deleted;
        private String departmentId;
        private String departmentName;
        private String email;
        private String enable;
        private String ename;
        private String id;
        private String mobile;
        private String note;
        private String optime;
        private String password;
        private String registrationID;
        private String registrationType;
        private String sex;
        private int sort;
        private String type;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

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

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRegistrationID() {
            return registrationID;
        }

        public void setRegistrationID(String registrationID) {
            this.registrationID = registrationID;
        }

        public String getRegistrationType() {
            return registrationType;
        }

        public void setRegistrationType(String registrationType) {
            this.registrationType = registrationType;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
