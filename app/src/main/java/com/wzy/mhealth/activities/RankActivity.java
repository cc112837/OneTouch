package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.RankAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.StepRank;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RankActivity extends Activity {
    private ImageView leftBtn, iv_userhead, iv_mytank;
    private ListView lv_show;
    private TextView tv_namemy, tv_who, tv_rankmy, tv_daystep, tv_count;
    private CheckBox cb_prasid;
    private RankAdapter rankAdapter;
    private LinearLayout ll_see;
    private List<AllStepRank.DataEntity> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:
                    StepRank stepRank = (StepRank) msg.obj;
                    tv_rankmy.setText(stepRank.getRank() + 1 + "");
                    tv_daystep.setText(stepRank.getStepNum() + "");
                    tv_count.setText(stepRank.getLikeNum() + "");
                    if (stepRank.getLikeNum() > 0) {
                        cb_prasid.setChecked(true);
                    } else {
                        cb_prasid.setChecked(false);
                    }
                    cb_prasid.setEnabled(false);
                    ImageLoader.getInstance().displayImage(stepRank.getImage(), iv_mytank, PhotoUtils.avatarImageOption);
                    break;
                case 124:
                    AllStepRank allStepRank = (AllStepRank) msg.obj;
                    tv_who.setText(allStepRank.getData().get(0).getUserName() + "占领了封面");
                    ImageLoader.getInstance().displayImage(allStepRank.getData().get(0).getImage(), iv_userhead, PhotoUtils.avatarImageOption);
                    rankAdapter.setList(allStepRank.getData());
                    rankAdapter.notifyDataSetChanged();
                    break;
                case 268:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    break;
            }
        }
    };


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

        lv_show = (ListView) findViewById(R.id.lv_show);
        String uri = Constants.SERVER_URL + "StepNumRankServlet";
        rankAdapter = new RankAdapter(RankActivity.this, list, handler);
        lv_show.setAdapter(rankAdapter);
        MyHttpUtils.handData(handler, 124, uri, user);
        View headview = LayoutInflater.from(RankActivity.this).inflate(R.layout.list_rank_header, null);
        lv_show.addHeaderView(headview);
        ll_see = (LinearLayout) headview.findViewById(R.id.ll_see);
        tv_who = (TextView) headview.findViewById(R.id.tv_who);
        iv_mytank = (ImageView) headview.findViewById(R.id.iv_mytank);
        tv_rankmy = (TextView) headview.findViewById(R.id.tv_rankmy);
        tv_namemy = (TextView) headview.findViewById(R.id.tv_namemy);
        tv_daystep = (TextView) headview.findViewById(R.id.tv_daystep);
        tv_count = (TextView) headview.findViewById(R.id.tv_count);
        cb_prasid = (CheckBox) headview.findViewById(R.id.cb_prasid);
        tv_namemy.setText(LeanchatUser.getCurrentUser().getUsername());
        iv_userhead = (ImageView) headview.findViewById(R.id.iv_userhead);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });

    }
}
