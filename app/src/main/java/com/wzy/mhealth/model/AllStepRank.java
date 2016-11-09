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
     * stepNum : 1682
     * likeNum : 0
     * stepNumId : 569
     * image : http://q.qlogo.cn/qqapp/1105190833/D1CFDC414C332B5F21B2775A2AA3A301/100
     * userName : 131****0884
     * like : false
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
        private int likeNum;
        private int stepNumId;
        private String image;
        private String userName;
        private boolean like;

        public void setStepNum(int stepNum) {
            this.stepNum = stepNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public void setStepNumId(int stepNumId) {
            this.stepNumId = stepNumId;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public int getStepNum() {
            return stepNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public int getStepNumId() {
            return stepNumId;
        }

        public String getImage() {
            return image;
        }

        public String getUserName() {
            return userName;
        }

        public boolean isLike() {
            return like;
        }
    }
}
