package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：用户注册结果
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/27 9:42
 * 修改人：Administrator
 * 修改时间：2016/9/27 9:42
 * 修改备注：
 */
public class Regmodel {

    /**
     * status : 1
     * userid : 1
     * mobile : 1809990988
     * data : 注册成功！
     */

    private String status;
    private String userid;
    private String mobile;
    private String data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getUserid() {
        return userid;
    }

    public String getMobile() {
        return mobile;
    }

    public String getData() {
        return data;
    }
}
