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
public class NoRecord {

    /**
     * rows : [{"ID":"0b2952b0d93576dd24b49dcb66a9c7d8","ERID":202960,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:40:06","KSDM":"CR","HISITEM":"000004","MVALUE":"颈椎：颈椎生理曲度变直， 椎列连续； 骨质结构完整。 胸部：胸廓对称；纵隔居中，两肺纹理清晰；两肺门无增大；心影大小、形态如常；两膈面光整、肋膈角清晰锐利。","DOCTOR":"杨彦龙","CNAME":"检查描述","KSMC":"放射检查","ORDERCODE":"400","ROW_ID":1},{"ID":"5f72bb9f-6948-4e21-b384-9122fdc66406","ERID":202961,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:40:06","KSDM":"CR","HISITEM":"000003","MVALUE":"1.颈椎骨质未见明显异常，生理曲度变直。 2.两肺及心膈未见明显异常。","DOCTOR":"杨彦龙","CNAME":"检查结论","KSMC":"放射检查","ORDERCODE":"401","ROW_ID":2},{"ID":"b1d29458-6ae9-4467-bdb9-cc1926887c8c","ERID":208460,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:21:54","KSDM":"EL","HISITEM":"000003","MVALUE":"正常心电图","DOCTOR":"张凤琴","CNAME":"检查结论","KSMC":"心电室","ORDERCODE":"401","ROW_ID":3},{"ID":"66C258DB4E6F4C55B34262821B7C47E5","ERID":18605,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:49:04","KSDM":"HP","HISITEM":"000003","MVALUE":"检测结果：DPM= 0 阴性 -","DOCTOR":"王丹","CNAME":"检查结论","KSMC":"C-14","ORDERCODE":"401","ROW_ID":4},{"ID":"48f38415-b65a-40c8-9250-cd1edf823354","ERID":1032767,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"020101","CVALUE":"形态规则，肝边缘光滑，实质回声均匀，肝内血管走行清晰。","DOCTOR":"崔联珍","CNAME":"肝脏","KSMC":"彩超一","ORDERCODE":"01","ROW_ID":5},{"ID":"76bc1311-34cc-47c3-b7e6-30bebfafae5c","ERID":1032768,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"020102","CVALUE":"大小正常，囊壁光滑，囊内未见异常回声。","DOCTOR":"崔联珍","CNAME":"胆囊","KSMC":"彩超一","ORDERCODE":"02","ROW_ID":6},{"ID":"e454fb65-803b-4cc6-b070-449d5f1a212d","ERID":1032769,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"020103","CVALUE":"胰腺大小、形态正常，内部回声均匀，主胰管无扩张。","DOCTOR":"崔联珍","CNAME":"胰脏","KSMC":"彩超一","ORDERCODE":"03","ROW_ID":7},{"ID":"7d905017-c423-4c3f-b61f-4fcbd868e74a","ERID":1032770,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"020104","CVALUE":"脾脏大小、形态正常，包膜完整光滑，回声均匀。","DOCTOR":"崔联珍","CNAME":"脾脏","KSMC":"彩超一","ORDERCODE":"04","ROW_ID":8},{"ID":"bc04cd89-c07d-4337-a086-650dd3815464","ERID":1032771,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"020105","CVALUE":"双肾形态、大小正常，包膜完整光滑，肾实质回声均匀，肾盂肾盏未见扩张。","DOCTOR":"崔联珍","CNAME":"双肾","KSMC":"彩超一","ORDERCODE":"05","ROW_ID":9},{"ID":"a5313344-2893-4352-bce3-9fbba9877c1f","ERID":1032772,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:42:30","KSDM":"US","HISITEM":"000003","MVALUE":"未见异常","DOCTOR":"崔联珍","CNAME":"检查结论","KSMC":"彩超一","ORDERCODE":"401","ROW_ID":10},{"ID":"fbd91064-1678-44bf-8db6-683901fcb135","ERID":1654364,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010101","CVALUE":"154.9","DOCTOR":"马文","CNAME":"身高","KSMC":"一般项目","ORDERCODE":"01","ROW_ID":11},{"ID":"aecffd9a-a088-484f-8f50-450cd343bf4c","ERID":1654365,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010102","CVALUE":"54.0","DOCTOR":"马文","CNAME":"体重","KSMC":"一般项目","ORDERCODE":"02","ROW_ID":12},{"ID":"508c9263-53a8-4d2e-bc5f-c83023073ac3","ERID":1654366,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010103","CVALUE":"22.51","DOCTOR":"马文","CNAME":"体重指数","KSMC":"一般项目","ORDERCODE":"03","ROW_ID":13},{"ID":"fd1ce299-d413-4fec-8be3-3331cc0ecb5d","ERID":1654367,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010104","CVALUE":"122","DOCTOR":"马文","CNAME":"收缩压","KSMC":"一般项目","ORDERCODE":"04","ROW_ID":14},{"ID":"5E4BC31D5EC84A3B8901404D7EAC9F70","ERID":1654368,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010105","CVALUE":"70","DOCTOR":"马文","CNAME":"舒张压","KSMC":"一般项目","ORDERCODE":"05","ROW_ID":15},{"ID":"1BBEC42F0D684BBA83FB9C2721A8A539","ERID":1654369,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010107","CVALUE":"66","DOCTOR":"马文","CNAME":"腰围","KSMC":"一般项目","ORDERCODE":"08","ROW_ID":16},{"ID":"10F6309C4C67455B85055A89A438C6A2","ERID":1654370,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010108","CVALUE":"90","DOCTOR":"马文","CNAME":"臀围","KSMC":"一般项目","ORDERCODE":"09","ROW_ID":17},{"ID":"5C885B2C7B22482D996F51B935E3D775","ERID":1654371,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010109","CVALUE":"0.73","DOCTOR":"马文","CNAME":"腰臀比","KSMC":"一般项目","ORDERCODE":"10","ROW_ID":18},{"ID":"24181636026443BC94B5D31E3BE5264C","ERID":1654372,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010115","CVALUE":"98","DOCTOR":"马文","CNAME":"血氧","KSMC":"一般项目","ORDERCODE":"13","ROW_ID":19},{"ID":"F2EA315792E64CC9824E732CB78E1882","ERID":1654373,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010106","CVALUE":"80","DOCTOR":"马文","CNAME":"脉搏","KSMC":"一般项目","ORDERCODE":"14","ROW_ID":20},{"ID":"FC6434177D9349DF8A2C258CE29DBADF","ERID":1654374,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"010118","CVALUE":"无","DOCTOR":"马文","CNAME":"既往病史","KSMC":"一般项目","ORDERCODE":"17","ROW_ID":21},{"ID":"7319a3e3-4c1e-4536-9f5f-2b071f880f18","ERID":1654375,"STUDYID":"1606300286","STUDYDATE":"2016-06-30 10:18:22","KSDM":"YB","HISITEM":"000001","MVALUE":"未见异常","DOCTOR":"马文","CNAME":"小结","KSMC":"一般项目","ORDERCODE":"403","ROW_ID":22}]
     * total : 23
     */

