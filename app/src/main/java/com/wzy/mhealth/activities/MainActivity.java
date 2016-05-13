package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avoscloud.leanchatlib.event.ImTypeMessageEvent;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.model.Room;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;
import com.wzy.mhealth.fragments.ChangeFragmentHelper;
import com.wzy.mhealth.fragments.FriendFragment;
import com.wzy.mhealth.fragments.HomeFragment;
import com.wzy.mhealth.fragments.MyFragment;

import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private RadioButton main_home;
    private RadioButton main_friend;
    private RadioButton main_my;

    private Fragment fragment;
    private TextView  countView;

    private ConversationManager conversationManager = ConversationManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FeedbackAgent agent = new FeedbackAgent(this);
        agent.sync();
        CacheService.registerUser(AVUser.getCurrentUser(LeanchatUser.class));
        Fragment fragment = new HomeFragment();

        ChangeFragmentHelper helper = new ChangeFragmentHelper();
        helper.setTargetFragment(fragment);
        helper.setIsClearStackBack(true);
        changeFragment(helper);
        initView();
        EventBus.getDefault().register(this);
        updateCount();
    }
    private void initView() {
        FrameLayout main_container = ((FrameLayout) findViewById(R.id.main_container));
        countView = (TextView) findViewById(R.id.countView);
        RadioGroup main_tabBar = ((RadioGroup) findViewById(R.id.main_tabBar));
        main_tabBar.check(R.id.main_home);
        fragment = null;
        main_home=(RadioButton) findViewById(R.id.main_home);
        main_friend=(RadioButton) findViewById(R.id.main_friend);
        main_my=(RadioButton) findViewById(R.id.main_my);
        main_tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.main_friend:
                        fragment = new FriendFragment();
                        break;
                    case R.id.main_my:
                        fragment = new MyFragment();
                        break;

                }

                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setIsClearStackBack(true);
                changeFragment(helper);
            }
        });
    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Override
    protected void onResume(){
        super.onResume();
        updateCount();
    }


    public void onEvent(ImTypeMessageEvent event) {
        updateCount();
    }
    public void updateCount() {
        conversationManager.findAndCacheRooms(new Room.MultiRoomsCallback() {
            @Override
            public void done(List<Room> roomList, AVException exception) {
                if (exception == null) {
                    int count = 0;
                    for (Room room : roomList)
                        count += room.getUnreadCount();

                    if (count > 0) {
                        countView.setVisibility(View.VISIBLE);
                        countView.setText("" + count);
                    } else
                        countView.setVisibility(View.GONE);
                }
            }
        });

    }
    public void changeFragment(ChangeFragmentHelper helper) {

        //获取需要跳转的Fragment
        Fragment targetFragment = helper.getTargetFragment();
        //获取是否清空回退栈
        boolean clearStackBack = helper.isClearStackBack();
        //获取是否加入回退栈
        String targetFragmentTag = helper.getTargetFragmentTag();
        //获取Bundle
        Bundle bundle = helper.getBundle();
        //是否给Fragment传值
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (targetFragment != null) {
            fragmentTransaction.replace(R.id.main_container, targetFragment);
        }

        //是否添加到回退栈
        if (targetFragmentTag != null) {
            fragmentTransaction.addToBackStack(targetFragmentTag);
        }

        //是否清空回退栈
        if (clearStackBack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentTransaction.commit();
    }

}
