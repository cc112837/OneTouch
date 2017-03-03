package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：推荐套餐的内容
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
     * image : http://117.34.105.29:8209/mhealth/upload/1475220119774.png
     * name : 青年套餐
     * oldPrice : 568
     * newPrice : 268
     * context : 详情
     * taoId : 21
     * taocanNum : 7
     */

    private String status;
    private String data;
    private String image;
    private String name;
    private String oldPrice;
    private String newPrice;
    private String context;
    private int taoId;
    private String taocanNum;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setTaoId(int taoId) {
        this.taoId = taoId;
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

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
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

    public int getTaoId() {
        return taoId;
    }

    public String getTaocanNum() {
        return taocanNum;
    }
}
