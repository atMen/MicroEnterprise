package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2018/11/22.
 */

public class jcInfo {

    /**
     * msg : 操作成功
     * stat : 1
     * result : {"totalItemCount":162,"totalPageCount":17,"items":[{"ID":2053,"OBJECTID":6,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":130,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2054,"OBJECTID":7,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":131,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东南偏东","BZ":""},{"ID":2055,"OBJECTID":8,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":132,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2056,"OBJECTID":9,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":133,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西南偏南","BZ":""},{"ID":2057,"OBJECTID":10,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":134,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2058,"OBJECTID":11,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":135,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2059,"OBJECTID":12,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":136,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西南偏南","BZ":""},{"ID":2060,"OBJECTID":13,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":137,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2061,"OBJECTID":14,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":138,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2062,"OBJECTID":15,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":139,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东南偏东","BZ":""}]}
     */

    private String msg;
    private int stat;
    private ResultBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * totalItemCount : 162
         * totalPageCount : 17
         * items : [{"ID":2053,"OBJECTID":6,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":130,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2054,"OBJECTID":7,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":131,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东南偏东","BZ":""},{"ID":2055,"OBJECTID":8,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":132,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2056,"OBJECTID":9,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":133,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西南偏南","BZ":""},{"ID":2057,"OBJECTID":10,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":134,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2058,"OBJECTID":11,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":135,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2059,"OBJECTID":12,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":136,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西南偏南","BZ":""},{"ID":2060,"OBJECTID":13,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":137,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"西北偏西","BZ":""},{"ID":2061,"OBJECTID":14,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":138,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东北偏北","BZ":""},{"ID":2062,"OBJECTID":15,"SSXMID":33,"SSXMName":"延安新区智慧交通项目","SSLKID":139,"SSLKName":null,"JCFL":"安防公交站监控杆基础","JCWZ":"东南偏东","BZ":""}]
         */

        private int totalItemCount;
        private int totalPageCount;
        private List<ItemsBean> items;

        public int getTotalItemCount() {
            return totalItemCount;
        }

        public void setTotalItemCount(int totalItemCount) {
            this.totalItemCount = totalItemCount;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * ID : 2053
             * OBJECTID : 6
             * SSXMID : 33
             * SSXMName : 延安新区智慧交通项目
             * SSLKID : 130
             * SSLKName : null
             * JCFL : 安防公交站监控杆基础
             * JCWZ : 东北偏北
             * BZ :
             */

            private int ID;
            private int OBJECTID;
            private int SSXMID;
            private String SSXMName;
            private int SSLKID;
            private Object SSLKName;
            private String JCFL;
            private String JCWZ;
            private String BZ;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getOBJECTID() {
                return OBJECTID;
            }

            public void setOBJECTID(int OBJECTID) {
                this.OBJECTID = OBJECTID;
            }

            public int getSSXMID() {
                return SSXMID;
            }

            public void setSSXMID(int SSXMID) {
                this.SSXMID = SSXMID;
            }

            public String getSSXMName() {
                return SSXMName;
            }

            public void setSSXMName(String SSXMName) {
                this.SSXMName = SSXMName;
            }

            public int getSSLKID() {
                return SSLKID;
            }

            public void setSSLKID(int SSLKID) {
                this.SSLKID = SSLKID;
            }

            public Object getSSLKName() {
                return SSLKName;
            }

            public void setSSLKName(Object SSLKName) {
                this.SSLKName = SSLKName;
            }

            public String getJCFL() {
                return JCFL;
            }

            public void setJCFL(String JCFL) {
                this.JCFL = JCFL;
            }

            public String getJCWZ() {
                return JCWZ;
            }

            public void setJCWZ(String JCWZ) {
                this.JCWZ = JCWZ;
            }

            public String getBZ() {
                return BZ;
            }

            public void setBZ(String BZ) {
                this.BZ = BZ;
            }
        }
    }
}
