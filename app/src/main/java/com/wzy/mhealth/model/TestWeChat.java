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
     * timestamp : 20161206150742
     * result_code : SUCCESS
     * sign : 70B910B3AA30F1521EDFEEE8BD5515D9
     * mch_id : 1419840502
     * prepay_id : wx20161206150739124bdcc6cf0740539186
     * return_msg : OK
     * package : Sign=WXPay
     * appid : wxee8f5f748fbea43c
     * nonce_str : k3GwOC8oFjPZZo5G
     * return_code : SUCCESS
     * trade_type : APP
     */

    private String timestamp;
    private String result_code;
    private String sign;
    private String mch_id;
    private String prepay_id;
    private String return_msg;
    @SerializedName("package")
    private String packageX;
    private String appid;
    private String nonce_str;
    private String return_code;
    private String trade_type;

    @Override
    public String toString() {
        return "TestWeChat{" +
                "timestamp='" + timestamp + '\'' +
                ", result_code='" + result_code + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", packageX='" + packageX + '\'' +
                ", appid='" + appid + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", return_code='" + return_code + '\'' +
                ", trade_type='" + trade_type + '\'' +
                '}';
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
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

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
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

    public String getMch_id() {
        return mch_id;
    }

    public String getPrepay_id() {
        return prepay_id;
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

    public String getNonce_str() {
        return nonce_str;
    }

    public String getReturn_code() {
        return return_code;
    }

    public String getTrade_type() {
        return trade_type;
    }
}
