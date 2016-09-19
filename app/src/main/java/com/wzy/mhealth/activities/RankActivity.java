package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.RankAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.StepRank;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RankActivity extends Activity {
    private ImageView leftBtn, iv_userhead, iv_mytank;
    private ListView lv_show;
    private TextView tv_namemy, tv_rankmy, tv_daystep, tv_count;
    private CheckBox cb_prasid;
    private List<AllStepRank.DataEntity> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    StepRank stepRank = (StepRank) msg.obj;
                    tv_rankmy.setText(stepRank.getRank() + "");
                    tv_daystep.setText(stepRank.getStepNum() + "");
                    break;
                case 124:
                    AllStepRank allStepRank = (AllStepRank) msg.obj;
                    list.addAll(allStepRank.getData());
                    rankAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private RankAdapter rankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        String url = Constants.SERVER_URL + "StepNumMeRankServlet";
        TiUser user = new TiUser();
        user.setName(LeanchatUser.getCurrentUser().getUsername());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdata = sf.format(new Date());
        user.setPass(nowdata);
        MyHttpUtils.handData(handler, 123, url, user);
//        cb_prasid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                int position = (int) buttonView.getTag(R.id.cb_prasid);
//                if (isChecked) {
//
//                } else {
//
//                }
//            }
//        });

        lv_show = (ListView) findViewById(R.id.lv_show);
        String uri = Constants.SERVER_URL + "StepNumRankServlet";
        TiUser user1 = new TiUser();
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        String nowdat = sf1.format(new Date());
        user1.setPass(nowdat);
        MyHttpUtils.handData(handler, 124, uri, user1);
        rankAdapter = new RankAdapter(RankActivity.this, list);
        lv_show.setAdapter(rankAdapter);
        View headview = LayoutInflater.from(RankActivity.this).inflate(R.layout.list_rank_header, null);
        lv_show.addHeaderView(headview);

        iv_mytank = (ImageView) headview.findViewById(R.id.iv_mytank);
        tv_rankmy = (TextView) headview.findViewById(R.id.tv_rankmy);
        tv_namemy = (TextView) headview.findViewById(R.id.tv_namemy);
        tv_daystep = (TextView) headview.findViewById(R.id.tv_daystep);
        tv_count = (TextView) headview.findViewById(R.id.tv_count);
        cb_prasid = (CheckBox) headview.findViewById(R.id.cb_prasid);
        tv_namemy.setText(LeanchatUser.getCurrentUser().getUsername());
        iv_userhead = (ImageView) headview.findViewById(R.id.iv_userhead);

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
        ImageLoader.getInstance().displayImage(avatarUrl, iv_mytank, PhotoUtils.avatarImageOption);
        ImageLoader.getInstance().displayImage(avatarUrl, iv_userhead, PhotoUtils.avatarImageOption);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
