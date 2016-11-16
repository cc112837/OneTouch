package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/16 10:22
 * 修改人：Administrator
 * 修改时间：2016/11/16 10:22
 * 修改备注：
 */
public class Pridefine {

    /**
     * taoId : 79
     * newPrice : 0.01
     * name : 私人医生套餐定金
     */

    private int taoId;
    private double newPrice;
    private String name;

    public void setTaoId(int taoId) {
        this.taoId = taoId;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaoId() {
        return taoId;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public String getName() {
        return name;
    }
}
