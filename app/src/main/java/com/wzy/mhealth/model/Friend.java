package com.wzy.mhealth.model;


public class Friend {
	public String id;
	public String username;
	public String userHead;
	public String mood;
	public boolean isChose;

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

	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public boolean isChose() {
		return isChose;
	}

	public void setIsChose(boolean isChose) {
		this.isChose = isChose;
	}

	public Friend(String username) {
		super();
		this.username = username;
	}


	public Friend(String id, String username,  String mood) {
		super();
		this.id = id;
		this.username = username;
		this.mood = mood;
	}
	
	@Override
	 public boolean equals(Object obj) {
	  boolean isEqual = false;
	  if (obj instanceof Friend) {
		  Friend t = (Friend) obj;
		  isEqual = this.username.equals(t.username);
		  return isEqual;
	  }
	  return super.equals(obj);
	 }
}
