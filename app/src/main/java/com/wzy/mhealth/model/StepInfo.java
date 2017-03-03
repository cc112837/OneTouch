package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：当前步数提交
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/1 11:04
 * 修改人：Administrator
 * 修改时间：2016/9/1 11:04
 * 修改备注：
 */
public class StepInfo {

    /**
     * status : 1
     * data : 步数提交成功
     */

    private String status;
    private String data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }
}
