package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：非化验报告
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/15 11:09
 * 修改人：Administrator
 * 修改时间：2016/7/15 11:09
 * 修改备注：
 */
public class NoHuaRecord {

    /**
     * msg :
     * obj : [{"STUDYID":"1606300286","KSDM":"YB","KSMC":"一般项目","KSNO":"003","ITEMS":[{"ITEMCODE":"0101","ITEMNAME":"一般项目九项"}],"RESULT":[{"ID":"fbd91064-1678-44bf-8db6-683901fcb135","ERID":1654364,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010101","COMPONENTNAME":"身高","CVALUE":"154.9"},{"ID":"aecffd9a-a088-484f-8f50-450cd343bf4c","ERID":1654365,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010102","COMPONENTNAME":"体重","CVALUE":"54.0"},{"ID":"508c9263-53a8-4d2e-bc5f-c83023073ac3","ERID":1654366,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010103","COMPONENTNAME":"体重指数","CVALUE":"22.51"},{"ID":"fd1ce299-d413-4fec-8be3-3331cc0ecb5d","ERID":1654367,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010104","COMPONENTNAME":"收缩压","CVALUE":"122"},{"ID":"5E4BC31D5EC84A3B8901404D7EAC9F70","ERID":1654368,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010105","COMPONENTNAME":"舒张压","CVALUE":"70"},{"ID":"1BBEC42F0D684BBA83FB9C2721A8A539","ERID":1654369,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010107","COMPONENTNAME":"腰围","CVALUE":"66"},{"ID":"10F6309C4C67455B85055A89A438C6A2","ERID":1654370,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010108","COMPONENTNAME":"臀围","CVALUE":"90"},{"ID":"5C885B2C7B22482D996F51B935E3D775","ERID":1654371,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010109","COMPONENTNAME":"腰臀比","CVALUE":"0.73"},{"ID":"24181636026443BC94B5D31E3BE5264C","ERID":1654372,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010115","COMPONENTNAME":"血氧","CVALUE":"98"},{"ID":"F2EA315792E64CC9824E732CB78E1882","ERID":1654373,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010106","COMPONENTNAME":"脉搏","CVALUE":"80"},{"ID":"FC6434177D9349DF8A2C258CE29DBADF","ERID":1654374,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010118","COMPONENTNAME":"既往病史","CVALUE":"无"},{"ID":"7319a3e3-4c1e-4536-9f5f-2b071f880f18","ERID":1654375,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"000001","COMPONENTNAME":"小结","CVALUE":"未见异常"}]},{"STUDYID":"1606300286","KSDM":"EL","KSMC":"心电室","KSNO":"013","ITEMS":[{"ITEMCODE":"0301","ITEMNAME":"12导联心电图"}],"RESULT":[{"ID":"b1d29458-6ae9-4467-bdb9-cc1926887c8c","ERID":208460,"STUDYDATE":"2016-06-30 10:21:54","HISITEM":"000003","COMPONENTNAME":"检查结论","CVALUE":"正常心电图"}]},{"STUDYID":"1606300286","KSDM":"US","KSMC":"彩超一","KSNO":"014","ITEMS":[{"ITEMCODE":"0201","ITEMNAME":"腹部彩超"}],"RESULT":[{"ID":"48f38415-b65a-40c8-9250-cd1edf823354","ERID":1032767,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"020101","COMPONENTNAME":"肝脏","CVALUE":"形态规则，肝边缘光滑，实质回声均匀，肝内血管走行清晰。"},{"ID":"76bc1311-34cc-47c3-b7e6-30bebfafae5c","ERID":1032768,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"020102","COMPONENTNAME":"胆囊","CVALUE":"大小正常，囊壁光滑，囊内未见异常回声。"},{"ID":"e454fb65-803b-4cc6-b070-449d5f1a212d","ERID":1032769,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"020103","COMPONENTNAME":"胰脏","CVALUE":"胰腺大小、形态正常，内部回声均匀，主胰管无扩张。"},{"ID":"7d905017-c423-4c3f-b61f-4fcbd868e74a","ERID":1032770,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"020104","COMPONENTNAME":"脾脏","CVALUE":"脾脏大小、形态正常，包膜完整光滑，回声均匀。"},{"ID":"bc04cd89-c07d-4337-a086-650dd3815464","ERID":1032771,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"020105","COMPONENTNAME":"双肾","CVALUE":"双肾形态、大小正常，包膜完整光滑，肾实质回声均匀，肾盂肾盏未见扩张。"},{"ID":"a5313344-2893-4352-bce3-9fbba9877c1f","ERID":1032772,"STUDYDATE":"2016-06-30 10:42:30","HISITEM":"000003","COMPONENTNAME":"检查结论","CVALUE":"未见异常"}]},{"STUDYID":"1606300286","KSDM":"CR","KSMC":"放射检查","KSNO":"022","ITEMS":[{"ITEMCODE":"0401","ITEMNAME":"DR胸部检查"},{"ITEMCODE":"0402","ITEMNAME":"DR颈部侧位检查"}],"RESULT":[{"ID":"0b2952b0d93576dd24b49dcb66a9c7d8","ERID":202960,"STUDYDATE":"2016-06-30 10:40:06","HISITEM":"000004","COMPONENTNAME":"检查描述","CVALUE":"颈椎：颈椎生理曲度变直， 椎列连续； 骨质结构完整。 胸部：胸廓对称；纵隔居中，两肺纹理清晰；两肺门无增大；心影大小、形态如常；两膈面光整、肋膈角清晰锐利。"},{"ID":"5f72bb9f-6948-4e21-b384-9122fdc66406","ERID":202961,"STUDYDATE":"2016-06-30 10:40:06","HISITEM":"000003","COMPONENTNAME":"检查结论","CVALUE":"1.颈椎骨质未见明显异常，生理曲度变直。 2.两肺及心膈未见明显异常。"}]},{"STUDYID":"1606300286","KSDM":"HP","KSMC":"C-14","KSNO":"031","ITEMS":[{"ITEMCODE":"1302","ITEMNAME":"C-14呼气试验"}],"RESULT":[{"ID":"66C258DB4E6F4C55B34262821B7C47E5","ERID":18605,"STUDYDATE":"2016-06-30 10:49:04","HISITEM":"000003","COMPONENTNAME":"检查结论","CVALUE":"检测结果：DPM= 0 阴性 -"}]},{"STUDYID":"1606300286","KSDM":"ET","KSMC":"餐厅","KSNO":"038","ITEMS":[{"ITEMCODE":"ZC","ITEMNAME":"早餐"}],"RESULT":[]}]
     * success : true
     */

