package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/24 13:29
 * 修改人：Administrator
 * 修改时间：2016/10/24 13:29
 * 修改备注：
 */
public class Recommend {

    /**
     * status : 1
     * data : 存在推荐套餐
     * taocanId : 1
     * taocanName : 青年套餐
     * oldPrice : 568
     * newPrice : 268
     * context : 详情
     * centerId : 关联的机构
     * taocanNum : 7
     */

    private String status;
    private String data;
    private int taocanId;
    private String taocanName;
    private String oldPrice;
    private String newPrice;
    private String context;
    private String centerId;
    private String taocanNum;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTaocanId(int taocanId) {
        this.taocanId = taocanId;
    }

    public void setTaocanName(String taocanName) {
        this.taocanName = taocanName;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public void setTaocanNum(String taocanNum) {
        this.taocanNum = taocanNum;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public int getTaocanId() {
        return taocanId;
    }

    public String getTaocanName() {
        return taocanName;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getContext() {
        return context;
    }

    public String getCenterId() {
        return centerId;
    }

    public String getTaocanNum() {
        return taocanNum;
    }
}
