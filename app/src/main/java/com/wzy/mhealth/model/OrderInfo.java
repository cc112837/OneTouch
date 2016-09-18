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
     * shopName :
     * payMoney : 1.0
     * userName :
     * tradeTime :
     * status  :
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String shopName;
        private double payMoney;
        private String userName;
        private String tradeTime;
        private String status;
        private String id;
        private String createTime;
        private String shopOrder;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getShopOrder() {
            return shopOrder;
        }

        public void setShopOrder(String shopOrder) {
            this.shopOrder = shopOrder;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setTradeTime(String tradeTime) {
            this.tradeTime = tradeTime;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShopName() {
            return shopName;
        }

        public double getPayMoney() {
            return payMoney;
        }

        public String getUserName() {
            return userName;
        }

        public String getTradeTime() {
            return tradeTime;
        }

        public String getStatus() {
            return status;
        }
    }
}