    private String msg;
    private boolean success;
    /**
     * STUDYID : 1606300286
     * KSDM : YB
     * KSMC : 一般项目
     * KSNO : 003
     * ITEMS : [{"ITEMCODE":"0101","ITEMNAME":"一般项目九项"}]
     * RESULT : [{"ID":"fbd91064-1678-44bf-8db6-683901fcb135","ERID":1654364,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010101","COMPONENTNAME":"身高","CVALUE":"154.9"},{"ID":"aecffd9a-a088-484f-8f50-450cd343bf4c","ERID":1654365,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010102","COMPONENTNAME":"体重","CVALUE":"54.0"},{"ID":"508c9263-53a8-4d2e-bc5f-c83023073ac3","ERID":1654366,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010103","COMPONENTNAME":"体重指数","CVALUE":"22.51"},{"ID":"fd1ce299-d413-4fec-8be3-3331cc0ecb5d","ERID":1654367,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010104","COMPONENTNAME":"收缩压","CVALUE":"122"},{"ID":"5E4BC31D5EC84A3B8901404D7EAC9F70","ERID":1654368,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010105","COMPONENTNAME":"舒张压","CVALUE":"70"},{"ID":"1BBEC42F0D684BBA83FB9C2721A8A539","ERID":1654369,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010107","COMPONENTNAME":"腰围","CVALUE":"66"},{"ID":"10F6309C4C67455B85055A89A438C6A2","ERID":1654370,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010108","COMPONENTNAME":"臀围","CVALUE":"90"},{"ID":"5C885B2C7B22482D996F51B935E3D775","ERID":1654371,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010109","COMPONENTNAME":"腰臀比","CVALUE":"0.73"},{"ID":"24181636026443BC94B5D31E3BE5264C","ERID":1654372,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010115","COMPONENTNAME":"血氧","CVALUE":"98"},{"ID":"F2EA315792E64CC9824E732CB78E1882","ERID":1654373,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010106","COMPONENTNAME":"脉搏","CVALUE":"80"},{"ID":"FC6434177D9349DF8A2C258CE29DBADF","ERID":1654374,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"010118","COMPONENTNAME":"既往病史","CVALUE":"无"},{"ID":"7319a3e3-4c1e-4536-9f5f-2b071f880f18","ERID":1654375,"STUDYDATE":"2016-06-30 10:18:22","HISITEM":"000001","COMPONENTNAME":"小结","CVALUE":"未见异常"}]
     */

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
        private String STUDYID;
        private String KSDM;
        private String KSMC;
        private String KSNO;
        /**
         * ITEMCODE : 0101
         * ITEMNAME : 一般项目九项
         */

