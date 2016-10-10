package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/9 16:35
 * 修改人：Administrator
 * 修改时间：2016/10/9 16:35
 * 修改备注：
 */
public class Proud {

    /**
     * stepNum : false
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private boolean stepNum;

        public void setStepNum(boolean stepNum) {
            this.stepNum = stepNum;
        }

        public boolean isStepNum() {
            return stepNum;
        }
    }
}
