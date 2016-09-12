package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/1 11:12
 * 修改人：Administrator
 * 修改时间：2016/9/1 11:12
 * 修改备注：
 */
public class StepResult {

    /**
     * userName : xiaowang
     * stepNum : 234
     * stepTime : 2016.9.7
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String userName;
        private int stepNum;
        private String stepTime;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setStepNum(int stepNum) {
            this.stepNum = stepNum;
        }

        public void setStepTime(String stepTime) {
            this.stepTime = stepTime;
        }

        public String getUserName() {
            return userName;
        }

        public int getStepNum() {
            return stepNum;
        }

        public String getStepTime() {
            return stepTime;
        }
    }
}
