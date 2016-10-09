package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/28 15:43
 * 修改人：Administrator
 * 修改时间：2016/9/28 15:43
 * 修改备注：
 */
public class Tijian {

    /**
     * id : 6101001
     * num : 0
     * adress : 西安市莲湖区丰登南路23号建大洋房(丰庆公园西门向北200米路东)
     * level : 5
     * name : 智行体检中心
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int num;
        private String adress;
        private int level;
        private String name;
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getNum() {
            return num;
        }

        public String getAdress() {
            return adress;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }
    }
}
