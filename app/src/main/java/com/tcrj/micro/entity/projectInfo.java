package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2018/10/19.
 */

public class projectInfo {

    /**
     * msg : 操作成功
     * stat : 1
     * result : [{"ID":1,"PName":"路面处理一期，就这么biubiu笨爽"},{"ID":2,"PName":"大本营"},{"ID":3,"PName":"红尘4"},{"ID":4,"PName":"1111"},{"ID":5,"PName":"333"},{"ID":6,"PName":"5"},{"ID":7,"PName":"2222222222"},{"ID":8,"PName":"3"},{"ID":11,"PName":"多么难得膏锋锷"},{"ID":12,"PName":"昆仑剑法"},{"ID":13,"PName":"这是个大项目"},{"ID":14,"PName":"16测试"},{"ID":24,"PName":"10"},{"ID":27,"PName":"11"},{"ID":28,"PName":"12"},{"ID":29,"PName":"信号灯二期"}]
     */

    private String msg;
    private int stat;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ID : 1
         * PName : 路面处理一期，就这么biubiu笨爽
         */

        private int ID;
        private String PName;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        private String Name;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getPName() {
            return PName;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }
    }
}
