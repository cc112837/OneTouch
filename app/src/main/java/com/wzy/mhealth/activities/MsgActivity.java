package com.wzy.mhealth.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMSingleMessageQueryCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.controller.ConversationHelper;
import com.avoscloud.leanchatlib.event.ImTypeMessageEvent;
import com.avoscloud.leanchatlib.model.ConversationType;
import com.avoscloud.leanchatlib.model.Room;
import com.avoscloud.leanchatlib.utils.AVUserCacheUtils;
import com.avoscloud.leanchatlib.utils.Constants;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.LeanChat.adapter.ConversationListAdapter;
import com.wzy.mhealth.LeanChat.event.ConversationItemClickEvent;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MsgActivity extends BaseActivity {
    private TextView titleView;
    View imClientStateView;

    protected ListView listView;
    protected List<Room> list;
    protected ConversationListAdapter itemAdapter;

    private boolean hidden;
    private ConversationManager conversationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        titleView = (TextView) findViewById(R.id.titleView);
        titleView.setText("消息列表");
        back();
        conversationManager = ConversationManager.getInstance();
        //// TODO: 2016/2/22 (修改)
        imClientStateView =View.inflate(getApplicationContext(),R.layout.chat_client_state_view,null);
        listView = (ListView) findViewById(R.id.fragment_conversation_srl_view);
        itemAdapter = new ConversationListAdapter(this);
        listView.setAdapter(itemAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MsgActivity.this).setTitle("你确定删除吗")
                        .setMessage("你确定删除吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChatManager.getInstance().getRoomsTable()
                                .deleteRoom(itemAdapter.getItem(position).getConversationId());
                        itemAdapter.remove(itemAdapter.getItem(position));
                        itemAdapter.notifyDataSetChanged();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return false;
            }
        });

        EventBus.getDefault().register(this);
        updateConversationList();
    }

    private void back() {
        // TODO Auto-generated method stub
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn_back);

        imageback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
        if (!hidden) {
            updateConversationList();
        }
    }

    public void onEvent(ConversationItemClickEvent event) {
        Intent intent = new Intent(MsgActivity.this, ChatRoomActivity.class);
        intent.putExtra(Constants.CONVERSATION_ID, event.conversationId);
        startActivity(intent);
    }

    public void onEvent(ImTypeMessageEvent event) {
        updateConversationList();
    }

    private void updateConversationList() {
        conversationManager.findAndCacheRooms(new Room.MultiRoomsCallback() {
            @Override
            public void done(List<Room> roomList, AVException exception) {
                if (exception == null) {

                    updateLastMessage(roomList);
                    cacheRelatedUsers(roomList);

                    List<Room> sortedRooms = sortRooms(roomList);
                    itemAdapter.clear();
                    itemAdapter.addAll(sortedRooms);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void updateLastMessage(final List<Room> roomList) {
        for (final Room room : roomList) {
            AVIMConversation conversation = room.getConversation();
            if (null != conversation) {
                conversation
                        .getLastMessage(new AVIMSingleMessageQueryCallback() {
                            @Override
                            public void done(AVIMMessage avimMessage,
                                             AVIMException e) {
                                if (e == null&& null != avimMessage) {
                                    room.setLastMessage(avimMessage);
                                    int index = roomList.indexOf(room);
                                    itemAdapter.notifyDataSetChanged();
                                }
                            }
                        });
            }
        }
    }

    private void cacheRelatedUsers(List<Room> rooms) {
        List<String> needCacheUsers = new ArrayList<String>();
        for (Room room : rooms) {
            AVIMConversation conversation = room.getConversation();
            if (ConversationHelper.typeOfConversation(conversation) == ConversationType.Single) {
                needCacheUsers.add(ConversationHelper
                        .otherIdOfConversation(conversation));
            }
        }
        AVUserCacheUtils.cacheUsers(needCacheUsers,
                new AVUserCacheUtils.CacheUserCallback() {
                    @Override
                    public void done(Exception e) {
                        itemAdapter.notifyDataSetChanged();
                    }
                });
    }

    private List<Room> sortRooms(final List<Room> roomList) {
        List<Room> sortedList = new ArrayList<Room>();
        if (null != roomList) {
            sortedList.addAll(roomList);
            Collections.sort(sortedList, new Comparator<Room>() {
                @Override
                public int compare(Room lhs, Room rhs) {
                    long value = lhs.getLastModifyTime()
                            - rhs.getLastModifyTime();
                    if (value > 0) {
                        return -1;
                    } else if (value < 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }
        return sortedList;
    }

    public void onConnectionChanged(boolean connect) {
        imClientStateView.setVisibility(connect ? View.GONE : View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }


}
