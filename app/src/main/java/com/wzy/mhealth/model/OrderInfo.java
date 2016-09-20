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
     * createTime : 2016-09-19 15:14:50.0
     * shopNumber : 2016091921001004430266499957
     * payMoney : 0.01
     * status : 0
     * tradeTime : 2016-09-19 15:13:57.0
     * shopName : ceishi
     * shopId : 6
     * OrderId : 10
     * userName : 18369956786
     * shopOrder : 091915143358521
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
        private String shopNumber;
        private double payMoney;
        private int status;
        private String tradeTime;
        private String shopName;
        private int shopId;
        private int OrderId;
        private String userName;
        private String shopOrder;

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
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

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setShopOrder(String shopOrder) {
            this.shopOrder = shopOrder;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getShopNumber() {
            return shopNumber;
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
            return OrderId;
        }

        public String getUserName() {
            return userName;
        }

        public String getShopOrder() {
            return shopOrder;
        }
    }

}
