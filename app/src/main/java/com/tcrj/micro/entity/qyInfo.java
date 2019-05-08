package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/29.
 */




public class qyInfo {


    /**
     * content : [{"address":"","cname":"工商局扶贫办","code":"","deleted":"0","departmentId":"","departmentName":"","email":"","enable":"1","ename":"GSJ_17802902206","id":"32b4c693f1e849538294bc2479823ade","isFirstLogin":"1","mobile":"17802902206","note":"","optime":"2019-05-07 09:19:10","password":"","registrationID":"","registrationType":"","sex":"","sort":0,"type":"4"},{"address":"","cname":"西安市扶贫中心","code":"","deleted":"0","departmentId":"","departmentName":"","email":"","enable":"1","ename":"GSJ_17802902208","id":"6bea52e15bea4478b9cd3f0cd9c01ffe","isFirstLogin":"1","mobile":"17802902208","note":"","optime":"2019-05-07 09:19:10","password":"","registrationID":"","registrationType":"","sex":"","sort":0,"type":"4"},{"address":"","cname":"王锋","code":"","deleted":"0","departmentId":"","departmentName":"","email":"","enable":"1","ename":"GSJ_18092194292","id":"ac0b22b042314230a0d0fbc37dbd94da","isFirstLogin":"1","mobile":"18092194292","note":"","optime":"2019-05-07 09:19:10","password":"","registrationID":"","registrationType":"","sex":"","sort":0,"type":"4"},{"address":"","cname":"渭南市扶贫办","code":"","deleted":"0","departmentId":"","departmentName":"","email":"","enable":"1","ename":"GSJ_17802902207","id":"e2e095fd528940cb990ba3f10e2719f5","isFirstLogin":"1","mobile":"17802902207","note":"","optime":"2019-05-07 09:19:10","password":"","registrationID":"","registrationType":"","sex":"","sort":0,"type":"4"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 4
     * size : 10
     * sort : {}
     * totalElements : 4
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
         * address :
         * cname : 工商局扶贫办
         * code :
         * deleted : 0
         * departmentId :
         * departmentName :
         * email :
         * enable : 1
         * ename : GSJ_17802902206
         * id : 32b4c693f1e849538294bc2479823ade
         * isFirstLogin : 1
         * mobile : 17802902206
         * note :
         * optime : 2019-05-07 09:19:10
         * password :
         * registrationID :
         * registrationType :
         * sex :
         * sort : 0
         * type : 4
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
        private String isFirstLogin;
        private String mobile;
        private String note;
        private String optime;
        private String password;
        private String registrationID;
        private String registrationType;
        private String sex;
        private int sort;
        private String type;

        private boolean isselect;


        public boolean isselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }



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

        public String getIsFirstLogin() {
            return isFirstLogin;
        }

        public void setIsFirstLogin(String isFirstLogin) {
            this.isFirstLogin = isFirstLogin;
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
