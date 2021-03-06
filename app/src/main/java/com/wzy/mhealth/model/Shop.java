package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商城首页的商品信息类
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/15 15:28
 * 修改人：Administrator
 * 修改时间：2016/11/15 15:28
 * 修改备注：
 */
public class Shop {


    /**
     * showImage : http://117.34.105.29:8818/mhealth/upload/files/20161122135940xCwNnP66.png
     * productData : [{"productImageBig":"http://117.34.105.29:8818/mhealth/upload/files/20161121102139jprZEZCB.png","productNewPrice":10.8,"productOldPrice":12.8,"productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121102139yPcnfkSW.png","productName":"有机青稞米100g","productId":"f5a2e99d5884b056015884b056520000"},{"productImageBig":"http://117.34.105.29:8818/mhealth/upload/files/201611211030474Zujn8h6.png","productNewPrice":49.8,"productOldPrice":50.8,"productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121103048rLwq5yDr.png","productName":"有机青稞米500g","productId":"f5a2e99d5884b056015884b8ac890001"},{"productImageBig":"http://117.34.105.29:8818/mhealth/upload/files/20161121103827ghk0Tp9g.png","productNewPrice":228,"productOldPrice":268,"productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121103826mS7CmMQL.png","productName":"有机青稞米2.5kg家庭版","productId":"f5a2e99d5884b056015884bfb8150002"},{"productImageBig":"http://117.34.105.29:8818/mhealth/upload/files/20161121104516jH15N1YD.png","productNewPrice":258,"productOldPrice":298,"productImageSmall":"http://117.34.105.29:8818/mhealth/upload/files/20161121104515iMnDUMs8.png","productName":"有机青稞米2.5kg礼盒装","productId":"f5a2e99d5884b056015884c5f2c80003"}]
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String showImage;
        /**
         * productImageBig : http://117.34.105.29:8818/mhealth/upload/files/20161121102139jprZEZCB.png
         * productNewPrice : 10.8
         * productOldPrice : 12.8
         * productImageSmall : http://117.34.105.29:8818/mhealth/upload/files/20161121102139yPcnfkSW.png
         * productName : 有机青稞米100g
         * productId : f5a2e99d5884b056015884b056520000
         */

        private List<ProductDataEntity> productData;

        public void setShowImage(String showImage) {
            this.showImage = showImage;
        }

        public void setProductData(List<ProductDataEntity> productData) {
            this.productData = productData;
        }

        public String getShowImage() {
            return showImage;
        }

        public List<ProductDataEntity> getProductData() {
            return productData;
        }

        public static class ProductDataEntity {
            private String productImageBig;
            private double productNewPrice;
            private double productOldPrice;
            private String productImageSmall;
            private String productName;
            private String productId;

            public void setProductImageBig(String productImageBig) {
                this.productImageBig = productImageBig;
            }

            public void setProductNewPrice(double productNewPrice) {
                this.productNewPrice = productNewPrice;
            }

            public void setProductOldPrice(double productOldPrice) {
                this.productOldPrice = productOldPrice;
            }

            public void setProductImageSmall(String productImageSmall) {
                this.productImageSmall = productImageSmall;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductImageBig() {
                return productImageBig;
            }

            public double getProductNewPrice() {
                return productNewPrice;
            }

            public double getProductOldPrice() {
                return productOldPrice;
            }

            public String getProductImageSmall() {
                return productImageSmall;
            }

            public String getProductName() {
                return productName;
            }

            public String getProductId() {
                return productId;
            }
        }
    }
}
