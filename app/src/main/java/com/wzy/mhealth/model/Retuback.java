package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/19 16:32
 * 修改人：Administrator
 * 修改时间：2016/9/19 16:32
 * 修改备注：
 */
public class Retuback {


    /**
     * status : 1
     * message : 退款成功
     */

    private String status;
    private String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
