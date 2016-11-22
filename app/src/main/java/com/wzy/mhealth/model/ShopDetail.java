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
     * productImageBig : upload/files/201611211030474Zujn8h6.png
     * status : 1
     * productNewPrice : 49.8
     * data : 存在此商品
     * productPackaging : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/70901479695441713.png" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/70901479695441713.png"/></p>
     * productIntroduce : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/48551479694763053.jpg" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/48551479694763053.jpg"/></p>
     * productName : 有机青稞米500g
     * productParameter : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/64621479695431603.png" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/64621479695431603.png"/></p>
     * productId : f5a2e99d5884b056015884b8ac890001
     */

    private String productImageBig;
    private String status;
    private double productNewPrice;
    private String data;
    private String productPackaging;
    private String productIntroduce;
    private String productName;
    private String productParameter;
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

    public void setProductPackaging(String productPackaging) {
        this.productPackaging = productPackaging;
    }

    public void setProductIntroduce(String productIntroduce) {
        this.productIntroduce = productIntroduce;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductParameter(String productParameter) {
        this.productParameter = productParameter;
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

    public String getProductPackaging() {
        return productPackaging;
    }

    public String getProductIntroduce() {
        return productIntroduce;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductParameter() {
        return productParameter;
    }

    public String getProductId() {
        return productId;
    }
}