    private int total;
    /**
     * ID : 0b2952b0d93576dd24b49dcb66a9c7d8
     * ERID : 202960
     * STUDYID : 1606300286
     * STUDYDATE : 2016-06-30 10:40:06
     * KSDM : CR
     * HISITEM : 000004
     * MVALUE : 颈椎：颈椎生理曲度变直， 椎列连续； 骨质结构完整。 胸部：胸廓对称；纵隔居中，两肺纹理清晰；两肺门无增大；心影大小、形态如常；两膈面光整、肋膈角清晰锐利。
     * DOCTOR : 杨彦龙
     * CNAME : 检查描述
     * KSMC : 放射检查
     * ORDERCODE : 400
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
        private int ERID;
        private String STUDYID;
        private String STUDYDATE;
        private String KSDM;
        private String HISITEM;
        private String MVALUE;
        private String DOCTOR;
        private String CNAME;
        private String KSMC;
        private String ORDERCODE;
        private int ROW_ID;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setERID(int ERID) {
            this.ERID = ERID;
        }

        public void setSTUDYID(String STUDYID) {
            this.STUDYID = STUDYID;
        }

        public void setSTUDYDATE(String STUDYDATE) {
            this.STUDYDATE = STUDYDATE;
        }

        public void setKSDM(String KSDM) {
            this.KSDM = KSDM;
        }

        public void setHISITEM(String HISITEM) {
            this.HISITEM = HISITEM;
        }

        public void setMVALUE(String MVALUE) {
            this.MVALUE = MVALUE;
        }

        public void setDOCTOR(String DOCTOR) {
            this.DOCTOR = DOCTOR;
        }

        public void setCNAME(String CNAME) {
            this.CNAME = CNAME;
        }

        public void setKSMC(String KSMC) {
            this.KSMC = KSMC;
        }

        public void setORDERCODE(String ORDERCODE) {
            this.ORDERCODE = ORDERCODE;
        }

        public void setROW_ID(int ROW_ID) {
            this.ROW_ID = ROW_ID;
        }

        public String getID() {
            return ID;
        }

        public int getERID() {
            return ERID;
        }

        public String getSTUDYID() {
            return STUDYID;
        }

        public String getSTUDYDATE() {
            return STUDYDATE;
        }

        public String getKSDM() {
            return KSDM;
        }

        public String getHISITEM() {
            return HISITEM;
        }

        public String getMVALUE() {
            return MVALUE;
        }

        public String getDOCTOR() {
            return DOCTOR;
        }

        public String getCNAME() {
            return CNAME;
        }

        public String getKSMC() {
            return KSMC;
        }

        public String getORDERCODE() {
            return ORDERCODE;
        }

        public int getROW_ID() {
            return ROW_ID;
        }
    }
}
