package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/1 14:55
 * 修改人：Administrator
 * 修改时间：2016/7/1 14:55
 * 修改备注：
 */
public class ItemInfo {

    /**
     * rows : [{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"ZC","XMMC":"早餐","SEX":"男女","KSMC":"餐厅","JCKS":"ET","ROW_ID":1},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0612","XMMC":"妇检+白带常规+宫颈刮片","SEX":"女","KSMC":"妇科","JCKS":"FK","ROW_ID":2},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0609","XMMC":"宫颈刮片2(化验)","SEX":"女","KSMC":"化验室","JCKS":"HY","ROW_ID":3},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0608","XMMC":"宫颈刮片1(化验)","SEX":"女","KSMC":"化验室","JCKS":"HY","ROW_ID":4},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0607","XMMC":"白带常规(化验)","SEX":"女","KSMC":"化验室","JCKS":"HY","ROW_ID":5},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"JL","XMMC":"结论","SEX":"男女","KSMC":"综合室","JCKS":"JL","ROW_ID":6},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0203","XMMC":"盆腔彩超(女)","SEX":"女","KSMC":"彩超二","JCKS":"PQ","ROW_ID":7},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0501","XMMC":"红外乳腺","SEX":"女","KSMC":"红外乳腺","JCKS":"RX","ROW_ID":8},{"ID":"234A0DA1A06E45CAA5ABF374178B64C3","STUDYID":"1512210325","ITEMCODE":"0201","XMMC":"腹部彩超","SEX":"男女","KSMC":"彩超一","JCKS":"US","ROW_ID":9}]
     * total : 9
     */

    private int total;
    /**
     * ID : 234A0DA1A06E45CAA5ABF374178B64C3
     * STUDYID : 1512210325
     * ITEMCODE : ZC
     * XMMC : 早餐
     * SEX : 男女
     * KSMC : 餐厅
     * JCKS : ET
     * ROW_ID : 1
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private String ID;
        private String STUDYID;
        private String ITEMCODE;
        private String XMMC;
        private String SEX;
        private String KSMC;
        private String JCKS;
        private int ROW_ID;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setSTUDYID(String STUDYID) {
            this.STUDYID = STUDYID;
        }

        public void setITEMCODE(String ITEMCODE) {
            this.ITEMCODE = ITEMCODE;
        }

        public void setXMMC(String XMMC) {
            this.XMMC = XMMC;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public void setKSMC(String KSMC) {
            this.KSMC = KSMC;
        }

        public void setJCKS(String JCKS) {
            this.JCKS = JCKS;
        }

        public void setROW_ID(int ROW_ID) {
            this.ROW_ID = ROW_ID;
        }

        public String getID() {
            return ID;
        }

        public String getSTUDYID() {
            return STUDYID;
        }

        public String getITEMCODE() {
            return ITEMCODE;
        }

        public String getXMMC() {
            return XMMC;
        }

        public String getSEX() {
            return SEX;
        }

        public String getKSMC() {
            return KSMC;
        }

        public String getJCKS() {
            return JCKS;
        }

        public int getROW_ID() {
            return ROW_ID;
        }
    }
}
