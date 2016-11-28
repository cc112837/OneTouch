package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/14 13:50
 * 修改人：Administrator
 * 修改时间：2016/11/14 13:50
 * 修改备注：
 */
public class Address {


    /**
     * addressNum : 4
     * data : [{"userName":"梦想宇宙","name":"小邢","telephone":"888182569","address":"有机青稞米100g","sid":1,"addressId":"f5a2e99d5884b056015884b056520000"}]
     */

    private int addressNum;
    /**
     * userName : 梦想宇宙
     * name : 小邢
     * telephone : 888182569
     * address : 有机青稞米100g
     * sid : 1
     * addressId : f5a2e99d5884b056015884b056520000
     */

    private List<DataEntity> data;

    public void setAddressNum(int addressNum) {
        this.addressNum = addressNum;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getAddressNum() {
        return addressNum;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable{
        private String userName;
        private String name;
        private String telephone;
        private String address;
        private int sid;
        private String addressId;

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

        public void setSid(int sid) {
            this.sid = sid;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
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

        public int getSid() {
            return sid;
        }

        public String getAddressId() {
            return addressId;
        }
    }
}
