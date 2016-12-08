package com.wzy.mhealth.model;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/12/6 15:10
 * 修改人：Administrator
 * 修改时间：2016/12/6 15:10
 * 修改备注：
 */
public class TestWeChat {



   /**
     * timestamp : 1481102662
     * result_code : SUCCESS
     * sign : 1316245E5CC73139FD2BB5290A8FBA06
     * noncestr : fjLJkMoxrknZpWo5
     * partnerid : 1419840502
     * prepayid : wx201612071723462a17759b4e0946566969
     * return_msg : OK
     * package : Sign=WXPay
     * appid : wxee8f5f748fbea43c
     * return_code : SUCCESS
     * trade_type : APP
     */

    private String timestamp;
    private String result_code;
    private String sign;
    private String noncestr;
    private String partnerid;
    private String prepayid;
    private String return_msg;
    @SerializedName("package")
    private String packageX;
    private String appid;
    private String return_code;
    private String trade_type;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getSign() {
        return sign;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getPackageX() {
        return packageX;
    }

    public String getAppid() {
        return appid;
    }

    public String getReturn_code() {
        return return_code;
    }

    public String getTrade_type() {
        return trade_type;
    }
}
