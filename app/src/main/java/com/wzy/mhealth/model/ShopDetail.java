package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：商品详情
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 15:14
 * 修改人：Administrator
 * 修改时间：2016/11/23 15:14
 * 修改备注：
 */
public class ShopDetail {

    /**
     * status : 1
     * data : 存在此商品
     * productPackaging : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/70901479695441713.png" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/70901479695441713.png"/></p>
     * productIntroduce : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/48551479694763053.jpg" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/48551479694763053.jpg"/></p>
     * productParameter : <p><img src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/92921479695888786.png" _src="http://117.34.105.29:8818/jeecg/plug-in/umeditor/jsp/upload1/20161121/92921479695888786.png"/></p>
     * productId : f5a2e99d5884b056015884c5f2c80003
     */

    private String status;
    private String data;
    private String productPackaging;
    private String productIntroduce;
    private String productParameter;
    private String productId;

    public void setStatus(String status) {
        this.status = status;
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

    public void setProductParameter(String productParameter) {
        this.productParameter = productParameter;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
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

    public String getProductParameter() {
        return productParameter;
    }

    public String getProductId() {
        return productId;
    }
}
