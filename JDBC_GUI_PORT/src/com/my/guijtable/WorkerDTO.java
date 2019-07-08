package com.my.guijtable;

public class WorkerDTO {
	
	int workerno;
	String name;
	String address;
	String phonenum;
	String id;
	String pw;
	public WorkerDTO(int workerno, String name, String address, String phonenum, String id, String pw) {
		super();
		this.workerno = workerno;
		this.name = name;
		this.address = address;
		this.phonenum = phonenum;
		this.id = id;
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public WorkerDTO() {
		super();
	}
	public WorkerDTO(int workerno, String name, String address, String phonenum) {
		super();
		this.workerno = workerno;
		this.name = name;
		this.address = address;
		this.phonenum = phonenum;
	}
	public int getWorkerno() {
		return workerno;
	}
	public void setWorkerno(int workerno) {
		this.workerno = workerno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	@Override
	public String toString() {
		return "WorkerDTO [workerno=" + workerno + ", name=" + name + ", address=" + address + ", phonenum=" + phonenum
				+ ", id=" + id + ", pw=" + pw + "]";
	}
	
	
	
}
