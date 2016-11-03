package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/26 11:54
 * 修改人：Administrator
 * 修改时间：2016/9/26 11:54
 * 修改备注：
 */
public class Doctor implements Serializable {

    /**
     * id : 1
     * sex : null
     * firstdep : Internal medicine
     * image : null
     * userName : Xiaoguang Li
     * pricePicture : 1
     * recommend : 98
     * hospital :
     * pricePhone : null
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable{
        private int id;
        private String sex;
        private String firstdep;
        private String image;
        private String userName;
        private double pricePicture;
        private int recommend;
        private String hospital;
        private double pricePhone;
        private String specialization;
        private String doctorTilte;
        private String doctorId;

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorTilte() {
            return doctorTilte;
        }

        public void setDoctorTilte(String doctorTilte) {
            this.doctorTilte = doctorTilte;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setFirstdep(String firstdep) {
            this.firstdep = firstdep;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setPricePicture(int pricePicture) {
            this.pricePicture = pricePicture;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public void setPricePhone(double pricePhone) {
            this.pricePhone = pricePhone;
        }

        public int getId() {
            return id;
        }

        public Object getSex() {
            return sex;
        }

        public String getFirstdep() {
            return firstdep;
        }

        public Object getImage() {
            return image;
        }

        public String getUserName() {
            return userName;
        }

        public double getPricePicture() {
            return pricePicture;
        }

        public int getRecommend() {
            return recommend;
        }

        public String getHospital() {
            return hospital;
        }

        public Object getPricePhone() {
            return pricePhone;
        }
    }
}
