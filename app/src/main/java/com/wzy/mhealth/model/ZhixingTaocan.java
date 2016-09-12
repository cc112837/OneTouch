package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/22 16:04
 * 修改人：Administrator
 * 修改时间：2016/8/22 16:04
 * 修改备注：
 */
public class ZhixingTaocan {

    /**
     * id : 1
     * oldPrice : 228
     * newPrice : 79
     * name : 入职套餐
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
        private int oldPrice;
        private int newPrice;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setOldPrice(int oldPrice) {
            this.oldPrice = oldPrice;
        }

        public void setNewPrice(int newPrice) {
            this.newPrice = newPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getOldPrice() {
            return oldPrice;
        }

        public int getNewPrice() {
            return newPrice;
        }

        public String getName() {
            return name;
        }
    }
}
