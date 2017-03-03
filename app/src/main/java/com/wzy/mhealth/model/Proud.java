package com.wzy.mhealth.model;

import java.io.Serializable;

/**
 * 项目名称：mhealth
 * 类描述：积分以及勋章展示
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/9 16:35
 * 修改人：Administrator
 * 修改时间：2016/10/9 16:35
 * 修改备注：
 */
public class Proud implements Serializable{

    /**
     * shop : false
     * stepNum : false
     * integration : 40
     * blood : false
     * couponNum : 2
     */

    private boolean shop;
    private boolean stepNum;
    private int integration;
    private boolean blood;
    private int couponNum;

    public void setShop(boolean shop) {
        this.shop = shop;
    }

    public void setStepNum(boolean stepNum) {
        this.stepNum = stepNum;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public void setBlood(boolean blood) {
        this.blood = blood;
    }

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public boolean isShop() {
        return shop;
    }

    public boolean isStepNum() {
        return stepNum;
    }

    public int getIntegration() {
        return integration;
    }

    public boolean isBlood() {
        return blood;
    }

    public int getCouponNum() {
        return couponNum;
    }
}
