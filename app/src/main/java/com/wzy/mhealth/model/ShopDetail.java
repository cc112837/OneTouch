package com.wzy.mhealth.model;

import java.util.List;

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
     * productImageBig : ["http://117.34.105.29:8818/mhealth/upload/files/1477387864441.jpg","http://117.34.105.29:8818/mhealth/upload/files/1477387864444.jpg"]
     * status : 1
     * productNewPrice : 49.8
     * data : 存在此商品
     * productName : 有机青稞米500g
     * productId : f5a2e99d5884b056015884b8ac890001
     */

    private String status;
    private double productNewPrice;
    private String data;
    private String productName;
    private String productId;
    private List<String> productImageBig;

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

    public void setProductImageBig(List<String> productImageBig) {
        this.productImageBig = productImageBig;
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

    public List<String> getProductImageBig() {
        return productImageBig;
    }
}
