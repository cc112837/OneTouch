package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.RankAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends Activity {
    private ImageView leftBtn,iv_userhead,iv_mytank;
    private ListView lv_show;
    private TextView tv_namemy,tv_rankmy,tv_daystep,tv_count;
    private CheckBox cb_prasid;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        init();
    }

    private void init() {
        tv_namemy=(TextView) findViewById(R.id.tv_namemy);
        tv_rankmy=(TextView) findViewById(R.id.tv_rankmy);
        tv_daystep=(TextView) findViewById(R.id.tv_daystep);
        tv_count=(TextView) findViewById(R.id.tv_count);
        cb_prasid=(CheckBox) findViewById(R.id.cb_prasid);
        cb_prasid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = (int) buttonView.getTag(R.id.cb_prasid);
                if (isChecked) {

                } else {

                }
            }
        });
        tv_namemy.setText(LeanchatUser.getCurrentUser().getUsername());
        iv_mytank=(ImageView) findViewById(R.id.iv_mytank);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        iv_userhead=(ImageView) findViewById(R.id.iv_userhead);
        lv_show=(ListView) findViewById(R.id.lv_show);
        RankAdapter rankAdapter = new RankAdapter(RankActivity.this,list);
        lv_show.setAdapter(rankAdapter);
        LeanchatUser curUser = AVUser.getCurrentUser(LeanchatUser.class);
        String avatarUrl = curUser.getAvatarUrl();
        if (avatarUrl == null) {
            try {
                JSONObject object = new JSONObject(curUser.toString());
                JSONObject serverData = object.getJSONObject("serverData");
                JSONObject avatar = serverData.getJSONObject("avatar");
                avatarUrl = avatar.getString("url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ImageLoader.getInstance().displayImage(avatarUrl,iv_mytank, PhotoUtils.avatarImageOption);
        ImageLoader.getInstance().displayImage(avatarUrl,iv_userhead, PhotoUtils.avatarImageOption);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
