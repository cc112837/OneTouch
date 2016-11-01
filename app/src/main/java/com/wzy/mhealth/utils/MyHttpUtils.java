package com.wzy.mhealth.utils;

import android.os.Handler;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.City;
import com.wzy.mhealth.model.Conclusion;
import com.wzy.mhealth.model.Doctor;
import com.wzy.mhealth.model.DoctorDetail;
import com.wzy.mhealth.model.ForgetPass;
import com.wzy.mhealth.model.Friend;
import com.wzy.mhealth.model.Grade;
import com.wzy.mhealth.model.HuaYanRecord;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.model.NewDetail;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.model.NoHuaRecord;
import com.wzy.mhealth.model.OrderInfo;
import com.wzy.mhealth.model.Proud;
import com.wzy.mhealth.model.Provice;
import com.wzy.mhealth.model.Question;
import com.wzy.mhealth.model.ReDefine;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.Record;
import com.wzy.mhealth.model.Regmodel;
import com.wzy.mhealth.model.Retuback;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.StepRank;
import com.wzy.mhealth.model.StepResult;
import com.wzy.mhealth.model.TaocanDetail;
import com.wzy.mhealth.model.TaocanEntity;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.model.ZhixingTaocan;


/**

 */
public class MyHttpUtils extends HttpUtils {
    private static MyHttpUtils httpUtils = new MyHttpUtils();

    public static void sendData(HttpRequest.HttpMethod method, String url, RequestParams params, RequestCallBack requestCallBack) {
        if (method == HttpRequest.HttpMethod.GET) {
            httpUtils.send(method, url, requestCallBack);
        } else if (method == HttpRequest.HttpMethod.POST) {
            httpUtils.send(method, url, params, requestCallBack);
        }
    }

