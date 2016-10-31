package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/26 15:30
 * 修改人：Administrator
 * 修改时间：2016/10/26 15:30
 * 修改备注：
 */
public class ReDefine {

    /**
     * status : 1
     * data : 存在推荐套餐
     * pageCount  : 0
     */

    private String status;
    private String data;
    private int pageCount;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public int getPageCount() {
        return pageCount;
    }
}
