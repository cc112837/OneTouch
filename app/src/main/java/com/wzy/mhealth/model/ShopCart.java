package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品购物车信息
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/28 11:52
 * 修改人：Administrator
 * 修改时间：2016/11/28 11:52
 * 修改备注：
 */
public class ShopCart {


    /**
     * prefPrice  : 0.0
     * data : [{"productNewPrice":10.8,"productNumber ":10,"productName":"小邢","productImageSmall ":"888182569","totalPrice  ":50.23,"productId":"f5a2e99d5884b056015884b056520000"}]
     */

    private double prefPrice;
    /**
     * productNewPrice : 10.8
     * productNumber  : 10
     * productName : 小邢
     * productImageSmall  : 888182569
     * totalPrice   : 50.23
     * productId : f5a2e99d5884b056015884b056520000
     */

    private List<DataEntity> data;

    public void setPrefPrice(double prefPrice) {
        this.prefPrice = prefPrice;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public double getPrefPrice() {
        return prefPrice;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable{
        private double productNewPrice;
        private int productNumber;
        private String productName;
        private String productImageSmall;
        private double totalPrice;
        private String productId;

        public void setProductNewPrice(double productNewPrice) {
            this.productNewPrice = productNewPrice;
        }

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setProductImageSmall(String productImageSmall) {
            this.productImageSmall = productImageSmall;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public double getProductNewPrice() {
            return productNewPrice;
        }

        public int getProductNumber() {
            return productNumber;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductImageSmall() {
            return productImageSmall;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public String getProductId() {
            return productId;
        }
    }
}
