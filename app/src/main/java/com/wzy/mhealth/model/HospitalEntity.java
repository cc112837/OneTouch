package com.wzy.mhealth.model;

import java.io.Serializable;

public class HospitalEntity implements Serializable {

	private String name;//医院名字
	private String id;//医院ID
	private String grade;//医院等级
	private String yuyue;//预约量
	private String pingjia;//评价量
	private String image;
	
	public HospitalEntity(String name,String grade,String yuyue,String pingjia) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.grade = grade;	
		this.yuyue = yuyue;
		this.pingjia = pingjia;
		
	}
	public HospitalEntity() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getgrade() {
		return grade;
	}
	public void setgrade(String grade) {
		this.grade = grade;
	}
	public String getyuyueliang() {
		return yuyue;
	}
	public void setyuyue(String yuyue) {
		this.yuyue = yuyue;
	}
	public String getpingjia() {
		return pingjia;
	}
	public void setpingjia(String pingjia) {
		this.pingjia = pingjia;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
