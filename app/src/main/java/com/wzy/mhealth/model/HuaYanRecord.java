package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/15 11:09
 * 修改人：Administrator
 * 修改时间：2016/7/15 11:09
 * 修改备注：
 */
public class HuaYanRecord {
    private String msg;
    private boolean success;
    private List<ObjEntity> obj;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setObj(List<ObjEntity> obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ObjEntity> getObj() {
        return obj;
    }

    public static class ObjEntity {
        private int ID;
        private String STUDYID;
        private String ITEMCODE;
        private String ITEMNAME;
        /**
         * ID : 5E4BC31D5EC84A3B8901404D7EAC9F70
         * ERID : 13986765
         * HISITEM : 070101
         * COMPONENTNAME : 白细胞
         * CHECKTIME : 15:10:15
         * HYRESULT : 6.18
         * UNIT : *10^9/L
         * YBID : 269
         * FLAG : 0
         * DEFVALUE : 4.00-10.00
         * DOCTOR : 王俊
         * SHDOCTOR : 王俊
         */

        private List<RESULTEntity> RESULT;

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setSTUDYID(String STUDYID) {
            this.STUDYID = STUDYID;
        }

        public void setITEMCODE(String ITEMCODE) {
            this.ITEMCODE = ITEMCODE;
        }

        public void setITEMNAME(String ITEMNAME) {
            this.ITEMNAME = ITEMNAME;
        }

        public void setRESULT(List<RESULTEntity> RESULT) {
            this.RESULT = RESULT;
        }

        public int getID() {
            return ID;
        }

        public String getSTUDYID() {
            return STUDYID;
        }

        public String getITEMCODE() {
            return ITEMCODE;
        }

        public String getITEMNAME() {
            return ITEMNAME;
        }

        public List<RESULTEntity> getRESULT() {
            return RESULT;
        }

        public static class RESULTEntity {
            private String ID;
            private int ERID;
            private String HISITEM;
            private String COMPONENTNAME;
            private String CHECKTIME;
            private String HYRESULT;
            private String UNIT;
            private int YBID;
            private int FLAG;
            private String DEFVALUE;
            private String DOCTOR;
            private String SHDOCTOR;

            public void setID(String ID) {
                this.ID = ID;
            }

            public void setERID(int ERID) {
                this.ERID = ERID;
            }

            public void setHISITEM(String HISITEM) {
                this.HISITEM = HISITEM;
            }

            public void setCOMPONENTNAME(String COMPONENTNAME) {
                this.COMPONENTNAME = COMPONENTNAME;
            }

            public void setCHECKTIME(String CHECKTIME) {
                this.CHECKTIME = CHECKTIME;
            }

            public void setHYRESULT(String HYRESULT) {
                this.HYRESULT = HYRESULT;
            }

            public void setUNIT(String UNIT) {
                this.UNIT = UNIT;
            }

            public void setYBID(int YBID) {
                this.YBID = YBID;
            }

            public void setFLAG(int FLAG) {
                this.FLAG = FLAG;
            }

            public void setDEFVALUE(String DEFVALUE) {
                this.DEFVALUE = DEFVALUE;
            }

            public void setDOCTOR(String DOCTOR) {
                this.DOCTOR = DOCTOR;
            }

            public void setSHDOCTOR(String SHDOCTOR) {
                this.SHDOCTOR = SHDOCTOR;
            }

            public String getID() {
                return ID;
            }

            public int getERID() {
                return ERID;
            }

            public String getHISITEM() {
                return HISITEM;
            }

            public String getCOMPONENTNAME() {
                return COMPONENTNAME;
            }

            public String getCHECKTIME() {
                return CHECKTIME;
            }

            public String getHYRESULT() {
                return HYRESULT;
            }

            public String getUNIT() {
                return UNIT;
            }

            public int getYBID() {
                return YBID;
            }

            public int getFLAG() {
                return FLAG;
            }

            public String getDEFVALUE() {
                return DEFVALUE;
            }

            public String getDOCTOR() {
                return DOCTOR;
            }

            public String getSHDOCTOR() {
                return SHDOCTOR;
            }
        }
    }
}
