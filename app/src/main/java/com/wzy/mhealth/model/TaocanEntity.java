package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/14 8:49
 * 修改人：Administrator
 * 修改时间：2016/10/14 8:49
 * 修改备注：
 */
public class TaocanEntity {
    /**
     * id : 27
     * oldPrice : 1155
     * num : 2
     * newPrice : 578
     * name : 中老年男宾体检套餐
     * image : http://117.34.105.29:8209/mhealth/upload/1475220119774.jpg
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
        private int num;
        private int newPrice;
        private String name;
        private String image;

        public void setId(int id) {
            this.id = id;
        }

        public void setOldPrice(int oldPrice) {
            this.oldPrice = oldPrice;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setNewPrice(int newPrice) {
            this.newPrice = newPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public int getOldPrice() {
            return oldPrice;
        }

        public int getNum() {
            return num;
        }

        public int getNewPrice() {
            return newPrice;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }
    }
}
