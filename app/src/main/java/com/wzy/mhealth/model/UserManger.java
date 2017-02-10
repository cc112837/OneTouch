package com.wzy.mhealth.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/9 16:24
 * 修改人：Administrator
 * 修改时间：2017/2/9 16:24
 * 修改备注：
 */

public class UserManger {


    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        /**
         * name : 吴聪聪
         * userID : 610123199207092143
         * sex : 女
         * age : 25
         * birth : 1992-07-09
         * userManageId : f5a2e99d5884b056015884b056520000
         */

        private String name;
        private String userID;
        private String sex;
        private String age;
        private String birth;
        private String userManageId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getUserManageId() {
            return userManageId;
        }

        public void setUserManageId(String userManageId) {
            this.userManageId = userManageId;
        }
    }
}
