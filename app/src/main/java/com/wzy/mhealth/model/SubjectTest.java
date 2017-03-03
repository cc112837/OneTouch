package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：总检项目类
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/20 13:56
 * 修改人：Administrator
 * 修改时间：2016/7/20 13:56
 * 修改备注：
 */
public class SubjectTest {
    private String name;
    private String cvalue;
    private String usual;
    private String defau;

    public SubjectTest(String name, String cvalue) {
        this.name = name;
        this.cvalue = cvalue;
    }

    public SubjectTest(String name, String cvalue, String defau,String usual) {
        this.defau = defau;
        this.name = name;
        this.cvalue = cvalue;
        this.usual = usual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getUsual() {
        return usual;
    }

    public void setUsual(String usual) {
        this.usual = usual;
    }

    public String getDefau() {
        return defau;
    }

    public void setDefau(String defau) {
        this.defau = defau;
    }
}
