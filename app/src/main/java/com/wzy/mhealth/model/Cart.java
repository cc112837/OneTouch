package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/16 14:56
 * 修改人：Administrator
 * 修改时间：2016/11/16 14:56
 * 修改备注：
 */
public class Cart {


    /**
     * productNum : 3
     * data : [{"productNumber":1,"productNewPrice":258,"shopcartId":"4fdf362514bd4a18927d7acdf3c186d2","productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121104515iMnDUMs8.png","productName":"有机青稞米2.5kg礼盒装"},{"productNumber":1,"productNewPrice":10.8,"shopcartId":"84af0eb26d554938a945bbf2caedf4f8","productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121102139yPcnfkSW.png","productName":"有机青稞米100g"},{"productNumber":1,"productNewPrice":49.8,"shopcartId":"8caa1029d0e14a5eb404310354499028","productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121103048rLwq5yDr.png","productName":"有机青稞米500g"}]
     */

    private int productNum;
    /**
     * productNumber : 1
     * productNewPrice : 258
     * shopcartId : 4fdf362514bd4a18927d7acdf3c186d2
     * productImageSmall : http://117.34.105.29:8818/mhealth/upload/files/20161121104515iMnDUMs8.png
     * productName : 有机青稞米2.5kg礼盒装
     */

    private List<DataEntity> data;

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getProductNum() {
        return productNum;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int productNumber;
        private double productNewPrice;
        private String shopcartId;
        private String productImageSmall;
        private String productName;

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public void setProductNewPrice(double productNewPrice) {
            this.productNewPrice = productNewPrice;
        }

        public void setShopcartId(String shopcartId) {
            this.shopcartId = shopcartId;
        }

        public void setProductImageSmall(String productImageSmall) {
            this.productImageSmall = productImageSmall;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductNumber() {
            return productNumber;
        }

        public double getProductNewPrice() {
            return productNewPrice;
        }

        public String getShopcartId() {
            return shopcartId;
        }

        public String getProductImageSmall() {
            return productImageSmall;
        }

        public String getProductName() {
            return productName;
        }
    }
}
