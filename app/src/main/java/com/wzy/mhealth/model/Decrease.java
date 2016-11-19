package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/19 9:29
 * 修改人：Administrator
 * 修改时间：2016/11/19 9:29
 * 修改备注：
 */
public class Decrease {
    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {

    }
}
