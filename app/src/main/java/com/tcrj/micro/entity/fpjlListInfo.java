package com.tcrj.micro.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2019/3/28.
 */

public class fpjlListInfo implements Serializable{


    /**
     * errorCode : SUCCESS
     * data : [{"id":"ff80808169c3608f0169c3be75920001","optime":"2019-03-28T10:00:32.912+0000","cityId":"3abb1c09ea4d4da29e0e431ad2d6f76c","countyId":"57134b7a55fb4d0db2b7283abc9733f1","townName":"好家村","airPoorObjectId":"ff808081695ac11401695ac602870003","homeName":"2","poorReason":"缺乏劳动力","enterpriseId":"1205000136","enterpriseName":"陕西蓝马广告实业有限公司","aidPoorMoney":1,"aidPoorIntelligence":2,"aidPoorJob":3,"aidPoorIndustry":4,"aidPoorBussiness":5,"aidPoorOtherType":6,"cityName":"西安市","countyName":"未央区"}]
     */

    private String errorCode;
    private List<DataBean> data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : ff80808169c3608f0169c3be75920001
         * optime : 2019-03-28T10:00:32.912+0000
         * cityId : 3abb1c09ea4d4da29e0e431ad2d6f76c
         * countyId : 57134b7a55fb4d0db2b7283abc9733f1
         * townName : 好家村
         * airPoorObjectId : ff808081695ac11401695ac602870003
         * homeName : 2
         * poorReason : 缺乏劳动力
         * enterpriseId : 1205000136
         * enterpriseName : 陕西蓝马广告实业有限公司
         * aidPoorMoney : 1
         * aidPoorIntelligence : 2
         * aidPoorJob : 3
         * aidPoorIndustry : 4
         * aidPoorBussiness : 5
         * aidPoorOtherType : 6
         * cityName : 西安市
         * countyName : 未央区
         */

        private String id;
        private String optime;
        private String cityId;
        private String countyId;
        private String townName;
        private String airPoorObjectId;
        private String homeName;
        private String poorReason;
        private String enterpriseId;
        private String enterpriseName;
        private int aidPoorMoney;
        private int aidPoorIntelligence;
        private int aidPoorJob;
        private int aidPoorIndustry;
        private int aidPoorBussiness;
        private int aidPoorOtherType;
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

        public String getAirPoorObjectId() {
            return airPoorObjectId;
        }

        public void setAirPoorObjectId(String airPoorObjectId) {
            this.airPoorObjectId = airPoorObjectId;
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

        public int getAidPoorMoney() {
            return aidPoorMoney;
        }

        public void setAidPoorMoney(int aidPoorMoney) {
            this.aidPoorMoney = aidPoorMoney;
        }

        public int getAidPoorIntelligence() {
            return aidPoorIntelligence;
        }

        public void setAidPoorIntelligence(int aidPoorIntelligence) {
            this.aidPoorIntelligence = aidPoorIntelligence;
        }

        public int getAidPoorJob() {
            return aidPoorJob;
        }

        public void setAidPoorJob(int aidPoorJob) {
            this.aidPoorJob = aidPoorJob;
        }

        public int getAidPoorIndustry() {
            return aidPoorIndustry;
        }

        public void setAidPoorIndustry(int aidPoorIndustry) {
            this.aidPoorIndustry = aidPoorIndustry;
        }

        public int getAidPoorBussiness() {
            return aidPoorBussiness;
        }

        public void setAidPoorBussiness(int aidPoorBussiness) {
            this.aidPoorBussiness = aidPoorBussiness;
        }

        public int getAidPoorOtherType() {
            return aidPoorOtherType;
        }

        public void setAidPoorOtherType(int aidPoorOtherType) {
            this.aidPoorOtherType = aidPoorOtherType;
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
}
