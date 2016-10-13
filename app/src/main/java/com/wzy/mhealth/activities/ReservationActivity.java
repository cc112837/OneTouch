package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.guahaoAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.GuaHao;
import com.wzy.mhealth.model.GuahaoDoctorEntity;
import com.wzy.mhealth.utils.DateUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationActivity extends BaseActivity {
    private List<GuaHao> guahaoList;
    private guahaoAdapter adapter;
    private GuaHao guahao = new GuaHao();
    private ListView list = null;
    private TextView name, keshi, yuyueliang, pingjiazhi, zixunliang;
    private GuahaoDoctorEntity doctor = new GuahaoDoctorEntity();
    private String s, selectkeshi;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue);
        Intent intent = getIntent();
        doctor = (GuahaoDoctorEntity) intent.getSerializableExtra("doctor");
        selectkeshi = intent.getStringExtra("keshi");
        guahao = null;
        guahaoList = new ArrayList<GuaHao>();
        init();
        list = (ListView) this.findViewById(R.id.listView1);
        name = (TextView) this.findViewById(R.id.nameView);
        name.setText(doctor.getName());
        keshi = (TextView) this.findViewById(R.id.keshiView);
        keshi.setText(doctor.getZhicheng());
        yuyueliang = (TextView) this.findViewById(R.id.yuyueliang);
        yuyueliang.setText(doctor.getJiezhenliang());
        pingjiazhi = (TextView) this.findViewById(R.id.pingjiazhi);
        pingjiazhi.setText(doctor.getPingfen());
        zixunliang = (TextView) this.findViewById(R.id.zixunliang);
        adapter = new guahaoAdapter(this, guahaoList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                guahao = guahaoList.get(position);

            }
        });
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*
     * public void onClick(View v) { switch (v.getId()) { case R.id.leftBtn:
     * finish(); break; default: break; } }
     */
    public void left(View v) {

        finish();
    }

    public void yuyuewancheng(View v) {
        if (guahao == null)
            Toast.makeText(ReservationActivity.this, "请选择挂号时间", Toast.LENGTH_LONG).show();
        else if (guahao.getNumber() == 0)
            Toast.makeText(ReservationActivity.this, "当前时间已约满，请重新选择挂号时间", Toast.LENGTH_LONG)
                    .show();
        else {
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        HttpClient client = new DefaultHttpClient();
                        HttpPost request = new HttpPost(
                                Constants.SERVER_URL+"RegisterServlet");

                        List<NameValuePair> info = new ArrayList<NameValuePair>();
                        info.add(new BasicNameValuePair("doctorId", guahao
                                .getId()));
                        info.add(new BasicNameValuePair("time", DateUtil
                                .str1ToStr2(guahao.getDate())));
                        info.add(new BasicNameValuePair("userId",
                                Constants.USER_NAME + "_u"));
                        if (guahao.getWeek().equals("上午"))
                            info.add(new BasicNameValuePair("time_quantum", "0"));
                        else if (guahao.getWeek().equals("下午"))
                            info.add(new BasicNameValuePair("time_quantum", "1"));
                        HttpParams params = client.getParams();
                        HttpConnectionParams.setConnectionTimeout(params,
                                6 * 1000);
                        request.setEntity(new UrlEncodedFormEntity(info,
                                HTTP.UTF_8));
                        HttpResponse response = client.execute(request);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            String contact = EntityUtils.toString(
                                    response.getEntity(), HTTP.UTF_8);
                            JSONArray reJson = new JSONArray(contact);
                            for (int index = 0; index < reJson.length(); index++) {
                                JSONObject objJson = reJson
                                        .getJSONObject(index);
                                s = objJson.getString("result");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            th.start();
            while (th.isAlive()) {
            }
            if ("1".equals(s)) {
                Toast.makeText(ReservationActivity.this, "预约成功", Toast.LENGTH_LONG)
                        .show();
                //// TODO: 2016/4/14
                Intent intent = new Intent();
                intent.setClass(ReservationActivity.this, ReservationListActivity.class);
                startActivity(intent);
                GuahaoDoctorListActivity.instance.finish();
                KeshiselectActivity.instance.finish();
                finish();

            } else if ("0".equals(s))
                Toast.makeText(ReservationActivity.this,
                        "当前时间已约满，请重新选择挂号时间", Toast.LENGTH_LONG).show();
            else if ("2".equals(s))
                Toast.makeText(ReservationActivity.this,
                        "预约失败，您已在当前医生该时间段的预约表里", Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        //// TODO: 2016/5/25 (修改成无网络)
        guahaoList.addAll(XmppConnection.getInstance().getGuahaoList(
                doctor.getDoctorId()));

          guahaoList.add(new GuaHao( "2015-08-14","周五上午",1));
		  guahaoList.add(new GuaHao( "2015-08-15","周六上午",0));
		  guahaoList.add(new GuaHao("2015-08-15","周六下午",0)); guahaoList.add(new
		  GuaHao("2015-08-16","周日上午",1)); guahaoList.add(new
		  GuaHao("2015-08-16","周日下午",0)); guahaoList.add(new
                GuaHao("2015-08-17", "周一上午", 1));

    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
