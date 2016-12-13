package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/18 13:03
 * 修改人：Administrator
 * 修改时间：2016/9/18 13:03
 * 修改备注：
 */
public class OrderInfo {


    /**
     * createTime : 2016-12-09 11:03:01
     * shopType : 2
     * payMoney : 0.01
     * status : 2
     * tradeTime : 2016-12-09 11:03:04
     * shopName : 吴聪聪
     * shopId : 19
     * userName : fff
     * image : http://117.34.105.29:8818/mhealth/upload/2.jpg
     * shopOrder : 120911025321459
     * orderId : 31
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String createTime;
        private int shopType;
        private double payMoney;
        private int status;
        private String tradeTime;
        private String shopName;
        private int shopId;
        private String userName;
        private String image;
        private String shopOrder;
        private int orderId;

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setShopType(int shopType) {
            this.shopType = shopType;
        }

        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTradeTime(String tradeTime) {
            this.tradeTime = tradeTime;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setShopOrder(String shopOrder) {
            this.shopOrder = shopOrder;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public int getShopType() {
            return shopType;
        }

        public double getPayMoney() {
            return payMoney;
        }

        public int getStatus() {
            return status;
        }

        public String getTradeTime() {
            return tradeTime;
        }

        public String getShopName() {
            return shopName;
        }

        public int getShopId() {
            return shopId;
        }

        public String getUserName() {
            return userName;
        }

        public String getImage() {
            return image;
        }

        public String getShopOrder() {
            return shopOrder;
        }

        public int getOrderId() {
            return orderId;
        }
    }
}
