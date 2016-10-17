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
     * shop : false
     * stepNum : true
     * blood : false
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private boolean shop;
        private boolean stepNum;
        private boolean blood;

        public void setShop(boolean shop) {
            this.shop = shop;
        }

        public void setStepNum(boolean stepNum) {
            this.stepNum = stepNum;
        }

        public void setBlood(boolean blood) {
            this.blood = blood;
        }

        public boolean isShop() {
            return shop;
        }

        public boolean isStepNum() {
            return stepNum;
        }

        public boolean isBlood() {
            return blood;
        }
    }
}
