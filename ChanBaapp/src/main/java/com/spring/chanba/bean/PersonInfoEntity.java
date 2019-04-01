package com.spring.chanba.bean;

import java.io.Serializable;

/**
 * 个人信息
 */
public class PersonInfoEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"IDNumber":"610402199009058748","addTime":"2018-10-11 17:47:20","address":"","age":"","education":"","id":"2c9f4a876629458201666285fe570010","isFund":"1","memberId":"8434271e2a1642c4a79069f1a48aecf0","name":"韩智伟","optime":{"date":11,"day":4,"hours":17,"minutes":47,"month":9,"nanos":0,"seconds":20,"time":1539251240000,"timezoneOffset":-480,"year":118},"phone":"155555555555","profession":"","sex":"0","university":"","workingLife":"","workplace":""}
     * state : 1
     */

    private String message;
    private DataBean data;
    private int state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * IDNumber : 610402199009058748
         * addTime : 2018-10-11 17:47:20
         * address :
         * age :
         * education :
         * id : 2c9f4a876629458201666285fe570010
         * isFund : 1
         * memberId : 8434271e2a1642c4a79069f1a48aecf0
         * name : 韩智伟
         * optime : {"date":11,"day":4,"hours":17,"minutes":47,"month":9,"nanos":0,"seconds":20,"time":1539251240000,"timezoneOffset":-480,"year":118}
         * phone : 155555555555
         * profession :
         * sex : 0
         * university :
         * workingLife :
         * workplace :
         */

        private String IDNumber;
        private String addTime;
        private String address;
        private String age;
        private String education;
        private String id;
        private String isFund;
        private String memberId;
        private String name;
        private OptimeBean optime;
        private String phone;
        private String profession;
        private String sex;
        private String university;
        private String workingLife;
        private String workplace;

        public String getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsFund() {
            return isFund;
        }

        public void setIsFund(String isFund) {
            this.isFund = isFund;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getWorkingLife() {
            return workingLife;
        }

        public void setWorkingLife(String workingLife) {
            this.workingLife = workingLife;
        }

        public String getWorkplace() {
            return workplace;
        }

        public void setWorkplace(String workplace) {
            this.workplace = workplace;
        }

        public static class OptimeBean {
            /**
             * date : 11
             * day : 4
             * hours : 17
             * minutes : 47
             * month : 9
             * nanos : 0
             * seconds : 20
             * time : 1539251240000
             * timezoneOffset : -480
             * year : 118
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
}