    public static void handData(Handler handler, int what, String url, Object object) {
        httpUtils.configCurrentHttpCacheExpiry(6000);// 设置超时时间
        httpUtils.configTimeout(6000);// 连接超时  //指的是连接一个url的连接等待时间。
        httpUtils.configSoTimeout(6000);// 获取数据超时  //指的是连接上一个url，获取response的返回等待时间
        RequestParams params = new RequestParams();
        if (what == 1) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("idnumber", user.getCardId());
            params.addBodyParameter("name", user.getName());
            params.addBodyParameter("phone", user.getTel());
            params.addBodyParameter("sex", "0");
            params.addBodyParameter("passwd", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Info(), handler, what));
        }
        if (what == 2) {
            sendData(HttpRequest.HttpMethod.GET, url, params, new MyCallBack(new Info(), handler, what));
        }
        if (what == 3 || what == 33) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new TaocanInfo(), handler, what));
        }
        if (what == 6) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new ItemInfo(), handler, what));
        }
        if (what == 7) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Record(), handler, what));
        }
        if (what == 8) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Conclusion(), handler, what));
        }
        if (what == 14) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NoHuaRecord(), handler, what));
        }
        if (what == 15) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new HuaYanRecord(), handler, what));
        }
        if (what == 21) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NewsYang(), handler, what));
        }
        if (what == 22) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NewDetail(), handler, what));

        }
        if (what == 23) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Record(), handler, what));
        }
        if (what == 31) {
            TiUser step = (TiUser) object;
            params.addBodyParameter("userName", step.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 40) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            params.addBodyParameter("number", user.getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AliPayBack(), handler, what));

        }
        if (what == 112) {
            Friend user = (Friend) object;
            params.addBodyParameter("userName", user.getUsername());
            params.addBodyParameter("stepNum", user.getId());
            params.addBodyParameter("stepTime", user.getMood());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 113) {
            StepInfo user = (StepInfo) object;
            params.addBodyParameter("userName", user.getData());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepResult(), handler, what));
        }
        if (what == 115) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ZhixingTaocan(), handler, what));
        }
        if (what == 120) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("result", user.getName());
            params.addBodyParameter("id", user.getTel());
            params.addBodyParameter("userName", user.getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 116) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new TaocanDetail(), handler, what));
        }
        if (what == 121) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("userName", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new OrderInfo(), handler, what));
        }
        if (what == 122) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("userName", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new OrderInfo(), handler, what));
        }
        if (what == 123) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("userName", user.getName());
            params.addBodyParameter("stepTime", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepRank(), handler, what));
        }
        if (what == 124) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("stepTime", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AllStepRank(), handler, what));
        }
        if (what == 124) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("stepTime", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AllStepRank(), handler, what));
        }
        if (what == 131) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("OrderId", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Retuback(), handler, what));
        }
        if (what == 150) {
            SelfHealth selfHealth = (SelfHealth) object;
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("name", selfHealth.getName());
            params.addBodyParameter("sex", selfHealth.getSex());
            params.addBodyParameter("profession", selfHealth.getProfession());
            params.addBodyParameter("age", selfHealth.getAge());
            params.addBodyParameter("marrage", selfHealth.getMarrage());
            params.addBodyParameter("relator", selfHealth.getRelator());
            params.addBodyParameter("relate", selfHealth.getRelate());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 151) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new SelfHealth(), handler, what));
        }
        if (what == 152) {
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Doctor(), handler, what));
        }
        if (what == 153) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new DoctorDetail(), handler, what));
        }
        if (what == 155) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("phone", user.getName());
            params.addBodyParameter("passWord", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Regmodel(), handler, what));
        }
        if (what == 156) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Grade(), handler, what));
        }
        if (what == 157) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Tijian(), handler, what));
        }
        if (what == 160) {
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Provice(), handler, what));
        }
        if (what == 162) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new City(), handler, what));
        }
        if (what == 170) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Proud(), handler, what));
        }
        if (what == 171) {
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Tijian(), handler, what));
        }
        if (what == 172) {
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Tijian(), handler, what));
        }
        if (what == 173) {
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new TaocanEntity(), handler, what));
        }
        if (what == 180) {
            params.addBodyParameter("phone", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ForgetPass(), handler, what));
        }
        if (what == 181) {
            params.addBodyParameter("phone", ((TiUser) object).getName());
            params.addBodyParameter("passWord1", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 220) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Question(), handler, what));
        }
        if (what == 221) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("taocanName", ((TiUser) object).getName());
            params.addBodyParameter("sex", ((TiUser) object).getTel());
            params.addBodyParameter("count", "0");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 222) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Recommend(), handler, what));
        }
        if (what == 223) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("age", ((TiUser) object).getName());
            params.addBodyParameter("height", ((TiUser) object).getTel());
            params.addBodyParameter("weight", ((TiUser) object).getPass());
            params.addBodyParameter("count", "1");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 224) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("live", ((TiUser) object).getName());
            params.addBodyParameter("count", "2");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 225) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("time", ((TiUser) object).getName());
            params.addBodyParameter("count", "3");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 226) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("disease", ((TiUser) object).getName());
            params.addBodyParameter("count", "4");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 227) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("inspect", ((TiUser) object).getName());
            params.addBodyParameter("count", "8");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 228) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("baby", ((TiUser) object).getName());
            params.addBodyParameter("count", "9");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 229) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("price", ((TiUser) object).getName());
            params.addBodyParameter("count", "10");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 230) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("supplement", ((TiUser) object).getName());
            params.addBodyParameter("count", "11");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 231) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("illness", ((TiUser) object).getName());
            params.addBodyParameter("count", "5");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 232) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("symptom", ((TiUser) object).getName());
            params.addBodyParameter("count", "6");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 233) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("medicine", ((TiUser) object).getName());
            params.addBodyParameter("count", "7");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 261) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ReDefine(), handler, what));
        }
        if (what == 262) {
            params.addBodyParameter("OrderId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 263) {
            params.addBodyParameter("OrderId", ((TiUser) object).getName());
            params.addBodyParameter("satisify", ((TiUser) object).getTel());
            params.addBodyParameter("evaluate", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 264) {
            params.addBodyParameter("taoId", ((TiUser) object).getName());
            params.addBodyParameter("status", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserEvaluation(), handler, what));
        }
    }
}
