package com.wzy.mhealth.model;

import java.io.Serializable;

public class DoctorEntity implements Serializable {

	private String doctorName;// 医生名字
	private String doctorUsername;// 医生username
	private String doctorObjectId;// 医生ObjectId
	private String firstdepName;// 科室
	private String doctorTitle;// 职称
	private String hospitalName;// 医院
	private String specialization;// 专长简介
	private String introduction;// 毕业院校及学位
//	private Image image;// 医生头像
	private String price_picture;// 图文咨询价格
	private String price_phone;// 电话咨询价格
	private String price_private, price_add, price_vedio;
	private String recommend;//推荐指数
	private String attitude;//服务态度评分
	private String level;//医术水平评分

	public DoctorEntity(String name, String doctorid, String department,
			String zhicheng, String hospital, String brief,String level) {
		// TODO Auto-generated constructor stub
		this.doctorName = name;
		this.level=level;
		this.doctorUsername = doctorid;
		this.firstdepName = department;
		this.doctorTitle = zhicheng;
		this.hospitalName = hospital;
		this.specialization = brief;
	}
	public DoctorEntity(String name, String doctorid){
		this.doctorName = name;
		this.doctorUsername = doctorid;
	}

	public DoctorEntity(String name, String doctorid, String department,
			String zhicheng, String hospital, String brief,String price_picture,
			String price_phone,String price_private,String level ) {
		// TODO Auto-generated constructor stub
		this.doctorName = name;
		this.level=level;
		this.doctorUsername = doctorid;
		this.firstdepName = department;
		this.doctorTitle = zhicheng;
		this.hospitalName = hospital;
		this.specialization = brief;
		this.price_picture = price_picture;
		this.price_phone = price_phone;
		this.price_private = price_private;
		this.price_add = price_picture;
		this.price_vedio = price_picture;
	}

	public DoctorEntity() {
	}

	public String getName() {
		return doctorName;
	}

	public void setName(String name) {
		this.doctorName = name;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDactroUsername(String doctorid) {
		this.doctorUsername = doctorid;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFirstdepName() {
		return firstdepName;
	}

	public void setFirstdepName(String firstdepName) {
		this.firstdepName = firstdepName;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
/*
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/

	public String getPrice_picture() {
		return price_picture;
	}

	public void setPrice_picture(String price_picture) {
		this.price_picture = price_picture;
	}

	public String getPrice_phone() {
		return price_phone;
	}

	public void setPrice_phone(String price_phone) {
		this.price_phone = price_phone;
	}

	public String getPrice_private() {
		return price_private;
	}

	public void setPrice_private(String price_private) {
		this.price_private = price_private;
	}

	public String getPrice_add() {
		return price_add;
	}

	public void setPrice_add(String price_add) {
		this.price_add = price_add;
	}

	public String getPrice_vedio() {
		return price_vedio;
	}

	public void setPrice_vedio(String price_vedio) {
		this.price_vedio = price_vedio;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDoctorObjectId() {
		return doctorObjectId;
	}

	public void setDoctorObjectId(String doctorObjectId) {
		this.doctorObjectId = doctorObjectId;
	}

}
