package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/16 11:13
 * 修改人：Administrator
 * 修改时间：2017/2/16 11:13
 * 修改备注：
 */

public class DoctorBuy {

    /**
     * status : 1
     * appointId  : der234235442
     * priceAdd : 0.01
     * doctorName : 吴聪聪
     * data : 用户预约提交成功
     */

    private String status;
    private String appointId;
    private double priceAdd;
    private String doctorName;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointId() {
        return appointId;
    }

    public void setAppointId(String appointId) {
        this.appointId = appointId;
    }

    public double getPriceAdd() {
        return priceAdd;
    }

    public void setPriceAdd(double priceAdd) {
        this.priceAdd = priceAdd;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
