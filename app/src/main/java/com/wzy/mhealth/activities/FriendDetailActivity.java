package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFriendship;
import com.avos.avoscloud.AVFriendshipQuery;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.callback.AVFriendshipCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avoscloud.leanchatlib.activity.AVBaseActivity;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.ConversationType;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.Constants;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.LeanChat.service.AddRequestManager;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 好友详情页面
*/
public class FriendDetailActivity extends AVBaseActivity implements View.OnClickListener {
    TextView usernameView;
    ImageView avatarView, avatarArrowView, leftBtn;
    LinearLayout allLayout;
    Button chatBtn, addFriendBtn;
    RelativeLayout avatarLayout;
    String userId, username, url;
    boolean isFriend = false;
    LeanchatUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        initData();
        findView();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        String use = intent.getStringExtra("use");
        JSONObject s;
        try {
            s = new JSONObject(use);
            JSONObject serverData = s.getJSONObject("serverData");
            userId = s.getString("objectId");
            username = serverData.getString("username");
            JSONObject avatar = serverData.getJSONObject("avatar");
            url = avatar.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void findView() {
        allLayout = (LinearLayout) findViewById(R.id.all_layout);
        avatarView = (ImageView) findViewById(R.id.avatar_view);
        avatarArrowView = (ImageView) findViewById(R.id.avatar_arrow);
        usernameView = (TextView) findViewById(R.id.username_view);
        avatarLayout = (RelativeLayout) findViewById(R.id.head_layout);
        chatBtn = (Button) findViewById(R.id.chatBtn);
        addFriendBtn = (Button) findViewById(R.id.addFriendBtn);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        LeanchatUser curUser = LeanchatUser.getCurrentUser();
        if (curUser.getUsername().equals(username)) {
            setTitle(R.string.contact_personalInfo);
            avatarLayout.setOnClickListener(this);
            avatarArrowView.setVisibility(View.VISIBLE);
            chatBtn.setVisibility(View.GONE);
            addFriendBtn.setVisibility(View.GONE);
        } else {
            setTitle(R.string.contact_detailInfo);
            avatarArrowView.setVisibility(View.INVISIBLE);
            AVFriendshipQuery query = AVUser
                    .friendshipQuery(AVUser.getCurrentUser()
                            .getObjectId(), LeanchatUser.class);
            query.include("follower");
            query.getInBackground(new AVFriendshipCallback() {

                                      @Override
                                      public void done(AVFriendship friendship, AVException e) {
                                          if (e == null) {
                                              List<LeanchatUser> followees = friendship
                                                      .getFollowers(); // 获取关注列表
                                              List<String> Ids = new ArrayList<String>();
                                              for (LeanchatUser u : followees) {
                                                  if (u.getUsername().equals(username)) {
                                                      user = u;
                                                      isFriend = true;
                                                      break;
                                                  } else {
                                                      isFriend = false;
                                                  }
                                              }
                                              CacheService.setFriendIds(Ids);
                                          }
                                          if (isFriend)
                                          {
                                              chatBtn.setVisibility(View.VISIBLE);
                                              chatBtn.setOnClickListener(FriendDetailActivity.this);
                                          } else

                                          {
                                              chatBtn.setVisibility(View.GONE);
                                              addFriendBtn.setVisibility(View.VISIBLE);
                                              addFriendBtn.setOnClickListener(FriendDetailActivity.this);
                                          }
                                      }

                                  }


            );

        }
        updateView();
    }

    private void updateView() {
        ImageLoader.getInstance().displayImage(url, avatarView, PhotoUtils.avatarImageOptions);
        usernameView.setText(username);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.chatBtn:// 发起聊天
                ChatManager chatManager = ChatManager.getInstance();
                chatManager.fetchConversationWithUserId(userId,
                        new AVIMConversationCreatedCallback() {
                            @Override
                            public void done(AVIMConversation conversation,
                                             AVIMException e) {
                                if (e != null) {
                                    Toast.makeText(FriendDetailActivity.this, e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent = new Intent(FriendDetailActivity.this,
                                            ChatRoomActivity.class);

                                    intent.putExtra(Constants.CONVERSATION_ID,
                                            conversation.getConversationId());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, ConversationType.Single.getValue());

                break;
            case R.id.addFriendBtn:// 添加好友
                AVQuery<LeanchatUser> q = AVUser.getQuery(LeanchatUser.class);
                q.whereEqualTo(Constants.OBJECT_ID, userId);
                q.findInBackground(new FindCallback<LeanchatUser>() {
                    @Override
                    public void done(List<LeanchatUser> list, AVException e) {
                        if (null == e) {
                            LeanchatUser finduser = list.get(0);
                            AddRequestManager.getInstance().createAddRequestInBackground(FriendDetailActivity.this, finduser);

                        }

                    }
                });

                break;
        }
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

