package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/4/28.
 */

public class bankInfo {

    /**
     * address : 西安市南二环正信大厦十三层A座
     * code : 987654321012345678
     * departmentId :
     * description :
     * id : 402894306a4eae62016a4eb10b530003
     * leagalIdCard : 615856455825654789
     * legalName : 王五
     * licenseName : 中国银行营业执照.jpg
     * licenseUrl : /uploadfile//2019-04-24/20190424053303394.jpg
     * linkmanName : 李三
     * linkmanPhone : 18149297792
     * logo :
     * logoName :
     * name : 中国银行
     * note :
     * optime : {"date":26,"day":5,"hours":12,"minutes":1,"month":3,"nanos":0,"seconds":14,"time":1556251274000,"timezoneOffset":-480,"year":119}
     * phone : 18149297793
     * type : 11502
     * userId : 1
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

    public static class OptimeBean {
        /**
         * date : 26
         * day : 5
         * hours : 12
         * minutes : 1
         * month : 3
         * nanos : 0
         * seconds : 14
         * time : 1556251274000
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