        private List<ITEMSEntity> ITEMS;
        /**
         * ID : fbd91064-1678-44bf-8db6-683901fcb135
         * ERID : 1654364
         * STUDYDATE : 2016-06-30 10:18:22
         * HISITEM : 010101
         * COMPONENTNAME : 身高
         * CVALUE : 154.9
         */

        private List<RESULTEntity> RESULT;

        public void setSTUDYID(String STUDYID) {
            this.STUDYID = STUDYID;
        }

        public void setKSDM(String KSDM) {
            this.KSDM = KSDM;
        }

        public void setKSMC(String KSMC) {
            this.KSMC = KSMC;
        }

        public void setKSNO(String KSNO) {
            this.KSNO = KSNO;
        }

        public void setITEMS(List<ITEMSEntity> ITEMS) {
            this.ITEMS = ITEMS;
        }

        public void setRESULT(List<RESULTEntity> RESULT) {
            this.RESULT = RESULT;
        }

        public String getSTUDYID() {
            return STUDYID;
        }

        public String getKSDM() {
            return KSDM;
        }

        public String getKSMC() {
            return KSMC;
        }

        public String getKSNO() {
            return KSNO;
        }

        public List<ITEMSEntity> getITEMS() {
            return ITEMS;
        }

        public List<RESULTEntity> getRESULT() {
            return RESULT;
        }

        public static class ITEMSEntity {
            private String ITEMCODE;
            private String ITEMNAME;

            public void setITEMCODE(String ITEMCODE) {
                this.ITEMCODE = ITEMCODE;
            }

            public void setITEMNAME(String ITEMNAME) {
                this.ITEMNAME = ITEMNAME;
            }

            public String getITEMCODE() {
                return ITEMCODE;
            }

            public String getITEMNAME() {
                return ITEMNAME;
            }
        }

        public static class RESULTEntity {
            private String ID;
            private int ERID;
            private String STUDYDATE;
            private String HISITEM;
            private String COMPONENTNAME;
            private String CVALUE;

            public void setID(String ID) {
                this.ID = ID;
            }

            public void setERID(int ERID) {
                this.ERID = ERID;
            }

            public void setSTUDYDATE(String STUDYDATE) {
                this.STUDYDATE = STUDYDATE;
            }

            public void setHISITEM(String HISITEM) {
                this.HISITEM = HISITEM;
            }

            public void setCOMPONENTNAME(String COMPONENTNAME) {
                this.COMPONENTNAME = COMPONENTNAME;
            }

            public void setCVALUE(String CVALUE) {
                this.CVALUE = CVALUE;
            }

            public String getID() {
                return ID;
            }

            public int getERID() {
                return ERID;
            }

            public String getSTUDYDATE() {
                return STUDYDATE;
            }

            public String getHISITEM() {
                return HISITEM;
            }

            public String getCOMPONENTNAME() {
                return COMPONENTNAME;
            }

            public String getCVALUE() {
                return CVALUE;
            }
        }
    }

}
