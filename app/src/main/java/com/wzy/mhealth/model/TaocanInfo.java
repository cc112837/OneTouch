package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：用户体检所选套餐
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/1 14:59
 * 修改人：Administrator
 * 修改时间：2016/7/1 14:59
 * 修改备注：
 */
public class TaocanInfo {

    /**
     * rows : [{"ID":"31981d20-fe42-4316-a001-48149296c0ef","TJID":"1512210325","XM":"abc","SFZH":"610104198507046121","XB":"女","CSNY":"1985-07-04 00:00:00","LC":"三楼","LXDH":"13636817885","ZY":"普检林珍","DWDM":"004094","TCDM":"009820","CZLX":1,"CZSJ":"2016-07-01 09:00:49","CZID":"151221032520160802","CZCS":8,"CZRY":"abc","CZLB":"C","STATUS":1,"RDESC":"huangyu","EID":"234A0DA1A06E45CAA5ABF374178B64C3","DWMC":"喜来登大酒店2015（置换）","NAME":"喜来登大酒店2015（置换）妇科套餐","SEX":"女","ROW_ID":1}]
     * total : 1
     */

    private int total;
    /**
     * ID : 31981d20-fe42-4316-a001-48149296c0ef
     * TJID : 1512210325
     * XM : abc
     * SFZH : 610104198507046121
     * XB : 女
     * CSNY : 1985-07-04 00:00:00
     * LC : 三楼
     * LXDH : 13636817885
     * ZY : 普检林珍
     * DWDM : 004094
     * TCDM : 009820
     * CZLX : 1
     * CZSJ : 2016-07-01 09:00:49
     * CZID : 151221032520160802
     * CZCS : 8
     * CZRY : abc
     * CZLB : C
     * STATUS : 1
     * RDESC : huangyu
     * EID : 234A0DA1A06E45CAA5ABF374178B64C3
     * DWMC : 喜来登大酒店2015（置换）
     * NAME : 喜来登大酒店2015（置换）妇科套餐
     * SEX : 女
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
        private String TCDM;
        private int CZLX;
        private String CZSJ;
        private String CZID;
        private int CZCS;
        private String CZRY;
        private String CZLB;
        private int STATUS;
        private String RDESC;
        private String EID;
        private String DWMC;
        private String NAME;
        private String SEX;
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

        public void setTCDM(String TCDM) {
            this.TCDM = TCDM;
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

        public void setRDESC(String RDESC) {
            this.RDESC = RDESC;
        }

        public void setEID(String EID) {
            this.EID = EID;
        }

        public void setDWMC(String DWMC) {
            this.DWMC = DWMC;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
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

        public String getTCDM() {
            return TCDM;
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

        public String getRDESC() {
            return RDESC;
        }

        public String getEID() {
            return EID;
        }

        public String getDWMC() {
            return DWMC;
        }

        public String getNAME() {
            return NAME;
        }

        public String getSEX() {
            return SEX;
        }

        public int getROW_ID() {
            return ROW_ID;
        }
    }
}
