package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/14 15:45
 * 修改人：Administrator
 * 修改时间：2017/2/14 15:45
 * 修改备注：
 */

public class ScanImage {


    /**
     * status : 1
     * mdicalPicture : ["http://117.34.105.29:8818/mhealth/upload/letv_icon.png"]
     * data : 存在数据
     */

    private String status;
    private String data;
    private List<String> mdicalPicture;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getMdicalPicture() {
        return mdicalPicture;
    }

    public void setMdicalPicture(List<String> mdicalPicture) {
        this.mdicalPicture = mdicalPicture;
    }
}
