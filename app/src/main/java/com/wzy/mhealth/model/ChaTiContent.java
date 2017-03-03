package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：查体内容
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/24 14:46
 * 修改人：Administrator
 * 修改时间：2016/6/24 14:46
 * 修改备注：
 */
public class ChaTiContent {
    // TODO: 2016/6/24

    String itemname;
   String itemcode;
    String stuyid;
    String id;

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getStuyid() {
        return stuyid;
    }

    public void setStuyid(String stuyid) {
        this.stuyid = stuyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }


}
