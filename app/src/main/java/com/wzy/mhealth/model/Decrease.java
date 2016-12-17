package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/19 9:29
 * 修改人：Administrator
 * 修改时间：2016/11/19 9:29
 * 修改备注：
 */
public class Decrease {

    /**
     * data : [{"couponTime":"2016-11-28~~2016-12-04","couponType":"1","isUserd":"0","productCouponId":"f5a2e99d58adf6ab0158adfc9b460003","couponPrice":5,"isValiadate":"1","muchPrice":99,"userName":"fff"}]
     * couponNum : 1
     */

    private int couponNum;
    /**
     * couponTime : 2016-11-28~~2016-12-04
     * couponType : 1
     * isUserd : 0
     * productCouponId : f5a2e99d58adf6ab0158adfc9b460003
     * couponPrice : 5
     * isValiadate : 1
     * muchPrice : 99
     * userName : fff
     */

    private List<DataEntity> data;

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String couponTime;
        private String couponType;
        private String isUserd;
        private String productCouponId;
        private int couponPrice;
        private String isValiadate;
        private int muchPrice;
        private String userName;

        public void setCouponTime(String couponTime) {
            this.couponTime = couponTime;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public void setIsUserd(String isUserd) {
            this.isUserd = isUserd;
        }

        public void setProductCouponId(String productCouponId) {
            this.productCouponId = productCouponId;
        }

        public void setCouponPrice(int couponPrice) {
            this.couponPrice = couponPrice;
        }

        public void setIsValiadate(String isValiadate) {
            this.isValiadate = isValiadate;
        }

        public void setMuchPrice(int muchPrice) {
            this.muchPrice = muchPrice;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCouponTime() {
            return couponTime;
        }

        public String getCouponType() {
            return couponType;
        }

        public String getIsUserd() {
            return isUserd;
        }

        public String getProductCouponId() {
            return productCouponId;
        }

        public int getCouponPrice() {
            return couponPrice;
        }

        public String getIsValiadate() {
            return isValiadate;
        }

        public int getMuchPrice() {
            return muchPrice;
        }

        public String getUserName() {
            return userName;
        }
    }
}
