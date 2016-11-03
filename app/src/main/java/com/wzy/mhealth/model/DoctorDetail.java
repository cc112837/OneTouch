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
     * priceVideo : 3
     * image : 2.jpg
     * recommend : 9
     * pricePicture : 3
     * pricePrivate : 3
     * pricePhone : 3
     * id : 19
     * priceAdd : 3
     * firstdep : 妇科
     * doctorId : 57ecc97cda2f60004f451fe0
     * userEvaluate : 18369956786
     * userName : 吴聪聪
     * doctorTilte : 主治医生
     * specialization :  妇科
     * hospital : 西安交通大学第一医院
     * introduction :  毕业于西安交通大学
     */

    private int priceVideo;
    private String image;
    private int recommend;
    private double pricePicture;
    private double pricePrivate;
    private double pricePhone;
    private int id;
    private int priceAdd;
    private String firstdep;
    private String doctorId;
    private String userEvaluate;
    private String userName;
    private String doctorTilte;
    private String specialization;
    private String hospital;
    private String introduction;

    public void setPriceVideo(int priceVideo) {
        this.priceVideo = priceVideo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void setPricePicture(int pricePicture) {
        this.pricePicture = pricePicture;
    }

    public void setPricePrivate(int pricePrivate) {
        this.pricePrivate = pricePrivate;
    }

    public void setPricePhone(int pricePhone) {
        this.pricePhone = pricePhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriceAdd(int priceAdd) {
        this.priceAdd = priceAdd;
    }

    public void setFirstdep(String firstdep) {
        this.firstdep = firstdep;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setUserEvaluate(String userEvaluate) {
        this.userEvaluate = userEvaluate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDoctorTilte(String doctorTilte) {
        this.doctorTilte = doctorTilte;
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

    public int getPriceVideo() {
        return priceVideo;
    }

    public String getImage() {
        return image;
    }

    public int getRecommend() {
        return recommend;
    }

    public double getPricePicture() {
        return pricePicture;
    }

    public double getPricePrivate() {
        return pricePrivate;
    }

    public double getPricePhone() {
        return pricePhone;
    }

    public int getId() {
        return id;
    }

    public int getPriceAdd() {
        return priceAdd;
    }

    public String getFirstdep() {
        return firstdep;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getUserEvaluate() {
        return userEvaluate;
    }

    public String getUserName() {
        return userName;
    }

    public String getDoctorTilte() {
        return doctorTilte;
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
