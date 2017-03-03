package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：体检报告
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/14 15:11
 * 修改人：Administrator
 * 修改时间：2016/7/14 15:11
 * 修改备注：
 */
public class Record {

    /**
     * rows : [{"ID":"a531334428934352bce39fbba9877c1f","TJID":"1606300286","XM":"曹芳","SFZH":"511622199310252223","XB":"女","CSNY":"1993-10-25 00:00:00","LC":"三楼","LXDH":"18883344820","ZY":"普检张先中","DWDM":"004314","CZLX":5,"CZSJ":"2016-07-07 08:02:02","CZID":"1606300286","CZCS":1,"CZRY":"市场部\n","CZLB":"C","STATUS":5,"EID":"48f38415b65a40c89250cd1edf823354","DWMC":"安克电子西安分公司 2016","ROW_ID":1}]
     * total : 1
     */

    private int total;
    /**
     * ID : a531334428934352bce39fbba9877c1f
     * TJID : 1606300286
     * XM : 曹芳
     * SFZH : 511622199310252223
     * XB : 女
     * CSNY : 1993-10-25 00:00:00
     * LC : 三楼
     * LXDH : 18883344820
     * ZY : 普检张先中
     * DWDM : 004314
     * CZLX : 5
     * CZSJ : 2016-07-07 08:02:02
     * CZID : 1606300286
     * CZCS : 1
     * CZRY : 市场部

     * CZLB : C
     * STATUS : 5
     * EID : 48f38415b65a40c89250cd1edf823354
     * DWMC : 安克电子西安分公司 2016
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
        private String TJID;
        private String XM;
        private String SFZH;
        private String XB;
        private String CSNY;
        private String LC;
        private String LXDH;
        private String ZY;
        private String DWDM;
        private int CZLX;
        private String CZSJ;
        private String CZID;
        private int CZCS;
        private String CZRY;
        private String CZLB;
        private int STATUS;
        private String EID;
        private String DWMC;
        private int ROW_ID;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setTJID(String TJID) {
            this.TJID = TJID;
        }

        public void setXM(String XM) {
            this.XM = XM;
        }

        public void setSFZH(String SFZH) {
            this.SFZH = SFZH;
        }

        public void setXB(String XB) {
            this.XB = XB;
        }

        public void setCSNY(String CSNY) {
            this.CSNY = CSNY;
        }

        public void setLC(String LC) {
            this.LC = LC;
        }

        public void setLXDH(String LXDH) {
            this.LXDH = LXDH;
        }

        public void setZY(String ZY) {
            this.ZY = ZY;
        }

        public void setDWDM(String DWDM) {
            this.DWDM = DWDM;
        }

        public void setCZLX(int CZLX) {
            this.CZLX = CZLX;
        }

        public void setCZSJ(String CZSJ) {
            this.CZSJ = CZSJ;
        }

        public void setCZID(String CZID) {
            this.CZID = CZID;
        }

        public void setCZCS(int CZCS) {
            this.CZCS = CZCS;
        }

        public void setCZRY(String CZRY) {
            this.CZRY = CZRY;
        }

        public void setCZLB(String CZLB) {
            this.CZLB = CZLB;
        }

        public void setSTATUS(int STATUS) {
            this.STATUS = STATUS;
        }

        public void setEID(String EID) {
            this.EID = EID;
        }

        public void setDWMC(String DWMC) {
            this.DWMC = DWMC;
        }

        public void setROW_ID(int ROW_ID) {
            this.ROW_ID = ROW_ID;
        }

        public String getID() {
            return ID;
        }

        public String getTJID() {
            return TJID;
        }

        public String getXM() {
            return XM;
        }

        public String getSFZH() {
            return SFZH;
        }

        public String getXB() {
            return XB;
        }

        public String getCSNY() {
            return CSNY;
        }

        public String getLC() {
            return LC;
        }

        public String getLXDH() {
            return LXDH;
        }

        public String getZY() {
            return ZY;
        }

        public String getDWDM() {
            return DWDM;
        }

        public int getCZLX() {
            return CZLX;
        }

        public String getCZSJ() {
            return CZSJ;
        }

        public String getCZID() {
            return CZID;
        }

        public int getCZCS() {
            return CZCS;
        }

        public String getCZRY() {
            return CZRY;
        }

        public String getCZLB() {
            return CZLB;
        }

        public int getSTATUS() {
            return STATUS;
        }

        public String getEID() {
            return EID;
        }

        public String getDWMC() {
            return DWMC;
        }

        public int getROW_ID() {
            return ROW_ID;
        }
    }
}
