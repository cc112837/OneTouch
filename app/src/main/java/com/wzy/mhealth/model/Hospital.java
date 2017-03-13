package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/3/13 13:32
 * 修改人：Administrator
 * 修改时间：2017/3/13 13:32
 * 修改备注：
 */

public class Hospital {

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * hospitalTelephone : 0471-5281777
         * hospitalDegree : 三级医院
         * hospitalAddress : 呼和浩特市南二环玉泉区150号
         * hospitalImage : http://117.34.105.29:8818/mhealth/upload/null
         * hospitalName : 呼和浩特市第一医院
         * hospitalId : 2
         */

        private String hospitalTelephone;
        private String hospitalDegree;
        private String hospitalAddress;
        private String hospitalImage;
        private String hospitalName;
        private int hospitalId;

        public String getHospitalTelephone() {
            return hospitalTelephone;
        }

        public void setHospitalTelephone(String hospitalTelephone) {
            this.hospitalTelephone = hospitalTelephone;
        }

        public String getHospitalDegree() {
            return hospitalDegree;
        }

        public void setHospitalDegree(String hospitalDegree) {
            this.hospitalDegree = hospitalDegree;
        }

        public String getHospitalAddress() {
            return hospitalAddress;
        }

        public void setHospitalAddress(String hospitalAddress) {
            this.hospitalAddress = hospitalAddress;
        }

        public String getHospitalImage() {
            return hospitalImage;
        }

        public void setHospitalImage(String hospitalImage) {
            this.hospitalImage = hospitalImage;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }
    }
}
