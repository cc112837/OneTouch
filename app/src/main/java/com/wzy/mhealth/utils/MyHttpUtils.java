package com.wzy.mhealth.utils;

import android.os.Handler;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.Conclusion;
import com.wzy.mhealth.model.Friend;
import com.wzy.mhealth.model.HuaYanRecord;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.model.NewDetail;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.model.NoHuaRecord;
import com.wzy.mhealth.model.OrderInfo;
import com.wzy.mhealth.model.Record;
import com.wzy.mhealth.model.Retuback;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.StepRank;
import com.wzy.mhealth.model.StepResult;
import com.wzy.mhealth.model.TaocanDetail;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.ZhixingTaocan;


/**

 */
public class MyHttpUtils extends HttpUtils{
    private static MyHttpUtils httpUtils=new MyHttpUtils();
    public  static void sendData(HttpRequest.HttpMethod method,String url,RequestParams params,RequestCallBack requestCallBack){
        if(method== HttpRequest.HttpMethod.GET){
             httpUtils.send(method, url, requestCallBack);
        }
        else if (method== HttpRequest.HttpMethod.POST){
            httpUtils.send(method,url,params,requestCallBack);
        }
    }
  public static void handData(Handler handler,int what,String url,Object object){
      RequestParams params = new RequestParams();
      if(what==1){
          TiUser user=(TiUser)object;
          params.addBodyParameter("idnumber",user.getCardId());
          params.addBodyParameter("name",user.getName());
          params.addBodyParameter("phone",user.getTel());
          params.addBodyParameter("sex","0");
          params.addBodyParameter("passwd",user.getPass());
          sendData(HttpRequest.HttpMethod.POST,url,params,new MyCallBack(new Info(), handler, what));
      }
      if(what==2) {
          sendData(HttpRequest.HttpMethod.GET, url, params, new MyCallBack(new Info(), handler, what));
      }
      if(what==3||what==33){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new TaocanInfo(), handler, what));
      }
      if(what==6){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new ItemInfo(), handler, what));
      }
      if(what==7){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Record(), handler, what));
      }
      if(what==8){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Conclusion(), handler, what));
      }
      if(what==14){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NoHuaRecord(), handler, what));
      }
      if(what==15){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new HuaYanRecord(), handler, what));
      }
      if(what==21){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NewsYang(), handler, what));
      }
      if(what==22){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new NewDetail(), handler, what));

      }
      if(what==23){
          sendData(HttpRequest.HttpMethod.GET, url, null, new MyCallBack(new Record(), handler, what));
      }
      if(what==31){
          TiUser step=(TiUser)object;
          params.addBodyParameter("userName",step.getName());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
      }
      if(what==40){
          TiUser user=(TiUser)object;
          params.addBodyParameter("id",user.getName());
          params.addBodyParameter("number",user.getCardId());
          sendData(HttpRequest.HttpMethod.POST,url, params,new MyCallBack(new AliPayBack(),handler,what));

      }
      if (what==112){
          Friend user=(Friend)object;
          params.addBodyParameter("userName",user.getUsername());
          params.addBodyParameter("stepNum",user.getId());
          params.addBodyParameter("stepTime", user.getMood());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
      }
      if (what==113){
          StepInfo user=(StepInfo)object;
          params.addBodyParameter("userName",user.getData());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepResult(), handler, what));
      }
      if(what==115){
          sendData(HttpRequest.HttpMethod.POST,url,null,new MyCallBack(new ZhixingTaocan(),handler,what));
      }
      if(what==120){
          TiUser user=(TiUser)object;
          params.addBodyParameter("result",user.getName());
          params.addBodyParameter("id",user.getTel());
          params.addBodyParameter("userName",user.getCardId());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
      }
      if(what==116){
          TiUser user=(TiUser)object;
          params.addBodyParameter("id",user.getName());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new TaocanDetail(), handler, what));
      }
      if(what==121){
          TiUser user=(TiUser) object;
          params.addBodyParameter("userName",user.getName());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new OrderInfo(), handler, what));
      }
      if(what==122){
          TiUser user=(TiUser) object;
          params.addBodyParameter("userName",user.getName());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new OrderInfo(), handler, what));
      }
      if(what==123){
          TiUser user=(TiUser) object;
          params.addBodyParameter("userName",user.getName());
          params.addBodyParameter("stepTime",user.getPass());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepRank(), handler, what));
      }
      if(what==124){
          TiUser user=(TiUser) object;
          params.addBodyParameter("stepTime",user.getPass());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AllStepRank(), handler, what));
      }
      if(what==124){
          TiUser user=(TiUser) object;
          params.addBodyParameter("stepTime",user.getPass());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new AllStepRank(), handler, what));
      }
      if(what==131){
          TiUser user=(TiUser) object;
          params.addBodyParameter("OrderId",user.getTel());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new Retuback(), handler, what));
      }
      if(what==150){
          SelfHealth selfHealth=(SelfHealth)object;
          params.addBodyParameter("userName", LeanchatUser.getCurrentUser().getUsername());
          params.addBodyParameter("name",selfHealth.getName());
          params.addBodyParameter("sex",selfHealth.getSex());
          params.addBodyParameter("profession",selfHealth.getProfession());
          params.addBodyParameter("age",selfHealth.getAge());
          params.addBodyParameter("marrage",selfHealth.getMarrage());
          params.addBodyParameter("relator",selfHealth.getRelator());
          params.addBodyParameter("relate",selfHealth.getRelate());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new StepInfo(), handler, what));
      }
      if(what==151){
          params.addBodyParameter("userName",LeanchatUser.getCurrentUser().getUsername());
          sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new SelfHealth(), handler, what));
      }
  }
}
