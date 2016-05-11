package com.wzy.mhealth.model;

import java.io.Serializable;

public class CommentsEntity  implements Serializable{

	private String user;
	private String manyidu;
	private String brief;
	private String wenti;
	public CommentsEntity(String user,String manyidu,String brief,String wenti){
		this.user = user;
		this.manyidu = manyidu;
		this.brief = brief;
		this.wenti = wenti;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getManyidu() {
		return manyidu;
	}
	public void setManyidu(String manyidu) {
		this.manyidu = manyidu;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getWenti() {
		return wenti;
	}
	public void setWenti(String wenti) {
		this.wenti = wenti;
	}
	
	
}
