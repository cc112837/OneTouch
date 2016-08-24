package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/22 16:04
 * 修改人：Administrator
 * 修改时间：2016/8/22 16:04
 * 修改备注：
 */
public class ZhixingTaocan {
    private String name;
    private int oldprice;
    private int newprice;

    public ZhixingTaocan(String name, int oldprice, int newprice) {
        this.name = name;
        this.oldprice = oldprice;
        this.newprice = newprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOldprice() {
        return oldprice;
    }

    public void setOldprice(int oldprice) {
        this.oldprice = oldprice;
    }

    public int getNewprice() {
        return newprice;
    }

    public void setNewprice(int newprice) {
        this.newprice = newprice;
    }
}
