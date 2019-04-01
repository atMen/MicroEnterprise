package com.tcrj.micro.entity;

import java.util.List;

/**
 * Created by leict on 2019/3/29.
 */

public class qyInfo {


    /**
     * content : [{"enterpriseId":"1205000143","enterpriseName":"陕西省三信实业有限公司","frIdentity":"610103610714043","gsjId":"5850e3a801f041ea994ef5b517516659","id":"ff80808169c848c80169c873a5ca0005","mobile":"","optime":"2019-03-29 15:56:56","tyshxydm":"1205000143","userId":"ff80808169c848c80169c873a5950004"},{"enterpriseId":"1205000127","enterpriseName":"陕西志华实业有限公司","frIdentity":"610325490910551","gsjId":"5850e3a801f041ea994ef5b517516659","id":"ff8080816998acd50169a45c34760019","mobile":"","optime":"2019-03-22 15:44:59","tyshxydm":"1205000127","userId":"ff8080816998acd50169a45c344f0018"},{"enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","frIdentity":"612129520321005","gsjId":"5850e3a801f041ea994ef5b517516659","id":"ff8080816998acd50169a456cff00016","mobile":"","optime":"2019-03-22 15:39:06","tyshxydm":"1205000131","userId":"ff8080816998acd50169a456cf960015"},{"enterpriseId":"1205000117","enterpriseName":"陕西涤玉阁书画社","frIdentity":"610104610410511","gsjId":"5850e3a801f041ea994ef5b517516659","id":"ff8080816998acd501699e0ca0890009","mobile":"","optime":"2019-03-21 10:20:21","tyshxydm":"1205000117","userId":"ff8080816998acd501699e0c9fa20008"},{"enterpriseId":"22054115","enterpriseName":"西安永明装饰材料实业公司","frIdentity":"610121540116285","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","id":"ff808081648e1f420164b5230b180098","mobile":"","optime":"2018-07-20 08:42:09","tyshxydm":"22054115","userId":"ff808081648e1f420164b5230a990097"},{"enterpriseId":"1205000153","enterpriseName":"陕西中华创意信息有限公司","frIdentity":"430302130121251","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","id":"ff808081648e1f420164b0106ca7008d","mobile":"","optime":"2018-07-19 09:03:43","tyshxydm":"1205000153","userId":"ff808081648e1f420164b0106c5c008c"},{"enterpriseId":"1205000130","enterpriseName":"陕西克莱玛实业有限公司","frIdentity":"610103530807003","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","id":"ff8080816488b31c01648c0f7cf20011","mobile":"","optime":"2018-07-12 09:16:22","tyshxydm":"1205000130","userId":"ff8080816488b31c01648c0f7c960010"},{"enterpriseId":"1205000136","enterpriseName":"陕西蓝马广告实业有限公司","frIdentity":"610112391231102","gsjId":"a90e2a364b364c87b41ff8b33e9752f1","id":"ff8080816488a9e1016488ae7a450003","mobile":"","optime":"2018-07-11 17:31:33","tyshxydm":"1205000136","userId":"ff8080816488a9e1016488ae7a1a0002"},{"enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","frIdentity":"612129520321005","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","id":"ff80808164889df00164889f8e080001","mobile":"","optime":"2018-07-11 17:15:15","tyshxydm":"1205000131","userId":"ff80808164889df00164889f8da40000"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 9
     * size : 20
     * sort : {}
     * totalElements : 9
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
         * enterpriseId : 1205000143
         * enterpriseName : 陕西省三信实业有限公司
         * frIdentity : 610103610714043
         * gsjId : 5850e3a801f041ea994ef5b517516659
         * id : ff80808169c848c80169c873a5ca0005
         * mobile :
         * optime : 2019-03-29 15:56:56
         * tyshxydm : 1205000143
         * userId : ff80808169c848c80169c873a5950004
         */
        public boolean isselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }

        private boolean isselect;
        private String enterpriseId;
        private String enterpriseName;
        private String frIdentity;
        private String gsjId;
        private String id;
        private String mobile;
        private String optime;
        private String tyshxydm;
        private String userId;

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

        public String getFrIdentity() {
            return frIdentity;
        }

        public void setFrIdentity(String frIdentity) {
            this.frIdentity = frIdentity;
        }

        public String getGsjId() {
            return gsjId;
        }

        public void setGsjId(String gsjId) {
            this.gsjId = gsjId;
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

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public String getTyshxydm() {
            return tyshxydm;
        }

        public void setTyshxydm(String tyshxydm) {
            this.tyshxydm = tyshxydm;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
