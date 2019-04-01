package com.spring.chanba.bean;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfoEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"addTime":"2018-09-20 17:18:06","headImage":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJic4DjodvuNZ8Qag4NOMzKLnKpmSMqQYY2XnGSOKB69guibcm3JSKibJic6XwoicribJznYJIoc2gxBglQ/132","id":"02b346dda0a7409880f85b94366f430e","loginPwd":"","memberName":"","memberPhone":"","nickName":"宇少","optime":{"date":20,"day":4,"hours":17,"minutes":18,"month":8,"seconds":6,"time":1537435086804,"timezoneOffset":-480,"year":118},"payPwd":"","qrCreateTime":"","qrFileCode":"","qrFilePath":"","salt":"","state":"1","wechatId":"odY3I1SPcHgEmKg-VavjYXP1_plg"}
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
         * addTime : 2018-09-20 17:18:06
         * headImage : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJic4DjodvuNZ8Qag4NOMzKLnKpmSMqQYY2XnGSOKB69guibcm3JSKibJic6XwoicribJznYJIoc2gxBglQ/132
         * id : 02b346dda0a7409880f85b94366f430e
         * loginPwd :
         * memberName :
         * memberPhone :
         * nickName : 宇少
         * optime : {"date":20,"day":4,"hours":17,"minutes":18,"month":8,"seconds":6,"time":1537435086804,"timezoneOffset":-480,"year":118}
         * payPwd :
         * qrCreateTime :
         * qrFileCode :
         * qrFilePath :
         * salt :
         * state : 1
         * wechatId : odY3I1SPcHgEmKg-VavjYXP1_plg
         */

        private String addTime;
        private String headImage;
        private String id;
        private String loginPwd;
        private String memberName;
        private String memberPhone;
        private String nickName;
        private OptimeBean optime;
        private String payPwd;
        private String qrCreateTime;
        private String qrFileCode;
        private String qrFilePath;
        private String salt;
        private String state;
        private String wechatId;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginPwd() {
            return loginPwd;
        }

        public void setLoginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberPhone() {
            return memberPhone;
        }

        public void setMemberPhone(String memberPhone) {
            this.memberPhone = memberPhone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public OptimeBean getOptime() {
            return optime;
        }

        public void setOptime(OptimeBean optime) {
            this.optime = optime;
        }

        public String getPayPwd() {
            return payPwd;
        }

        public void setPayPwd(String payPwd) {
            this.payPwd = payPwd;
        }

        public String getQrCreateTime() {
            return qrCreateTime;
        }

        public void setQrCreateTime(String qrCreateTime) {
            this.qrCreateTime = qrCreateTime;
        }

        public String getQrFileCode() {
            return qrFileCode;
        }

        public void setQrFileCode(String qrFileCode) {
            this.qrFileCode = qrFileCode;
        }

        public String getQrFilePath() {
            return qrFilePath;
        }

        public void setQrFilePath(String qrFilePath) {
            this.qrFilePath = qrFilePath;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getWechatId() {
            return wechatId;
        }

        public void setWechatId(String wechatId) {
            this.wechatId = wechatId;
        }

        public static class OptimeBean {
            /**
             * date : 20
             * day : 4
             * hours : 17
             * minutes : 18
             * month : 8
             * seconds : 6
             * time : 1537435086804
             * timezoneOffset : -480
             * year : 118
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
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
