package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/7/24.
 */

public class backqyinfo {

    /**
     * address : 西安市新城区民乐园万达广场2期1幢1单元1层10105-10109号
     * code : 123456789098765432
     * departmentId : 57134b7a55fb4d0db2b7283abc9733f1
     * description :
     * id : ff8080816b79b86e016bfd9cfc6c00b6
     * leagalIdCard : 610102198008300621
     * legalName : 杨茜
     * licenseName : mrtx.jpg
     * licenseUrl : /uploadfile//2019-07-17/20190717094723814.jpg
     * linkmanName : 李洋
     * linkmanPhone : 15929485755
     * logo :
     * logoName :
     * name : 西安国信小额贷款有限公司
     * note :
     * optime : {"date":18,"day":4,"hours":15,"minutes":23,"month":6,"nanos":0,"seconds":56,"time":1563434636000,"timezoneOffset":-480,"year":119}
     * phone : 029-88767680
     * type : 11501
     * userId : 402894b36a4d32a2016a4e245dcd0008
     * userList : []
     */

    private String address;
    private String code;
    private String departmentId;
    private String description;
    private String id;
    private String leagalIdCard;
    private String legalName;
    private String licenseName;
    private String licenseUrl;
    private String linkmanName;
    private String linkmanPhone;
    private String logo;
    private String logoName;
    private String name;
    private String note;
    private OptimeBean optime;
    private String phone;
    private String type;
    private String userId;
    private List<?> userList;

    public String getQyjj() {
        return qyjj;
    }

    public void setQyjj(String qyjj) {
        this.qyjj = qyjj;
    }

    private String qyjj;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeagalIdCard() {
        return leagalIdCard;
    }

    public void setLeagalIdCard(String leagalIdCard) {
        this.leagalIdCard = leagalIdCard;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OptimeBean getOptime() {
        return optime;
    }

    public void setOptime(OptimeBean optime) {
        this.optime = optime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }

    public static class OptimeBean {
        /**
         * date : 18
         * day : 4
         * hours : 15
         * minutes : 23
         * month : 6
         * nanos : 0
         * seconds : 56
         * time : 1563434636000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
