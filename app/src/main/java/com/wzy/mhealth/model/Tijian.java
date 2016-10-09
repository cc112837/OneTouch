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
     * id : 1
     * phone : 029-68018969
     * num : 0
     * adress : 西安市莲湖区丰登南路23号建大洋房(丰庆公园西门向北200米路东)
     * level : 5
     * details : <p><img src="http://117.34.105.29:8209/mhealth/ueditor/jsp/upload/image/20160929/1475145552248017940.jpg" title="1475145552248017940.jpg" alt="zhixingintro.jpg"/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 西安智行深度(精密)体检中心是一家为企业和个人提供普通健康体检、深度(精密)体检和检后健康改善与美式家庭医生服务的医疗机构。</p><p>&nbsp;&nbsp; &nbsp; &nbsp;西安智行深度(精密)体检中心是西安规模最大的高端体检与保健中心，营业面积6000平方米。</p><p>&nbsp;&nbsp; &nbsp; &nbsp;西安智行深度(精密)体检中心秉承早发现、早诊断、早治疗的先进预防医学理念，集权威医疗专家、国际一流检查设备、电脑排检系统、人性化服务流程于一体，对您的健康做以细胞级的深度、精密检测。</p><p>&nbsp;&nbsp; &nbsp; &nbsp;西安智行深度(精密)体检中心高度尊重与保护您的隐私、追求卓越的医疗质量和客户体验，致力于建立与您的终身关系，长期专注您的的健康状况，为您提供个性化健康改善计划和无碍就医通道服务，“保障健康，优化生命”以“治未病”为追求，&nbsp;全力协助您掌握更长远的人生，畅享生命的无限欢乐!&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;医院地址 &nbsp;西安市莲湖区丰登南路23号建大洋房(丰庆公园西门向北200米路东) &nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：68018969（8:00—11:30）<br/></p><p><br/></p>
     * name : 西安智行深度(精密)体检中心
     * img : http://117.34.105.29:8209/mhealth/upload/1475220119774.jpg
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
        private String phone;
        private int num;
        private String adress;
        private int level;
        private String details;
        private String name;
        private String img;

        public void setId(int id) {
            this.id = id;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public void setDetails(String details) {
            this.details = details;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public String getPhone() {
            return phone;
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

        public String getDetails() {
            return details;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }
    }
}
