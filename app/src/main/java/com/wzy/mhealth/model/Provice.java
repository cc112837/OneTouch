package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/28 17:14
 * 修改人：Administrator
 * 修改时间：2016/9/28 17:14
 * 修改备注：
 */
public class Provice {

    /**
     * id : 110000
     * sort : 9
     * provice : 北京市
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int sort;
        private String provice;

        public void setId(int id) {
            this.id = id;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public int getId() {
            return id;
        }

        public int getSort() {
            return sort;
        }

        public String getProvice() {
            return provice;
        }
    }
}
