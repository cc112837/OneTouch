package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/18 10:16
 * 修改人：Administrator
 * 修改时间：2016/11/18 10:16
 * 修改备注：
 */
public class ShopOrder {


    /**
     * data : [{"shopStatus":"未发货","bussinessName":"经稞","shopPrice":0.01,"shopName":"有机青稞米500g","shopImage":"http://117.34.105.29:8818/mhealth/upload/files/20161128141803jIaDjJeU.jpg","shopNum":1,"bussinessImage":"http://117.34.105.29:8818/mhealth/upload/files/20161130152742E51RY7b3.jpg","logistate":"您的订单已支付，还未发货，请等待","shopDate":"2016-11-30 11:31:10.0"}]
     * number : 1
     */

    private int number;
    /**
     * shopStatus : 未发货
     * bussinessName : 经稞
     * shopPrice : 0.01
     * shopName : 有机青稞米500g
     * shopImage : http://117.34.105.29:8818/mhealth/upload/files/20161128141803jIaDjJeU.jpg
     * shopNum : 1
     * bussinessImage : http://117.34.105.29:8818/mhealth/upload/files/20161130152742E51RY7b3.jpg
     * logistate : 您的订单已支付，还未发货，请等待
     * shopDate : 2016-11-30 11:31:10.0
     */

    private List<DataEntity> data;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String shopStatus;
        private String bussinessName;
        private double shopPrice;
        private String shopName;
        private String shopImage;
        private int shopNum;
        private String bussinessImage;
        private String logistate;
        private String shopDate;

        public void setShopStatus(String shopStatus) {
            this.shopStatus = shopStatus;
        }

        public void setBussinessName(String bussinessName) {
            this.bussinessName = bussinessName;
        }

        public void setShopPrice(double shopPrice) {
            this.shopPrice = shopPrice;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public void setShopNum(int shopNum) {
            this.shopNum = shopNum;
        }

        public void setBussinessImage(String bussinessImage) {
            this.bussinessImage = bussinessImage;
        }

        public void setLogistate(String logistate) {
            this.logistate = logistate;
        }

        public void setShopDate(String shopDate) {
            this.shopDate = shopDate;
        }

        public String getShopStatus() {
            return shopStatus;
        }

        public String getBussinessName() {
            return bussinessName;
        }

        public double getShopPrice() {
            return shopPrice;
        }

        public String getShopName() {
            return shopName;
        }

        public String getShopImage() {
            return shopImage;
        }

        public int getShopNum() {
            return shopNum;
        }

        public String getBussinessImage() {
            return bussinessImage;
        }

        public String getLogistate() {
            return logistate;
        }

        public String getShopDate() {
            return shopDate;
        }
    }
}
