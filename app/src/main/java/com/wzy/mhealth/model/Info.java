package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/1 9:37
 * 修改人：Administrator
 * 修改时间：2016/7/1 9:37
 * 修改备注：
 */
public class Info {

    /**
     * msg : 非法用户！
     * success : false
     */

    private String msg;
    private boolean success;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }
}
