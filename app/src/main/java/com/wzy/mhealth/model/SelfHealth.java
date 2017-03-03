package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：个人体检报告
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/23 10:14
 * 修改人：Administrator
 * 修改时间：2016/9/23 10:14
 * 修改备注：
 */
public class SelfHealth {


    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * medicalDate : 2017-02-08
         * medicalType : 体检报告
         * medicalInstitution : 第四军医大
         * medicalId : f5a2e99d5884b056015884b056520000
         */

        private String medicalDate;
        private String medicalType;
        private String medicalInstitution;
        private String medicalId;

        public String getMedicalDate() {
            return medicalDate;
        }

        public void setMedicalDate(String medicalDate) {
            this.medicalDate = medicalDate;
        }

        public String getMedicalType() {
            return medicalType;
        }

        public void setMedicalType(String medicalType) {
            this.medicalType = medicalType;
        }

        public String getMedicalInstitution() {
            return medicalInstitution;
        }

        public void setMedicalInstitution(String medicalInstitution) {
            this.medicalInstitution = medicalInstitution;
        }

        public String getMedicalId() {
            return medicalId;
        }

        public void setMedicalId(String medicalId) {
            this.medicalId = medicalId;
        }
    }
}
