package com.wzy.mhealth.model;

public class YuyueDoctor {
	private String id;
	private String doctorname;
	private String doctorHead;
	private String firstdepName;// ����
	private String doctorTitle;// ְ��
	private String hospitalName;// ҽԺ
	private String date;
	private String miaoshu;

	/*public YuyueDoctor(String username) {
		super();
		this.doctorname = username;
	}*/

	public YuyueDoctor() {
		super();
	}

	public YuyueDoctor(String id, String username, String firstdepName,
			String doctorTitle, String date, String hospitalName) {
		super();
		this.setId(id);
		this.doctorname = username;
		this.setFirstdepName(firstdepName);
		this.setDoctorTitle(doctorTitle);
		this.setDate(date);
		this.hospitalName = hospitalName;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj instanceof YuyueDoctor) {
			YuyueDoctor t = (YuyueDoctor) obj;
			isEqual = this.doctorname.equals(t.doctorname);
			return isEqual;
		}
		return super.equals(obj);
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctorHead() {
		return doctorHead;
	}

	public void setDoctorHead(String doctorHead) {
		this.doctorHead = doctorHead;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getFirstdepName() {
		return firstdepName;
	}

	public void setFirstdepName(String firstdepName) {
		this.firstdepName = firstdepName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctornamee) {
		this.doctorname = doctornamee;
	}
}
