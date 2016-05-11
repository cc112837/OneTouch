package com.wzy.mhealth.model;


public class GuaHao {
	private String id;
	private String username;
	private String date;
	private String week;
	private int number;
	private String mon;
	private String menzhen;
	
	
	public GuaHao() {
	}

	public GuaHao(String date,String week,int number) {
		super();
		this.setDate(date);
		this.setWeek(week);
		this.setNumber(number);
	}
	
	@Override
	 public boolean equals(Object obj) {
	  boolean isEqual = false;
	  if (obj instanceof GuaHao) {
		  GuaHao t = (GuaHao) obj;
		  isEqual = this.username.equals(t.username);
		  return isEqual;
	  }
	  return super.equals(obj);
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getMenzhen() {
		return menzhen;
	}

	public void setMenzhen(String menzhen) {
		this.menzhen = menzhen;
	}
}
