package com.tcrj.micro.entity;

/**
 * Created by leict on 2019/4/2.
 */

public class zwCodeList {

    /**
     * code : 10701
     * deleted : 0
     * id : 10701
     * isParent : true
     * name : 销售|客服|市场
     * newStyle : 1
     * note :
     * optime : 2019-03-28 17:42:03
     * parentId : 107
     * path : #1#107#10701#
     * sort : 1
     */

    private String code;
    private String deleted;
    private String id;
    private boolean isParent;
    private String name;
    private String newStyle;
    private String note;
    private String optime;
    private String parentId;
    private String path;
    private int sort;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewStyle() {
        return newStyle;
    }

    public void setNewStyle(String newStyle) {
        this.newStyle = newStyle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
