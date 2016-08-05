package com.wzy.mhealth.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFriendship;
import com.avos.avoscloud.AVFriendshipQuery;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.callback.AVFriendshipCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.Constants;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.LeanChat.adapter.ContactsAdapter;
import com.wzy.mhealth.LeanChat.event.ContactItemClickEvent;
import com.wzy.mhealth.LeanChat.event.ContactItemLongClickEvent;
import com.wzy.mhealth.LeanChat.event.ContactRefreshEvent;
import com.wzy.mhealth.LeanChat.event.InvitationEvent;
import com.wzy.mhealth.LeanChat.event.MemberLetterEvent;
import com.wzy.mhealth.LeanChat.service.AddRequestManager;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.NewFriendActivity;
import com.wzy.mhealth.activities.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends D3Fragment {

    protected ImageView rightBtn;
    protected Context ctx;
    protected SwipeRefreshLayout refreshLayout;

    protected RecyclerView recyclerView;

    private View headerView;
    ImageView msgTipsView;

    private ContactsAdapter itemAdapter;
    LinearLayoutManager layoutManager;

    private Handler handler = new Handler(Looper.getMainLooper());
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd("friend-list-fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ctx = getActivity();
        View view = setContentView(inflater, R.layout.fragment_friend);

        headerView = inflater.inflate(R.layout.contact_fragment_header_layout,
                container, false);
        rightBtn = (ImageView) view.findViewById(R.id.rightBtn);

        refreshLayout = (SwipeRefreshLayout) view
                .findViewById(R.id.activity_square_members_srl_list);

        recyclerView = (RecyclerView) view
                .findViewById(R.id.activity_square_members_rv_list);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        itemAdapter = new ContactsAdapter();
        itemAdapter.setHeaderView(headerView);
        recyclerView.setAdapter(itemAdapter);
        refreshLayout
                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myGetMenber();
                    }
                });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHeaderView();
        refresh();
        EventBus.getDefault().register(this);
        myGetMenber();
        rightBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void myGetMenber() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AVFriendshipQuery query = AVUser
                            .friendshipQuery(AVUser.getCurrentUser()
                                    .getObjectId(), LeanchatUser.class);
                    query.include("followee");
                    query.include("follower");
                    query.getInBackground(new AVFriendshipCallback() {

                        @Override
                        public void done(AVFriendship friendship, AVException e) {
                            if (e == null) {
                                List<LeanchatUser> followers = friendship
                                        .getFollowers(); // 获取粉丝
                                List<LeanchatUser> followees = friendship
                                        .getFollowees(); // 获取关注列表
                                AVUser user = friendship.getUser(); // 获取用户对象本身
                                final List<LeanchatUser> users = new ArrayList<LeanchatUser>();
                                List<String> Ids = new ArrayList<String>();
                                // if(followees)
                                for (LeanchatUser u : followees)
                                    if (followees.contains(u)) {
                                        Ids.add(u.getObjectId());
                                        users.add(u);
                                    }
                                CacheService.setFriendIds(Ids);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        refreshLayout.setRefreshing(false);
                                        itemAdapter.setUserList(users);
                                        itemAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart("friend-list-fragment");
        updateNewRequestBadge();
    }

    private void initHeaderView() {
        msgTipsView = (ImageView) headerView.findViewById(R.id.iv_msg_tips);
        View newView = headerView.findViewById(R.id.layout_new);
        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, NewFriendActivity.class);
                ctx.startActivity(intent);
            }
        });


        View groupView = headerView.findViewById(R.id.layout_group);
        groupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                intent.putExtra(Constants.MEMBER_ID, "567461f5ddb2084a556f7010");
                startActivity(intent);
            }
        });
    }

    private void updateNewRequestBadge() {
        msgTipsView.setVisibility(AddRequestManager.getInstance()
                .hasUnreadRequests() ? View.VISIBLE : View.GONE);
    }

    private void refresh() {
        AddRequestManager.getInstance().countUnreadRequests(
                new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        updateNewRequestBadge();
                    }
                });
    }

    public void showDeleteDialog(final String memberId) {
        new AlertDialog.Builder(ctx)
                .setMessage(R.string.contact_deleteContact)
                .setPositiveButton(R.string.common_sure,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                final ProgressDialog dialog1 = showSpinnerDialog();
                                AVUser.getCurrentUser(LeanchatUser.class)
                                        .removeFriend(memberId,
                                                new SaveCallback() {
                                                    @Override
                                                    public void done(
                                                            AVException e) {
                                                        dialog1.dismiss();
                                                        if (e == null) {
                                                            myGetMenber();
                                                        }
                                                    }
                                                });

                            }
                        }).setNegativeButton(R.string.chat_common_cancel, null)
                .show();
    }

    public static List<LeanchatUser> findFriends(boolean isforce)
            throws Exception {
        final List<LeanchatUser> friends = new ArrayList<LeanchatUser>();
        final AVException[] es = new AVException[1];
        final CountDownLatch latch = new CountDownLatch(1);
        LeanchatUser.getCurrentUser(LeanchatUser.class)
                .findFriendsWithCachePolicy(
                        isforce ? AVQuery.CachePolicy.NETWORK_ELSE_CACHE
                                : AVQuery.CachePolicy.CACHE_ELSE_NETWORK,
                        new FindCallback<LeanchatUser>() {
                            @Override
                            public void done(List<LeanchatUser> avUsers,
                                             AVException e) {
                                if (e != null) {
                                    es[0] = e;
                                } else {
                                    friends.addAll(avUsers);
                                }
                                latch.countDown();
                            }
                        });
        latch.await();
        if (es[0] != null) {
            throw es[0];
        } else {
            List<String> userIds = new ArrayList<String>();
            for (AVUser user : friends) {
                userIds.add(user.getObjectId());
            }
            CacheService.setFriendIds(userIds);
            CacheService.cacheUsers(userIds);
            List<LeanchatUser> newFriends = new ArrayList<LeanchatUser>();
            for (LeanchatUser user : friends) {
                newFriends.add(CacheService.lookupUser(user.getObjectId()));
            }
            return newFriends;
        }
    }

    public void onEvent(ContactRefreshEvent event) {
        myGetMenber();
    }

    public void onEvent(InvitationEvent event) {
        AddRequestManager.getInstance().unreadRequestsIncrement();
        updateNewRequestBadge();
    }

    public void onEvent(ContactItemClickEvent event) {
        final ChatManager chatManager = ChatManager.getInstance();
        chatManager.fetchConversationWithUserId(event.memberId,
                new AVIMConversationCreatedCallback() {
                    @Override
                    public void done(AVIMConversation conversation,
                                     AVIMException e) {
                        if (e != null) {
                            Toast.makeText(getActivity(), e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(getActivity(),
                                    ChatRoomActivity.class);

                            intent.putExtra(Constants.CONVERSATION_ID,
                                    conversation.getConversationId());
                            startActivity(intent);
                        }
                    }
                });

    }

    public void onEvent(ContactItemLongClickEvent event) {
        showDeleteDialog(event.memberId);
    }

    /**
     * 处理 LetterView 发送过来的 MemberLetterEvent 会通过 MembersAdapter 获取应该要跳转到的位置，然后跳转
     */
    public void onEvent(MemberLetterEvent event) {
        Character targetChar = Character.toLowerCase(event.letter);
        if (itemAdapter.getIndexMap().containsKey(targetChar)) {
            int index = itemAdapter.getIndexMap().get(targetChar);
            if (index > 0 && index < itemAdapter.getItemCount()) {
                layoutManager.scrollToPositionWithOffset(index, 0);
            }
        }
    }

    protected ProgressDialog showSpinnerDialog() {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.chat_utils_hardLoading));
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

}
