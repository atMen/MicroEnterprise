package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2018/11/27.
 */

public class BumenInfo {

    /**
     * state : 1
     * msg : 操作成功
     * comlist : [{"deptlist":[{"id":3,"name":"产品研发中心"},{"id":5,"name":"综合管理中心"},{"id":6,"name":"财务结算中心"},{"id":7,"name":"顾问组"},{"id":9,"name":"市场营销中心"},{"id":48,"name":"党工委"},{"id":50,"name":"审计"},{"id":51,"name":"智慧城市研究院"}],"id":1,"name":"陕西天诚软件有限公司"},{"deptlist":[{"id":968,"name":"风控部"},{"id":969,"name":"法务部"},{"id":970,"name":"财务部"}],"id":60,"name":"弥尔（西安）商业保理有限公司"},{"deptlist":[{"id":971,"name":"财务部"}],"id":62,"name":"嘉峪关天诚信息技术有限公司"},{"deptlist":[],"id":63,"name":"陕西天诚软件有限公司兰州分公司"},{"deptlist":[{"id":972,"name":"财务部"}],"id":64,"name":"深圳市天诚软件有限公司"},{"deptlist":[{"id":973,"name":"财务部"}],"id":65,"name":"宁夏天诚基业软件科技有限公司"},{"deptlist":[{"id":974,"name":"财务部"}],"id":66,"name":"弥尔（北京）科技有限公司"},{"deptlist":[{"id":69,"name":"综合部"},{"id":70,"name":"财务部"},{"id":71,"name":"工程部"},{"id":72,"name":"运维部"},{"id":975,"name":"采购部"},{"id":976,"name":"市场部"},{"id":977,"name":"公共关系部"},{"id":978,"name":"宣传部"}],"id":68,"name":"延安弥尔智慧城市投资发展有限公司"},{"deptlist":[],"id":73,"name":"陕西天诚软件有限公司榆林分公司"},{"deptlist":[],"id":979,"name":"战略发展部"}]
     */

    private int state;
    private String msg;
    private List<ComlistBean> comlist;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ComlistBean> getComlist() {
        return comlist;
    }

    public void setComlist(List<ComlistBean> comlist) {
        this.comlist = comlist;
    }

    public static class ComlistBean {
        /**
         * deptlist : [{"id":3,"name":"产品研发中心"},{"id":5,"name":"综合管理中心"},{"id":6,"name":"财务结算中心"},{"id":7,"name":"顾问组"},{"id":9,"name":"市场营销中心"},{"id":48,"name":"党工委"},{"id":50,"name":"审计"},{"id":51,"name":"智慧城市研究院"}]
         * id : 1
         * name : 陕西天诚软件有限公司
         */

        private int id;
        private String name;
        private List<DeptlistBean> deptlist;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DeptlistBean> getDeptlist() {
            return deptlist;
        }

        public void setDeptlist(List<DeptlistBean> deptlist) {
            this.deptlist = deptlist;
        }

        public static class DeptlistBean {
            /**
             * id : 3
             * name : 产品研发中心
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
