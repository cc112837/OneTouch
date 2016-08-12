package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFamilyHealthActivity extends BaActivity {
    private EditText nameText, ageText, medicalHistoryText, relatedText;
    private String name, age, medicalHistory, related;
    private TextView queRen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family_health);
        nameText = (EditText) findViewById(R.id.nameView);
        ageText = (EditText) findViewById(R.id.ageView);
        medicalHistoryText = (EditText) findViewById(R.id.medicalHistory);
        relatedText = (EditText) findViewById(R.id.related);
        queRen = (TextView) findViewById(R.id.right);
        queRen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                age = ageText.getText().toString();
                medicalHistory = medicalHistoryText.getText().toString();
                related = relatedText.getText().toString();
                if (TextUtils.isEmpty(name)) ;
                else if (TextUtils.isEmpty(medicalHistory)) ;
                else Queren();

            }
        });

    }

    public void leftBtnClick(View v) {
        finish();
    }

    // public void rightBtnClick(View v) {
    //
    // }
    private void Queren() {
        Thread thth = new Thread() {
            @Override
            public void run() {
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost request = new HttpPost(
                            "http://123.57.191.21:8080/mhealth/servlet/SaveUserFamilyServlet");
                    List<NameValuePair> info = new ArrayList<NameValuePair>();
                    info.add(new BasicNameValuePair("userName", LeanchatUser.getCurrentUser()
                            .getUsername()));
                    info.add(new BasicNameValuePair("familyName", name));
                    info.add(new BasicNameValuePair("age", age));
                    info.add(new BasicNameValuePair("medicalHistory", medicalHistory));
                    info.add(new BasicNameValuePair("related", related));
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 6 * 1000);
                    request.setEntity(new UrlEncodedFormEntity(info, HTTP.UTF_8));
                    HttpResponse response = client.execute(request);
                    Log.e("成功了", response.getStatusLine().getStatusCode() + "");
                    if (response.getStatusLine().getStatusCode() == 200) {
                        Log.e("成功了", "" + EntityUtils.toString(
                                response.getEntity(), HTTP.UTF_8));
                        AddFamilyHealthActivity.this.finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thth.start();
    }

}
