package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/25 10:20
 * 修改人：Administrator
 * 修改时间：2017/2/25 10:20
 * 修改备注：
 */

public class OrderAppiont {

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * doctorImage : http://117.34.105.29:8818/mhealth/upload/2.jpg
         * firstdep : 妇科
         * clinicTime : 02-27上午
         * clinicName : 吕升海
         * doctorName : 吴聪聪
         * clinicId : f5a2e99d5a4aff77015a59a6e2e40004
         * hospital : 西安交通大学第一医院
         * adrress : 18369956786
         * doctorTitle : 主治医生
         */

        private String doctorImage;
        private String firstdep;
        private String clinicTime;
        private String clinicName;
        private String doctorName;
        private String clinicId;
        private String hospital;
        private String adrress;
        private String doctorTitle;

        public String getDoctorImage() {
            return doctorImage;
        }

        public void setDoctorImage(String doctorImage) {
            this.doctorImage = doctorImage;
        }

        public String getFirstdep() {
            return firstdep;
        }

        public void setFirstdep(String firstdep) {
            this.firstdep = firstdep;
        }

        public String getClinicTime() {
            return clinicTime;
        }

        public void setClinicTime(String clinicTime) {
            this.clinicTime = clinicTime;
        }

        public String getClinicName() {
            return clinicName;
        }

        public void setClinicName(String clinicName) {
            this.clinicName = clinicName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getClinicId() {
            return clinicId;
        }

        public void setClinicId(String clinicId) {
            this.clinicId = clinicId;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getAdrress() {
            return adrress;
        }

        public void setAdrress(String adrress) {
            this.adrress = adrress;
        }

        public String getDoctorTitle() {
            return doctorTitle;
        }

        public void setDoctorTitle(String doctorTitle) {
            this.doctorTitle = doctorTitle;
        }
    }
}
