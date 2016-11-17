package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/17 10:07
 * 修改人：Administrator
 * 修改时间：2016/11/17 10:07
 * 修改备注：
 */
public class FirstDep {

    /**
     * data : [{"firstDepName":"妇科","firstDepId":1},{"firstDepName":"产科","firstDepId":2},{"firstDepName":"儿科","firstDepId":3},{"firstDepName":"皮肤病科","firstDepId":5},{"firstDepName":"内科","firstDepId":6},{"firstDepName":"眼科","firstDepId":7},{"firstDepName":"骨科","firstDepId":8},{"firstDepName":"外科","firstDepId":9},{"firstDepName":"胸科","firstDepId":10},{"firstDepName":"口腔颌面外科科","firstDepId":11},{"firstDepName":"医学整形科","firstDepId":12},{"firstDepName":"肿瘤与控制部分","firstDepId":13},{"firstDepName":"神经科","firstDepId":14},{"firstDepName":"中国医学科","firstDepId":15},{"firstDepName":"营养科","firstDepId":16},{"firstDepName":"心脏内科","firstDepId":17},{"firstDepName":"遗传科","firstDepId":18},{"firstDepName":"男科","firstDepId":19}]
     * number : 18
     */

    private int number;
    /**
     * firstDepName : 妇科
     * firstDepId : 1
     */

    private List<DataEntity> data;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String firstDepName;
        private int firstDepId;

        public void setFirstDepName(String firstDepName) {
            this.firstDepName = firstDepName;
        }

        public void setFirstDepId(int firstDepId) {
            this.firstDepId = firstDepId;
        }

        public String getFirstDepName() {
            return firstDepName;
        }

        public int getFirstDepId() {
            return firstDepId;
        }
    }
}
