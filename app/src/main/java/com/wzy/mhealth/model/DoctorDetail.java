package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/26 15:21
 * 修改人：Administrator
 * 修改时间：2016/9/26 15:21
 * 修改备注：
 */
public class DoctorDetail {

    /**
     * outpatientCatagory : Ordinary
     * recommend : 93
     * pricePicture : 4
     * pricePhone : 6
     * id : 3
     * attitude : 90
     * firstdep : Internal medicine
     * level : 90
     * userName : YIn Min
     * doctorTilte : Associate Chief Physician
     * registerFee : 20
     * specialization : Chest tightness, chest pain, heart failure
     * hospital : Chinese people's Liberation Army General Hospital (301 hospital)
     * introduction : Cardiovascular Medicine for 5 years, and senior attending physician, master of medicine
     Cardiovascular Medicine for 5 years, and senior attending physician, master of medicine
     */

    private String outpatientCatagory;
    private int recommend;
    private int pricePicture;
    private int pricePhone;
    private int id;
    private int attitude;
    private String firstdep;
    private int level;
    private String userName;
    private String doctorTilte;
    private int registerFee;
    private String specialization;
    private String hospital;
    private String introduction;
    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public void setOutpatientCatagory(String outpatientCatagory) {
        this.outpatientCatagory = outpatientCatagory;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void setPricePicture(int pricePicture) {
        this.pricePicture = pricePicture;
    }

    public void setPricePhone(int pricePhone) {
        this.pricePhone = pricePhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public void setFirstdep(String firstdep) {
        this.firstdep = firstdep;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDoctorTilte(String doctorTilte) {
        this.doctorTilte = doctorTilte;
    }

    public void setRegisterFee(int registerFee) {
        this.registerFee = registerFee;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOutpatientCatagory() {
        return outpatientCatagory;
    }

    public int getRecommend() {
        return recommend;
    }

    public int getPricePicture() {
        return pricePicture;
    }

    public int getPricePhone() {
        return pricePhone;
    }

    public int getId() {
        return id;
    }

    public int getAttitude() {
        return attitude;
    }

    public String getFirstdep() {
        return firstdep;
    }

    public int getLevel() {
        return level;
    }

    public String getUserName() {
        return userName;
    }

    public String getDoctorTilte() {
        return doctorTilte;
    }

    public int getRegisterFee() {
        return registerFee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getHospital() {
        return hospital;
    }

    public String getIntroduction() {
        return introduction;
    }
}
