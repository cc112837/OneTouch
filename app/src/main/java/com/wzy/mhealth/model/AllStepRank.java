package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/19 9:00
 * 修改人：Administrator
 * 修改时间：2016/9/19 9:00
 * 修改备注：
 */
public class AllStepRank {

    /**
     * stepNum : 1666
     * userName : 17092933912
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int stepNum;
        private String userName;

        public void setStepNum(int stepNum) {
            this.stepNum = stepNum;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getStepNum() {
            return stepNum;
        }

        public String getUserName() {
            return userName;
        }
    }
}
