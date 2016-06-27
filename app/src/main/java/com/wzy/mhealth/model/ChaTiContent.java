package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/24 14:46
 * 修改人：Administrator
 * 修改时间：2016/6/24 14:46
 * 修改备注：
 */
public class ChaTiContent {
    // TODO: 2016/6/24
    Boolean state;
    String itemname;
    String waitcount;
    String name;
    String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getWaitcount() {
        return waitcount;
    }

    public void setWaitcount(String waitcount) {
        this.waitcount = waitcount;
    }
}
