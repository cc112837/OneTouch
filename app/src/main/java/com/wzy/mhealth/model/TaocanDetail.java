package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/12 10:18
 * 修改人：Administrator
 * 修改时间：2016/9/12 10:18
 * 修改备注：
 */
public class TaocanDetail {


    /**
     * taoId : 1
     * oldPrice : 228
     * newPrice : 79
     * name : 入职套餐
     * context : 11
     */

    private int taoId;
    private int oldPrice;
    private int newPrice;
    private String name;
    private String context;

    public void setTaoId(int taoId) {
        this.taoId = taoId;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTaoId() {
        return taoId;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }
}
