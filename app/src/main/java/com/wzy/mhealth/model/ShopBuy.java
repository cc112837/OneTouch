package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：购物车
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/29 13:52
 * 修改人：Administrator
 * 修改时间：2016/11/29 13:52
 * 修改备注：
 */
public class ShopBuy {

    /**
     * status : 1
     * productNum : 18
     * data : 购物车有数量
     */

    private String status;
    private int productNum;
    private String data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public int getProductNum() {
        return productNum;
    }

    public String getData() {
        return data;
    }
}
