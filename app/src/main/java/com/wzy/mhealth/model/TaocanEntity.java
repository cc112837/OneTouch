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
     * oldPrice : 0.01
     * taocanNum : 2
     * taoId : 6
     * newPrice : 0.01
     * name : ceishi
     * context : 1473661343789.png
     * image : http://117.34.105.29:8209/mhealth/upload/1475220119774.png
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private double oldPrice;
        private int taocanNum;
        private int taoId;
        private double newPrice;
        private String name;
        private String context;
        private String image;
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setOldPrice(double oldPrice) {
            this.oldPrice = oldPrice;
        }

        public void setTaocanNum(int taocanNum) {
            this.taocanNum = taocanNum;
        }

        public void setTaoId(int taoId) {
            this.taoId = taoId;
        }

        public void setNewPrice(double newPrice) {
            this.newPrice = newPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getOldPrice() {
            return oldPrice;
        }

        public int getTaocanNum() {
            return taocanNum;
        }

        public int getTaoId() {
            return taoId;
        }

        public double getNewPrice() {
            return newPrice;
        }

        public String getName() {
            return name;
        }

        public String getContext() {
            return context;
        }

        public String getImage() {
            return image;
        }
    }
}
