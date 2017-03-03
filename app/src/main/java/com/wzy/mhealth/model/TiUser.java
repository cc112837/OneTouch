package com.wzy.mhealth.model;

/**
 * 项目名称：mhealth
 * 类描述：体检相关用户类
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/30 17:10
 * 修改人：Administrator
 * 修改时间：2016/6/30 17:10
 * 修改备注：
 */
public class TiUser {
    String name;
    String pass;
    String cardId;
    String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
