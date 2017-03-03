package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品订单
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/18 10:16
 * 修改人：Administrator
 * 修改时间：2016/11/18 10:16
 * 修改备注：
 */
public class ShopOrder {


    /**
     * data : [{"shopStatus":"待收货","bussinessName":"经稞","shopPrice":0.01,"shopId":"f5a2e99d5884b056015884c5f2c80003","shopName":"有机青稞米2.5kg礼盒装","shopImage":"http://117.34.105.29:8818/mhealth/upload/files/20161128141912FBHYgXcG.jpg","shopNum":1,"bussinessImage":"http://117.34.105.29:8818/mhealth/upload/files/20161130152818vxYRdCX7.jpg","logistate":"您的订单正在派送中，请耐心等待","shopDate":"2016-11-30 16:58:25.0","orderId":"f5a2e99d58b4363c0158b474d42b0001"}]
     * number : 1
     */

    private int number;
    /**
     * shopStatus : 待收货
     * bussinessName : 经稞
     * shopPrice : 0.01
     * shopName : 有机青稞米2.5kg礼盒装
     * shopImage : http://117.34.105.29:8818/mhealth/upload/files/20161128141912FBHYgXcG.jpg
     * shopNum : 1
     * bussinessImage : http://117.34.105.29:8818/mhealth/upload/files/20161130152818vxYRdCX7.jpg
     * logistate : 您的订单正在派送中，请耐心等待
     * shopDate : 2016-11-30 16:58:25.0
     * orderId : f5a2e99d58b4363c0158b474d42b0001
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
        private String productId;
        private String shopName;
        private String shopImage;
        private int shopNum;
        private String evaluateStatu;
        private String bussinessImage;
        private String logistate;
        private String shopDate;
        private String orderId;

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getEvaluateStatu() {
            return evaluateStatu;
        }

        public void setEvaluateStatu(String evaluateStatu) {
            this.evaluateStatu = evaluateStatu;
        }

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

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getProductId() {
            return productId;
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

        public String getOrderId() {
            return orderId;
        }
    }
}
