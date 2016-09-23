package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/23 10:14
 * 修改人：Administrator
 * 修改时间：2016/9/23 10:14
 * 修改备注：
 */
public class SelfHealth {

    /**
     * name : 梦想宇宙
     * sex : 男
     * profession : IT员
     * age : 26
     * marrage : 是
     * relator : 无
     * relate : 无
     */

    private String name;
    private String sex;
    private String profession;
    private String age;
    private String marrage;
    private String relator;
    private String relate;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMarrage(String marrage) {
        this.marrage = marrage;
    }

    public void setRelator(String relator) {
        this.relator = relator;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getProfession() {
        return profession;
    }

    public String getAge() {
        return age;
    }

    public String getMarrage() {
        return marrage;
    }

    public String getRelator() {
        return relator;
    }

    public String getRelate() {
        return relate;
    }
}
