package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/26 9:13
 * 修改人：Administrator
 * 修改时间：2016/11/26 9:13
 * 修改备注：
 */
public class DefaultAdress {

    /**
     * status : 1
     * data : 默认地址存在
     * userName : 梦想宇宙
     * name : 小邢
     * telephone : 888182569
     * address : 有机青稞米100g
     * addressId : f5a2e99d5884b056015884b056520000
     */

    private String status;
    private String data;
    private String userName;
    private String name;
    private String telephone;
    private String address;
    private String addressId;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressId() {
        return addressId;
    }
}
