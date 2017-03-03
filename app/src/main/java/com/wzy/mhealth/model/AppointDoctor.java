package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：预约医生选择时间
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/10 16:08
 * 修改人：Administrator
 * 修改时间：2017/2/10 16:08
 * 修改备注：
 */

public class AppointDoctor {

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * appointTime : 2017-02-08 星期三
         * appointFore : 15
         * appointAfter : 10
         */

        private String appointTime;
        private String appointFore;
        private String appointAfter;

        public String getAppointTime() {
            return appointTime;
        }

        public void setAppointTime(String appointTime) {
            this.appointTime = appointTime;
        }

        public String getAppointFore() {
            return appointFore;
        }

        public void setAppointFore(String appointFore) {
            this.appointFore = appointFore;
        }

        public String getAppointAfter() {
            return appointAfter;
        }

        public void setAppointAfter(String appointAfter) {
            this.appointAfter = appointAfter;
        }
    }
}
