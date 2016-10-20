package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/20 8:46
 * 修改人：Administrator
 * 修改时间：2016/10/20 8:46
 * 修改备注：
 */
public class ForgetPass {

    /**
     * status : 1
     * data : 密码存在！
     * password : QyA8hnzPxPtsErv/f6wqMh/4OLecipeRybAySp8+Zg30DY+mDKyOvJFpEGKWhIQhI0G3K63d7+aO+eOL2AfzIQ==
     */

    private String status;
    private String data;
    private String password;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String getPassword() {
        return password;
    }
}
