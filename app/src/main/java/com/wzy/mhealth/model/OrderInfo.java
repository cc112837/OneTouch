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
     * createTime : 2016-09-18 13:41:27
     * shopNumber : null
     * payMoney : 0.01
     * status : 1
     * tradeTime : 2016-09-18 13:41:23
     * shopName : ceishi
     * shopId : 6
     * OrderId : 4
     * userName : 18369956786
     * image : 1475220119774.png
     * shopOrder : 0918134113-1250
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
        private double payMoney;
        private int status;
        private String tradeTime;
        private String shopName;
        private int shopId;
        private int orderId;
        private String userName;
        private String image;
        private String shopOrder;

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public void setOrderId(int orderId) {
            this.orderId = orderId;
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

        public String getCreateTime() {
            return createTime;
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

        public int getOrderId() {
            return orderId;
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
    }
}
