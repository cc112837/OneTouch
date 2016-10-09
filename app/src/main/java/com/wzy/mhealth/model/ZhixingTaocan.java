package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/22 16:04
 * 修改人：Administrator
 * 修改时间：2016/8/22 16:04
 * 修改备注：
 */
public class ZhixingTaocan {


    /**
     * phone : 029-68018969
     * details :  详情
     * name : 智行体检中心
     * data : [{"id":15,"oldPrice":0.01,"newPrice":0.01,"name":"ceishi4"}]
     * img : http://117.34.105.29:8209/mhealth/upload/1475060756700.jpg
     */

    private String phone;
    private String details;
    private String name;
    private String img;
    private String adress;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * id : 15
     * oldPrice : 0.01
     * newPrice : 0.01
     * name : ceishi4
     */

    private List<DataEntity> data;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private double oldPrice;
        private double newPrice;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setOldPrice(double oldPrice) {
            this.oldPrice = oldPrice;
        }

        public void setNewPrice(double newPrice) {
            this.newPrice = newPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public double getOldPrice() {
            return oldPrice;
        }

        public double getNewPrice() {
            return newPrice;
        }

        public String getName() {
            return name;
        }
    }
}
