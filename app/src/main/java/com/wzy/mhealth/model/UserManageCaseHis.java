package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：用户管理病历修改
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/15 10:10
 * 修改人：Administrator
 * 修改时间：2017/2/15 10:10
 * 修改备注：
 */

public class UserManageCaseHis {
    /**
     * status : 1
     * userManageId : 546200fe
     * data : 保存修改成功
     */

    private String status;
    private String userManageId;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserManageId() {
        return userManageId;
    }

    public void setUserManageId(String userManageId) {
        this.userManageId = userManageId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
