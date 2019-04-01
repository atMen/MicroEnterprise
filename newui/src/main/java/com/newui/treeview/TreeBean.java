package com.newui.treeview;

public class TreeBean {
	private int id;
	private int pid;
	private String lable;
	private Object object;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {this.pid = pid;}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public TreeBean(int id, int pid, String lable, Object object) {
		super();
		this.id = id;
		this.pid = pid;
		this.lable = lable;
		this.object = object;
	}

}
