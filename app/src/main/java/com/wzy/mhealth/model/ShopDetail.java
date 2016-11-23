package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/15 15:28
 * 修改人：Administrator
 * 修改时间：2016/11/15 15:28
 * 修改备注：
 */
public class ShopDetail {

    /**
     * productImageBig : upload/files/20161121102139jprZEZCB.png
     * status : 1
     * productNewPrice : 10.8
     * data : 存在此商品
     * productName : 有机青稞米100g
     * productId : f5a2e99d5884b056015884b056520000
     */

    private String productImageBig;
    private String status;
    private double productNewPrice;
    private String data;
    private String productName;
    private String productId;

    public void setProductImageBig(String productImageBig) {
        this.productImageBig = productImageBig;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProductNewPrice(double productNewPrice) {
        this.productNewPrice = productNewPrice;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImageBig() {
        return productImageBig;
    }

    public String getStatus() {
        return status;
    }

    public double getProductNewPrice() {
        return productNewPrice;
    }

    public String getData() {
        return data;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }
}
