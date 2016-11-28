package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/28 11:52
 * 修改人：Administrator
 * 修改时间：2016/11/28 11:52
 * 修改备注：
 */
public class ShopCart {


    /**
     * productNumber : 3
     * productNewPrice : 258
     * productImageSmall : http://117.34.105.29:8818/mhealth/upload/files/20161128141912FBHYgXcG.jpg
     * productName : 有机青稞米2.5kg礼盒装
     * totalPrice : 774
     * productId : f5a2e99d5884b056015884c5f2c80003
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable{
        private int productNumber;
        private double productNewPrice;
        private String productImageSmall;
        private String productName;
        private double totalPrice;
        private String productId;

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public void setProductNewPrice(int productNewPrice) {
            this.productNewPrice = productNewPrice;
        }

        public void setProductImageSmall(String productImageSmall) {
            this.productImageSmall = productImageSmall;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getProductNumber() {
            return productNumber;
        }

        public double getProductNewPrice() {
            return productNewPrice;
        }

        public String getProductImageSmall() {
            return productImageSmall;
        }

        public String getProductName() {
            return productName;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public String getProductId() {
            return productId;
        }
    }
}
