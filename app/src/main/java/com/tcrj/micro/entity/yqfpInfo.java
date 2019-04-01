package com.tcrj.micro.entity;

import java.util.List;

/**
 *  public boolean isselect() {
 return isselect;
 }

 public void setIsselect(boolean isselect) {
 this.isselect = isselect;
 }

 private boolean isselect;
 * Created by leict on 2019/3/14.
 */

public class yqfpInfo {


    /**
     * errorcode : 9999
     * message : 操作成功。
     * data : {"content":[{"id":"ff8080816998acd50169a45c34760019","optime":"2019-03-22T07:44:59.970+0000","enterpriseId":"1205000127","enterpriseName":"陕西志华实业有限公司","userId":"ff8080816998acd50169a45c344f0018","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000127","frIdentity":"610325490910551"},{"id":"ff8080816998acd50169a456cff00016","optime":"2019-03-22T07:39:06.495+0000","enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","userId":"ff8080816998acd50169a456cf960015","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000131","frIdentity":"612129520321005"},{"id":"ff8080816998acd501699e0ca0890009","optime":"2019-03-21T02:20:21.243+0000","enterpriseId":"1205000117","enterpriseName":"陕西涤玉阁书画社","userId":"ff8080816998acd501699e0c9fa20008","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000117","frIdentity":"610104610410511"},{"id":"ff808081648e1f420164b5230b180098","optime":"2018-07-20T00:42:09.882+0000","enterpriseId":"22054115","enterpriseName":"西安永明装饰材料实业公司","userId":"ff808081648e1f420164b5230a990097","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","tyshxydm":"22054115","frIdentity":"610121540116285"},{"id":"ff808081648e1f420164b0106ca7008d","optime":"2018-07-19T01:03:43.702+0000","enterpriseId":"1205000153","enterpriseName":"陕西中华创意信息有限公司","userId":"ff808081648e1f420164b0106c5c008c","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","tyshxydm":"1205000153","frIdentity":"430302130121251"},{"id":"ff8080816488b31c01648c0f7cf20011","optime":"2018-07-12T01:16:22.541+0000","enterpriseId":"1205000130","enterpriseName":"陕西克莱玛实业有限公司","userId":"ff8080816488b31c01648c0f7c960010","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","tyshxydm":"1205000130","frIdentity":"610103530807003"},{"id":"ff8080816488a9e1016488ae7a450003","optime":"2018-07-11T09:31:33.268+0000","enterpriseId":"1205000136","enterpriseName":"陕西蓝马广告实业有限公司","userId":"ff8080816488a9e1016488ae7a1a0002","gsjId":"a90e2a364b364c87b41ff8b33e9752f1","tyshxydm":"1205000136","frIdentity":"610112391231102"},{"id":"ff80808164889df00164889f8e080001","optime":"2018-07-11T09:15:15.190+0000","enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","userId":"ff80808164889df00164889f8da40000","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","tyshxydm":"1205000131","frIdentity":"612129520321005"}],"last":true,"totalPages":1,"totalElements":8,"first":true,"sort":[{"direction":"DESC","property":"optime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}],"numberOfElements":8,"size":20,"number":0}
     */

    private String errorcode;
    private String message;
    private DataBean data;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

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

    public static class DataBean {
        /**
         * content : [{"id":"ff8080816998acd50169a45c34760019","optime":"2019-03-22T07:44:59.970+0000","enterpriseId":"1205000127","enterpriseName":"陕西志华实业有限公司","userId":"ff8080816998acd50169a45c344f0018","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000127","frIdentity":"610325490910551"},{"id":"ff8080816998acd50169a456cff00016","optime":"2019-03-22T07:39:06.495+0000","enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","userId":"ff8080816998acd50169a456cf960015","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000131","frIdentity":"612129520321005"},{"id":"ff8080816998acd501699e0ca0890009","optime":"2019-03-21T02:20:21.243+0000","enterpriseId":"1205000117","enterpriseName":"陕西涤玉阁书画社","userId":"ff8080816998acd501699e0c9fa20008","gsjId":"5850e3a801f041ea994ef5b517516659","tyshxydm":"1205000117","frIdentity":"610104610410511"},{"id":"ff808081648e1f420164b5230b180098","optime":"2018-07-20T00:42:09.882+0000","enterpriseId":"22054115","enterpriseName":"西安永明装饰材料实业公司","userId":"ff808081648e1f420164b5230a990097","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","tyshxydm":"22054115","frIdentity":"610121540116285"},{"id":"ff808081648e1f420164b0106ca7008d","optime":"2018-07-19T01:03:43.702+0000","enterpriseId":"1205000153","enterpriseName":"陕西中华创意信息有限公司","userId":"ff808081648e1f420164b0106c5c008c","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","tyshxydm":"1205000153","frIdentity":"430302130121251"},{"id":"ff8080816488b31c01648c0f7cf20011","optime":"2018-07-12T01:16:22.541+0000","enterpriseId":"1205000130","enterpriseName":"陕西克莱玛实业有限公司","userId":"ff8080816488b31c01648c0f7c960010","gsjId":"3abb1c09ea4d4da29e0e431ad2d6f76c","tyshxydm":"1205000130","frIdentity":"610103530807003"},{"id":"ff8080816488a9e1016488ae7a450003","optime":"2018-07-11T09:31:33.268+0000","enterpriseId":"1205000136","enterpriseName":"陕西蓝马广告实业有限公司","userId":"ff8080816488a9e1016488ae7a1a0002","gsjId":"a90e2a364b364c87b41ff8b33e9752f1","tyshxydm":"1205000136","frIdentity":"610112391231102"},{"id":"ff80808164889df00164889f8e080001","optime":"2018-07-11T09:15:15.190+0000","enterpriseId":"1205000131","enterpriseName":"陕西宏飞实业有限公司","userId":"ff80808164889df00164889f8da40000","gsjId":"57134b7a55fb4d0db2b7283abc9733f1","tyshxydm":"1205000131","frIdentity":"612129520321005"}]
         * last : true
         * totalPages : 1
         * totalElements : 8
         * first : true
         * sort : [{"direction":"DESC","property":"optime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}]
         * numberOfElements : 8
         * size : 20
         * number : 0
         */

        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
            /**
             * id : ff8080816998acd50169a45c34760019
             * optime : 2019-03-22T07:44:59.970+0000
             * enterpriseId : 1205000127
             * enterpriseName : 陕西志华实业有限公司
             * userId : ff8080816998acd50169a45c344f0018
             * gsjId : 5850e3a801f041ea994ef5b517516659
             * tyshxydm : 1205000127
             * frIdentity : 610325490910551
             */

            private String id;
            private String optime;
            private String enterpriseId;
            private String enterpriseName;
            private String userId;
            private String gsjId;
            private String tyshxydm;
            private String frIdentity;

            public boolean isselect() {
                return isselect;
            }

            public void setIsselect(boolean isselect) {
                this.isselect = isselect;
            }

            private boolean isselect;

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getGsjId() {
                return gsjId;
            }

            public void setGsjId(String gsjId) {
                this.gsjId = gsjId;
            }

            public String getTyshxydm() {
                return tyshxydm;
            }

            public void setTyshxydm(String tyshxydm) {
                this.tyshxydm = tyshxydm;
            }

            public String getFrIdentity() {
                return frIdentity;
            }

            public void setFrIdentity(String frIdentity) {
                this.frIdentity = frIdentity;
            }
        }

        public static class SortBean {
            /**
             * direction : DESC
             * property : optime
             * ignoreCase : false
             * nullHandling : NATIVE
             * ascending : false
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean ascending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }
        }
    }
}
