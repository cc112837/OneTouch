package com.wzy.mhealth.utils;

import android.os.Handler;
import android.util.Log;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.model.Address;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.BingZhen;
import com.wzy.mhealth.model.Cart;
import com.wzy.mhealth.model.Conclusion;
import com.wzy.mhealth.model.Decrease;
import com.wzy.mhealth.model.DefaultAdress;
import com.wzy.mhealth.model.Doctor;
import com.wzy.mhealth.model.DoctorDetail;
import com.wzy.mhealth.model.FirstDep;
import com.wzy.mhealth.model.ForgetPass;
import com.wzy.mhealth.model.Friend;
import com.wzy.mhealth.model.HuaYanRecord;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.model.NewDetail;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.model.NoHuaRecord;
import com.wzy.mhealth.model.OrderInfo;
import com.wzy.mhealth.model.Pridefine;
import com.wzy.mhealth.model.Proud;
import com.wzy.mhealth.model.Provice;
import com.wzy.mhealth.model.Question;
import com.wzy.mhealth.model.ReDefine;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.Record;
import com.wzy.mhealth.model.Regmodel;
import com.wzy.mhealth.model.Retuback;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.Shop;
import com.wzy.mhealth.model.ShopBuy;
import com.wzy.mhealth.model.ShopCart;
import com.wzy.mhealth.model.ShopDetail;
import com.wzy.mhealth.model.ShopDetail2;
import com.wzy.mhealth.model.ShopOrder;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.StepRank;
import com.wzy.mhealth.model.StepResult;
import com.wzy.mhealth.model.TaocanDetail;
import com.wzy.mhealth.model.TaocanEntity;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TestWeChat;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.model.UserManger;
import com.wzy.mhealth.model.Zan;
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
        if (what == 1) {//智行注册验证
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
        if (what == 21) {//资讯列表页面
            params.addBodyParameter("type", ((TiUser) object).getName());
            params.addBodyParameter("pageCount", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new NewsYang(), handler, what));
        }
        if (what == 22) {//资讯详情页面
            params.addBodyParameter("medicalId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new NewDetail(), handler, what));
        }
        if (what == 23) {
            sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Record(), handler, what));
        }
        if (what == 31) {
            TiUser step = (TiUser) object;
            params.addBodyParameter("userName", step.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 40) {//医生咨询(套餐支付)
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("type", user.getPass());
            params.addBodyParameter("number", user.getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AliPayBack(), handler, what));

        }
        if (what == 112) {//一周内的计步数
            Friend user = (Friend) object;
            params.addBodyParameter("userName", user.getUsername());
            params.addBodyParameter("stepNum", user.getId());
            params.addBodyParameter("stepTime", user.getMood());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 113) {//每天的步数
            StepInfo user = (StepInfo) object;
            params.addBodyParameter("userName", user.getData());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepResult(), handler, what));
        }
        if (what == 115) {//套餐列表
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ZhixingTaocan(), handler, what));
        }
        if (what == 120) {//支付包套餐同步通知（医生咨询）
            TiUser user = (TiUser) object;
            params.addBodyParameter("result", user.getName());
            params.addBodyParameter("id", user.getTel());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 116) {//套餐详情
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
        if (what == 123) {//我的计步排行
            TiUser user = (TiUser) object;
            params.addBodyParameter("userName", user.getName());
            params.addBodyParameter("stepTime", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepRank(), handler, what));
        }
        if (what == 124) {//所有用户的计步排行
            TiUser user = (TiUser) object;
            params.addBodyParameter("userName", user.getName());
            params.addBodyParameter("stepTime", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AllStepRank(), handler, what));
        }
        if (what == 131) {//退款
            TiUser user = (TiUser) object;
            params.addBodyParameter("orderId", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Retuback(), handler, what));
        }
        if (what == 149) {//科室分类
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new FirstDep(), handler, what));
        }
        if (what == 150) {//健康管家
            SelfHealth selfHealth = (SelfHealth) object;
//            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
//            params.addBodyParameter("name", selfHealth.getName());
//            params.addBodyParameter("sex", selfHealth.getSex());
//            params.addBodyParameter("profession", selfHealth.getProfession());
//            params.addBodyParameter("age", selfHealth.getAge());
//            params.addBodyParameter("marrage", selfHealth.getMarrage());
//            params.addBodyParameter("relator", selfHealth.getRelator());
//            params.addBodyParameter("relate", selfHealth.getRelate());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 151) {//病历管理页面数据请求
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new SelfHealth(), handler, what));
        }
        if (what == 152) {//医生列表
            params.addBodyParameter("firstDepId", ((TiUser) object).getName());
            params.addBodyParameter("evaluate", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Doctor(), handler, what));
        }
        if (what == 153) {
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new DoctorDetail(), handler, what));
        }
        if (what == 155) {//用户注册
            TiUser user = (TiUser) object;
            params.addBodyParameter("phone", user.getName());
            params.addBodyParameter("passWord", user.getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Regmodel(), handler, what));
        }
        if (what == 157) {//体检机构列表
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Tijian(), handler, what));
        }
        if (what == 160) {//省市联动
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Provice(), handler, what));
        }
        if (what == 170) {//我的勋章.积分 优惠劵
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Proud(), handler, what));
        }
        if (what == 171 || what == 172) {//套餐详情页面
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new Tijian(), handler, what));
        }
        if (what == 173) {//套餐列表
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new TaocanEntity(), handler, what));
        }
        if (what == 180) {//忘记密码
            params.addBodyParameter("phone", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ForgetPass(), handler, what));
        }
        if (what == 181) {//修改密码
            params.addBodyParameter("phone", ((TiUser) object).getName());
            params.addBodyParameter("passWord1", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 220) {//问卷调查判断
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Question(), handler, what));
        }
        if (what == 221) {//问卷调查0
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("taocanName", ((TiUser) object).getName());
            params.addBodyParameter("sex", ((TiUser) object).getTel());
            params.addBodyParameter("count", "0");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 222) {//问卷调查套餐推荐
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Recommend(), handler, what));
        }
        if (what == 223) {//问卷调查1
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("age", ((TiUser) object).getName());
            params.addBodyParameter("height", ((TiUser) object).getTel());
            params.addBodyParameter("weight", ((TiUser) object).getPass());
            params.addBodyParameter("count", "1");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 224) {//问卷调查2
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("live", ((TiUser) object).getName());
            params.addBodyParameter("count", "2");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 225) {//问卷调查3
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("time", ((TiUser) object).getName());
            params.addBodyParameter("count", "3");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 226) {//问卷调查4
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("disease", ((TiUser) object).getName());
            params.addBodyParameter("count", "4");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 227) {//问卷调查8
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("inspect", ((TiUser) object).getName());
            params.addBodyParameter("count", "8");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 228) {//问卷调查9
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("baby", ((TiUser) object).getName());
            params.addBodyParameter("count", "9");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 229) {//问卷调查10
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("price", ((TiUser) object).getName());
            params.addBodyParameter("count", "10");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 230) {//问卷调查11
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("supplement", ((TiUser) object).getName());
            params.addBodyParameter("count", "11");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 231) {//问卷调查5
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("illness", ((TiUser) object).getName());
            params.addBodyParameter("count", "5");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 232) {//问卷调查6
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("symptom", ((TiUser) object).getName());
            params.addBodyParameter("count", "6");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 233) {//问卷调查7
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("medicine", ((TiUser) object).getName());
            params.addBodyParameter("count", "7");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 261) {//判断是否有推荐套餐
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ReDefine(), handler, what));
        }
        if (what == 262) {
            params.addBodyParameter("orderId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 263) {//满意度评价
            params.addBodyParameter("orderId", ((TiUser) object).getName());
            params.addBodyParameter("satisify", ((TiUser) object).getTel());
            params.addBodyParameter("evaluate", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 264) {//查看评价
            params.addBodyParameter("taoId", ((TiUser) object).getName());
            params.addBodyParameter("status", ((TiUser) object).getPass());
            params.addBodyParameter("statify", ((TiUser) object).getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserEvaluation(), handler, what));
        }
        if (what == 265) {
            params.addBodyParameter("type", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new BingZhen(), handler, what));
        }
        if (what == 266) {//向服务端传用户图像
            params.addBodyParameter("userName", ((TiUser) object).getName());
            params.addBodyParameter("objectId", ((TiUser) object).getCardId());
            params.addBodyParameter("imgUrl", ((TiUser) object).getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 267) {
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("id", ((TiUser) object).getName());
            params.addBodyParameter("orderTime", ((TiUser) object).getPass());
            params.addBodyParameter("order", ((TiUser) object).getCardId());
            params.addBodyParameter("telephone", ((TiUser) object).getTel());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 268) {//计步点赞
            params.addBodyParameter("userName", ((TiUser) object).getName());
            params.addBodyParameter("stepNumId", ((TiUser) object).getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 269) {//计步谁点赞了我
            params.addBodyParameter("stepTime", ((TiUser) object).getPass());
            params.addBodyParameter("userName", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Zan(), handler, what));
        }
        if (what == 270) {//商城首页
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Shop(), handler, what));
        }
        if (what == 271) {//私人医生价格
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Pridefine(), handler, what));
        }
        if (what == 272) {//购物车页面
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Cart(), handler, what));
        }
        if (what == 273 || what == 274) {//可使用优惠劵//不可使用优惠劵
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("type", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Decrease(), handler, what));

        }
        if (what == 275) {//商品详情/商品
            params.addBodyParameter("productId", ((TiUser) object).getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ShopDetail(), handler, what));
        }
        if (what == 276) {//商品详情/详情
            params.addBodyParameter("productId", ((TiUser) object).getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ShopDetail2(), handler, what));
        }
        if (what == 277) {//加入购物车
            params.addBodyParameter("productId", ((Pridefine) object).getName());
            params.addBodyParameter("productNumber", ((Pridefine) object).getTaoId() + "");
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 278) {//收货地址
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Address(), handler, what));
        }
        if (what == 279) {//添加或者修改收货地址
            params.addBodyParameter("name", ((Recommend) object).getName());
            params.addBodyParameter("telephone", ((Recommend) object).getTaocanNum());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("area", ((Recommend) object).getData());
            params.addBodyParameter("address", ((Recommend) object).getContext());
            params.addBodyParameter("sid", ((Recommend) object).getTaoId() + "");
            params.addBodyParameter("addressId", ((Recommend) object).getStatus() + "");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 280) {//删除收货地址
            params.addBodyParameter("addressId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 281) {//用户默认地址
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new DefaultAdress(), handler, what));
        }
        if (what == 282) {//删除购物车商品
            params.addBodyParameter("shopcartId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 283) {//收货临时地址
            params.addBodyParameter("userName", ((TiUser) object).getName());
            params.addBodyParameter("addressId", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 284) {//购物车点击结算
            params.addBodyParameter("result", ((TiUser) object).getName());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ShopCart(), handler, what));
        }
        if (what == 285) {//购物车加减数量
            params.addBodyParameter("productNumber", ((TiUser) object).getName());
            params.addBodyParameter("shopcartId", ((TiUser) object).getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 286) {//购物车数量
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ShopBuy(), handler, what));
        }
        if (what == 287) {//商品购买
            params.addBodyParameter("totalPrice", ((TiUser) object).getName());
            params.addBodyParameter("addressId", ((TiUser) object).getCardId());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AliPayBack(), handler, what));
        }
        if (what == 288) {//同步参数返回(支付宝。微信)
            params.addBodyParameter("result", ((TiUser) object).getName());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 289) {//商品订单分类
            params.addBodyParameter("type", ((TiUser) object).getName());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ShopOrder(), handler, what));
        }
        if (what == 290) {//商品评价
            params.addBodyParameter("orderId", ((TiUser) object).getCardId());
            params.addBodyParameter("satisify", ((TiUser) object).getTel());
            params.addBodyParameter("evaluate", ((TiUser) object).getPass());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 291) {//查看订单评价
            params.addBodyParameter("productId", ((TiUser) object).getCardId());
            params.addBodyParameter("statify", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserEvaluation(), handler, what));
        }
        if (what == 292 || what == 293 || what == 294 || what == 295) {//提醒发货//确认收货//申请商品退款//删除订单
            params.addBodyParameter("orderId", ((TiUser) object).getName());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }
        if (what == 296) {//微信支付商品订单
            params.addBodyParameter("totalPrice", ((TiUser) object).getName());
            params.addBodyParameter("addressId", ((TiUser) object).getCardId());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new TestWeChat(), handler, what));
        }
        if (what == 297) {//微信支付套餐。医生咨询
            TiUser user = (TiUser) object;
            params.addBodyParameter("id", user.getName());
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("type", user.getPass());
            params.addBodyParameter("number", user.getCardId());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new TestWeChat(), handler, what));
        }
        if (what == 298) {//用户管理页面
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserManger(), handler, what));
        }
        if (what == 299) {
            params.addBodyParameter("name", ((Recommend) object).getName());
            params.addBodyParameter("userID", ((Recommend) object).getNewPrice() + "");
            params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
            params.addBodyParameter("sex", ((Recommend) object).getImage());
            params.addBodyParameter("age", ((Recommend) object).getData());
            params.addBodyParameter("birth", ((Recommend) object).getContext() + "");
            params.addBodyParameter("userManageId", ((Recommend) object).getStatus() + "");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));

        }
        if (what == 300) {
            params.addBodyParameter("userManageId", ((Recommend) object).getStatus() + "");
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
        }

    }
}
