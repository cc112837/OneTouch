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
     * id : 1
     * oldPrice : 228
     * newPrice : 79
     * name : 入职套餐
     * context : http://localhost:8080/mhealth/upload/upload\160829023423542.png
     */

    private int id;
    private int oldPrice;
    private int newPrice;
    private String name;
    private String context;

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
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
