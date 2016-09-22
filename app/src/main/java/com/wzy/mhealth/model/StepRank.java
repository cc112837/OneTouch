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
     * stepNum : 1550
     * rank : 2
     * userName : 18089292757
     */

    private int stepNum;
    private int rank;
    private String userName;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStepNum() {
        return stepNum;
    }

    public int getRank() {
        return rank;
    }

    public String getUserName() {
        return userName;
    }
}
