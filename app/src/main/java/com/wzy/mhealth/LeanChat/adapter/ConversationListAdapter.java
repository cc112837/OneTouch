package com.wzy.mhealth.LeanChat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.controller.ConversationHelper;
import com.avoscloud.leanchatlib.controller.MessageHelper;
import com.avoscloud.leanchatlib.model.ConversationType;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.model.Room;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.LeanChat.event.ConversationItemClickEvent;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by wli on 15/10/8.
 */
public class ConversationListAdapter extends ArrayAdapter<Room> {
    Context ctx;

    public ConversationListAdapter(Context context) {
        super(context, 0);
        this.ctx = context;
    }


    @SuppressLint("NewApi")
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();
        convertView = LayoutInflater.from(ctx).inflate(R.layout.conversation_item,
                null);

        vh.recentAvatarView = (ImageView) convertView
                .findViewById(R.id.iv_recent_avatar);
        vh.recentNameView = (TextView) convertView
                .findViewById(R.id.recent_time_text);
        vh.recentMsgView = (TextView) convertView
                .findViewById(R.id.recent_msg_text);
        vh.recentTimeView = (TextView) convertView
                .findViewById(R.id.recent_teim_text);
        vh.recentUnreadView = (TextView) convertView
                .findViewById(R.id.recent_unread);


        final Room room = getItem(position);
        AVIMConversation conversation = room.getConversation();
        if ( null!=room.getLastMessage()) {
            if (new Date().getTime() - room.getConversation().getCreatedAt().getTime() > 24 * 60 * 60 * 1000) {
                ChatManager.getInstance().getRoomsTable()
                        .deleteRoom(room.getConversationId());
            }
        }

        if (null != conversation) {

            vh.recentNameView.setText(ConversationHelper
                    .nameOfConversation(conversation));
            if (ConversationHelper.typeOfConversation(conversation) == ConversationType.Single) {
                LeanchatUser user = (LeanchatUser) CacheService
                        .lookupUser(ConversationHelper.otherIdOfConversation(conversation));
                if (null != user) {
                    ImageLoader.getInstance().displayImage(user.getAvatarUrl(),
                            vh.recentAvatarView, PhotoUtils.avatarImageOptions);
                    if (user.getUsername().equals("mdoctor"))
                        vh.recentNameView.setText("一点医生");
                }

            } else {
                vh.recentAvatarView.setImageBitmap(ConversationManager
                        .getConversationIcon(conversation));
            }

            int num = room.getUnreadCount();
            if (num > 0) {
                vh.recentUnreadView.setVisibility(View.VISIBLE);
                vh.recentUnreadView.setText(num + "");
            } else {
                vh.recentUnreadView.setVisibility(View.GONE);
            }

            if (room.getLastMessage() != null) {
                Date date = new Date(room.getLastMessage().getTimestamp());
                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
                vh.recentTimeView.setText(format.format(date));
                vh.recentMsgView.setText(MessageHelper
                        .outlineOfMsg((AVIMTypedMessage) room.getLastMessage()));
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ConversationItemClickEvent itemClickEvent = new ConversationItemClickEvent();
                    itemClickEvent.conversationId = room.getConversationId();
                    EventBus.getDefault().post(itemClickEvent);
                }
                // }
            });
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView recentAvatarView;
        TextView recentNameView;
        TextView recentMsgView;
        TextView recentTimeView;
        TextView recentUnreadView;
    }
}
