package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/23 10:14
 * 修改人：Administrator
 * 修改时间：2016/9/23 10:14
 * 修改备注：
 */
public class SelfHealth {
    private int number;
    /**
     * firstDepName : 妇科
     * firstDepId : 1
     */

    private List<DataEntity> data;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String firstDepName;
        private int firstDepId;

        public void setFirstDepName(String firstDepName) {
            this.firstDepName = firstDepName;
        }

        public void setFirstDepId(int firstDepId) {
            this.firstDepId = firstDepId;
        }

        public String getFirstDepName() {
            return firstDepName;
        }

        public int getFirstDepId() {
            return firstDepId;
        }
    }
}
