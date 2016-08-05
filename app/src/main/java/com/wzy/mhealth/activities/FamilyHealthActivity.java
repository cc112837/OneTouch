package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVAnalytics;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.FamilyHealthAdapter;
import com.wzy.mhealth.model.FamilyHealth;

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

public class FamilyHealthActivity extends Activity {
    private ListView listView;
    private List<FamilyHealth> list = new ArrayList<>();
    private FamilyHealthAdapter famHealthAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_health);
        listView = (ListView) findViewById(R.id.listView1);
        famHealthAdapter = new FamilyHealthAdapter(this);
        list.addAll(getFamilyHealth());
        famHealthAdapter.addAll(list);
        listView.setAdapter(famHealthAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub

            }
        });
        getFamilyHealth();
    }

    public void leftBtnClick(View v) {
        finish();
    }

    public void add(View v) {
        startActivity(new Intent(FamilyHealthActivity.this,
                AddFamilyHealthActivity.class));
        finish();
    }




    public List<FamilyHealth> getFamilyHealth() {
        final List<FamilyHealth> li = new ArrayList<FamilyHealth>();
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost request = new HttpPost(
                            "http://123.57.191.21:8080/mhealth/servlet/FindUserFamilyServlet");
                    List<NameValuePair> info = new ArrayList<NameValuePair>();
                    info.add(new BasicNameValuePair("userName", LeanchatUser.getCurrentUser()
                            .getUsername()));
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
                    request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
                    // request.setEntity(new UrlEncodedFormEntity());
                    HttpResponse response = client.execute(request);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        String contact = EntityUtils.toString(response.getEntity(),
                                HTTP.UTF_8);


                        JSONArray reJson = new JSONArray(contact);
                        for (int index = 0; index < reJson.length(); index++) {
                            JSONObject objJson = reJson.getJSONObject(index);

                            FamilyHealth doctor = new FamilyHealth();
                            doctor.setMemberName(objJson.getString("name"));
                            doctor.setMemberAge(objJson.getString("age"));
                            doctor.setMemberMedicalHistory(objJson
                                    .getString("medicalHistory"));
                            doctor.setMemberRelated(objJson.getString("related"));
                            li.add(doctor);
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
        while (th.isAlive())
            ;
        return li;
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
