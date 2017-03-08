package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avoscloud.leanchatlib.event.ImTypeMessageEvent;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.model.Room;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;
import com.wzy.mhealth.fragments.FriendFragment;
import com.wzy.mhealth.fragments.HomeNewFragment;
import com.wzy.mhealth.fragments.MyFragment;
import com.wzy.mhealth.fragments.NewsFragment;
import com.wzy.mhealth.fragments.ShopFragment;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 首页
*/
public class MainActivity extends AppCompatActivity {
    private RadioButton main_home;
    private RadioButton main_friend;
    private RadioButton main_my;
    private RadioButton main_news,main_shop;
    private Fragment homeNewFragment,newsFragment,shopFragment,friendFragment,myFragment;
    private TextView countView;
    private RadioGroup main_tabBar;
    private Fragment fragmentBy;

    public RadioGroup getMain_tabBar() {
        return main_tabBar;
    }

    public void setMain_tabBar(RadioGroup main_tabBar) {
        this.main_tabBar = main_tabBar;
    }

    private ConversationManager conversationManager = ConversationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        FeedbackAgent agent = new FeedbackAgent(this);
        agent.sync();
        CacheService.registerUser(AVUser.getCurrentUser(LeanchatUser.class));
        initView();
        EventBus.getDefault().register(this);
        updateCount();
    }


    private void initView() {
        fragmentBy = getSupportFragmentManager().findFragmentById(
                R.id.main_container);
        countView = (TextView) findViewById(R.id.countView);
        main_tabBar = ((RadioGroup) findViewById(R.id.main_tabBar));
        main_tabBar.check(R.id.main_home);
        main_shop=(RadioButton) findViewById(R.id.main_shop);
        main_home = (RadioButton) findViewById(R.id.main_home);
        main_friend = (RadioButton) findViewById(R.id.main_friend);
        main_my = (RadioButton) findViewById(R.id.main_my);
        main_news = (RadioButton) findViewById(R.id.main_news);
        showHomeFragment();
        main_tabBar.check(R.id.main_home);
        main_tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_home:
                        showHomeFragment();
                        break;
                    case R.id.main_news:
                       showNewsFragment();
                        break;
                    case R.id.main_shop:
                        showShopFragment();
                        break;
                    case R.id.main_friend:
                       showFriendFragment();
                        break;
                    case R.id.main_my:
                        showUserFragment();
                        break;

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
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

    public void showHomeFragment() {
        if (!(fragmentBy instanceof HomeNewFragment)) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            hideFragment(ft);
            if(homeNewFragment == null){
                homeNewFragment = new HomeNewFragment();
                ft.add(R.id.main_container, homeNewFragment);
            }else {
                ft.show(homeNewFragment);
            }
            ft.commit();
        }
    }
    public void showNewsFragment() {

        if (!(fragmentBy instanceof NewsFragment)) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            hideFragment(ft);
            if(newsFragment == null){
                newsFragment = new NewsFragment();
                ft.add(R.id.main_container, newsFragment);
            }else {
                ft.show(newsFragment);
            }
            ft.commit();
        }
    }
    public void showShopFragment() {

        if (!(fragmentBy instanceof ShopFragment)) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            hideFragment(ft);
            if(shopFragment == null){
                shopFragment = new ShopFragment();
                ft.add(R.id.main_container, shopFragment);
            }else {
                ft.show(shopFragment);
            }
            ft.commit();
        }
    }
    public void showFriendFragment() {

        if (!(fragmentBy instanceof FriendFragment)) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            hideFragment(ft);
            if(friendFragment == null){
                friendFragment = new FriendFragment();
                ft.add(R.id.main_container, friendFragment);
            }else {
                ft.show(friendFragment);
            }
            ft.commit();
        }
    }

    public void showUserFragment() {
        if (!(fragmentBy instanceof MyFragment)) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            hideFragment(ft);
            if(myFragment == null){
                myFragment = new MyFragment();
                ft.add(R.id.main_container, myFragment);
            }else {
                ft.show(myFragment);
            }
            ft.commit();
        }
    }

    //隐藏fragment
    public void hideFragment(FragmentTransaction ft){
        if(homeNewFragment != null){
            ft.hide(homeNewFragment);
        }
        if(friendFragment != null){
            ft.hide(friendFragment);
        }
        if(newsFragment != null){
            ft.hide(newsFragment);
        }
        if(shopFragment != null){
            ft.hide(shopFragment);
        }
        if(myFragment != null){
            ft.hide(myFragment);
        }
    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }


}