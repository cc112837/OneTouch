package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/28 15:43
 * 修改人：Administrator
 * 修改时间：2016/9/28 15:43
 * 修改备注：
 */
public class Tijian {


    /**
     * taocanId : [{"oldPrice":0.01,"taocanNum":0,"taoId":6,"newPrice":0.01,"name":"ceishi","context":"具体一个套餐详情"},{"oldPrice":228,"taocanNum":0,"taoId":19,"newPrice":79,"name":"入职套餐","context":"具体一个套餐详情"}]
     * phone : 029-68018969
     * adress : 西安市莲湖区丰登南路23号建大洋房(丰庆公园西门向北200米路东)
     * level : 5
     * details :  详情
     * centerName : 西安智行深度(精密)体检中心
     * img : http://117.34.105.29:8209/mhealth/upload/1475220119774.jpg
     * centerId : 1
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String phone;
        private String adress;
        private int level;
        private String details;
        private String centerName;
        private String img;
        private int centerId;
        /**
         * oldPrice : 0.01
         * taocanNum : 0
         * taoId : 6
         * newPrice : 0.01
         * name : ceishi
         * context : 具体一个套餐详情
         */

        private List<TaocanIdEntity> taocanId;

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setCenterName(String centerName) {
            this.centerName = centerName;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setCenterId(int centerId) {
            this.centerId = centerId;
        }

        public void setTaocanId(List<TaocanIdEntity> taocanId) {
            this.taocanId = taocanId;
        }

        public String getPhone() {
            return phone;
        }

        public String getAdress() {
            return adress;
        }

        public int getLevel() {
            return level;
        }

        public String getDetails() {
            return details;
        }

        public String getCenterName() {
            return centerName;
        }

        public String getImg() {
            return img;
        }

        public int getCenterId() {
            return centerId;
        }

        public List<TaocanIdEntity> getTaocanId() {
            return taocanId;
        }

        public static class TaocanIdEntity implements Serializable{
            private double oldPrice;
            private int taocanNum;
            private int taoId;
            private double newPrice;
            private String name;
            private String context;

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
        }
    }
}
