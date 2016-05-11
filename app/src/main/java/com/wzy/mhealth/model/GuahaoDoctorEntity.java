package com.wzy.mhealth.model;

import java.io.Serializable;

public class GuahaoDoctorEntity implements Serializable {

	private String name;
	private String doctorid;
	private String zhicheng;
	private String pingfen;
	private String jiezhenliang;
	private String brief; 
	private int youhao;
	private String registerFee;
	private String outpatientCatagory;
	
	public GuahaoDoctorEntity(String name,String doctorid,String zhicheng,String pingfen,String jiezhenliang,String brief,int youhao) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.doctorid = doctorid;
		this.pingfen = pingfen;
		this.zhicheng = zhicheng;
		this.jiezhenliang = jiezhenliang;
		this.brief = brief;
		this.setYouhao(youhao);
	}
	public GuahaoDoctorEntity() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoctorId() {
		return doctorid;
	}
	public void setDactroId(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getZhicheng() {
		return zhicheng;
	}
	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}
	public String getPingfen() {
		return pingfen;
	}
	public void setPingfen(String pingfen) {
		this.pingfen = pingfen;
	}
	public String getJiezhenliang() {
		return jiezhenliang;
	}
	public void setJiezhenliang(String jiezhenliang) {
		this.jiezhenliang = jiezhenliang;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getRegisterFee() {
		return registerFee;
	}
	public void setRegisterFee(String registerFee) {
		this.registerFee = registerFee;
	}
	public String getOutpatientCatagory() {
		return outpatientCatagory;
	}
	public void setOutpatientCatagory(String outpatientCatagory) {
		this.outpatientCatagory = outpatientCatagory;
	}
	public int getYouhao() {
		return youhao;
	}
	public void setYouhao(int youhao) {
		this.youhao = youhao;
	}
}
