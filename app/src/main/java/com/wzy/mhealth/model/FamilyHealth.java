package com.wzy.mhealth.model;

public 
class FamilyHealth {
	private String memberName;
	private String memberAge;
	private String memberMedicalHistory;
	private String memberRelated;

	public FamilyHealth() {

	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberMedicalHistory() {
		return memberMedicalHistory;
	}

	public void setMemberMedicalHistory(String memberMedicalHistory) {
		this.memberMedicalHistory = memberMedicalHistory;
	}

	public String getMemberRelated() {
		return memberRelated;
	}

	public void setMemberRelated(String memberRelated) {
		this.memberRelated = memberRelated;
	}
}
