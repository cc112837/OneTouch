package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/28 17:14
 * 修改人：Administrator
 * 修改时间：2016/9/28 17:14
 * 修改备注：
 */
public class Provice {


    /**
     * provice : 北京市
     * cityArr : [{"cityId":110100,"city":"市辖区"},{"cityId":110200,"city":"县"}]
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String provice;
        /**
         * cityId : 110100
         * city : 市辖区
         */

        private List<CityArrEntity> cityArr;

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public void setCityArr(List<CityArrEntity> cityArr) {
            this.cityArr = cityArr;
        }

        public String getProvice() {
            return provice;
        }

        public List<CityArrEntity> getCityArr() {
            return cityArr;
        }

        public static class CityArrEntity {
            private int cityId;
            private String city;

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCityId() {
                return cityId;
            }

            public String getCity() {
                return city;
            }
        }
    }
}
