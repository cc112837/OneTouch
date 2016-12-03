package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/18 17:01
 * 修改人：Administrator
 * 修改时间：2016/9/18 17:01
 * 修改备注：
 */
public class StepRank {

    /**
     * stepNum : 1426
     * rank : 2
     * likeNum : 0
     * stepNumId : 564
     * userName : 17092933912
     * image : http://ac-cirdf9pJ.clouddn.com/d71b8dKTdoKMxIKZQA2cALC
     */

    private int stepNum;
    private int rank;
    private int likeNum;
    private String stepNumId;
    private String userName;
    private String image;

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void setStepNumId(String stepNumId) {
        this.stepNumId = stepNumId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStepNum() {
        return stepNum;
    }

    public int getRank() {
        return rank;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public String getStepNumId() {
        return stepNumId;
    }

    public String getUserName() {
        return userName;
    }

    public String getImage() {
        return image;
    }
}
