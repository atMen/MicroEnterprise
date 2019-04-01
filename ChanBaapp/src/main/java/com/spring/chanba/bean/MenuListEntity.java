package com.spring.chanba.bean;

import java.io.Serializable;

/**
 * 菜单实体类
 */
public class MenuListEntity implements Serializable {
    private int id;
    private String name;
    private int icon;

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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
