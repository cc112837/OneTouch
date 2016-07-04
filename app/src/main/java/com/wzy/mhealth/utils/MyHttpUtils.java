package com.wzy.mhealth.utils;

import android.os.Handler;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TiUser;


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

  }
}
