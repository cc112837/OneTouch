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
     * data : [{"sid":1,"area":"陕西西安阎良","address":"黄山路","name":"小樱","telephone":"185****8884","addressId":"f5a2e99d58aec9e60158af329c440010"}]
     * addressNum : 1
     */

    private int addressNum;
    /**
     * sid : 1
     * area : 陕西西安阎良
     * address : 黄山路
     * name : 小樱
     * telephone : 185****8884
     * addressId : f5a2e99d58aec9e60158af329c440010
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
        private int sid;
        private String area;
        private String address;
        private String name;
        private String telephone;
        private String addressId;

        public void setSid(int sid) {
            this.sid = sid;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public int getSid() {
            return sid;
        }

        public String getArea() {
            return area;
        }

        public String getAddress() {
            return address;
        }

        public String getName() {
            return name;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getAddressId() {
            return addressId;
        }
    }
}
