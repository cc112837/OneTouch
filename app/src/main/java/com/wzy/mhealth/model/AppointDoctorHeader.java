package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：选择预约医生信息
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/13 11:14
 * 修改人：Administrator
 * 修改时间：2017/2/13 11:14
 * 修改备注：
 */

public class AppointDoctorHeader {

    /**
     * appointTime : 01-14上午
     * doctorImage : http://117.34.105.29:8818/mhealth/upload/2.jpg
     * firstdep : 妇科
     * doctorName : 吴聪聪
     * hospital : 西安交通大学第一医院
     * adrress : 18369956786
     * doctorTitle : 主治医生
     */

    private String appointTime;
    private String doctorImage;
    private String firstdep;
    private String doctorName;
    private String hospital;
    private String adrress;
    private String doctorTitle;

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }

    public String getFirstdep() {
        return firstdep;
    }

    public void setFirstdep(String firstdep) {
        this.firstdep = firstdep;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }
}
