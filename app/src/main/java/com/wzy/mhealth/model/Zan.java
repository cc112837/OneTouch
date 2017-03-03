package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：用户点赞实现
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/9 11:09
 * 修改人：Administrator
 * 修改时间：2016/11/9 11:09
 * 修改备注：
 */
public class Zan {

    /**
     * num : 1
     * data : [{"time":"2016-11-08 13:41:50.0","image":"http://ac-cirdf9pJ.clouddn.com/d71b8dKTdoKMxIKZQA2cALC","userName":"17092933912"}]
     */

    private int num;
    /**
     * time : 2016-11-08 13:41:50.0
     * image : http://ac-cirdf9pJ.clouddn.com/d71b8dKTdoKMxIKZQA2cALC
     * userName : 17092933912
     */

    private List<DataEntity> data;

    public void setNum(int num) {
        this.num = num;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getNum() {
        return num;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String time;
        private String image;
        private String userName;

        public void setTime(String time) {
            this.time = time;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTime() {
            return time;
        }

        public String getImage() {
            return image;
        }

        public String getUserName() {
            return userName;
        }
    }
}
