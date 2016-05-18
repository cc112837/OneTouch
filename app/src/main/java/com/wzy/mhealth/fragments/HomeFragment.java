package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avoscloud.leanchatlib.event.ImTypeMessageEvent;
import com.avoscloud.leanchatlib.model.Room;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.BingliActivity;
import com.wzy.mhealth.activities.CaptureActivity;
import com.wzy.mhealth.activities.GreenPassActivity;
import com.wzy.mhealth.activities.GuahaoActivity;
import com.wzy.mhealth.activities.JiBingActivity;
import com.wzy.mhealth.activities.JianyanBaogaoActivity;
import com.wzy.mhealth.activities.MsgActivity;
import com.wzy.mhealth.activities.NoContentActivity;
import com.wzy.mhealth.activities.PoiKeywordSearchActivity;
import com.wzy.mhealth.activities.RouteActivity;
import com.wzy.mhealth.activities.ScanresultActivity;
import com.wzy.mhealth.activities.YuyueListActivity;
import com.wzy.mhealth.activities.ZixunActivity;
import com.wzy.mhealth.view.LocalImageHolderView;
import com.wzy.mhealth.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ConversationManager conversationManager = ConversationManager.getInstance();
    private TextView countView;
    private RelativeLayout title1;
    private MyScrollView my_scroll;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        EventBus.getDefault().register(this);
        updateCount();
        return view;
    }


    private void loadLocalImage() {
        localImages.add(R.mipmap.carouse1);
        localImages.add(R.mipmap.carouse2);
        localImages.add(R.mipmap.carouse3);
        localImages.add(R.mipmap.carouse4);
    }

    public void initView(View view) {
        loadLocalImage();
        my_scroll = (MyScrollView) view.findViewById(R.id.my_scroll);
        title1 = (RelativeLayout) view.findViewById(R.id.title1);
        countView = (TextView) view.findViewById(R.id.countView);
        Button chat_btn = (Button) view.findViewById(R.id.chat_btn);
        Button sacn_btn = (Button) view.findViewById(R.id.sacn_btn);
        Button wodeyuyue_btn = (Button) view.findViewById(R.id.mode1);
        Button dianzibingli_btn = (Button) view.findViewById(R.id.mode41);
        Button jianyanbaogao_btn = (Button) view.findViewById(R.id.mode3);
        Button jibingku_btn = (Button) view.findViewById(R.id.mode4);
        Button menzhen = (Button) view.findViewById(R.id.mode2);
        Button tijian = (Button) view.findViewById(R.id.mode51);
        Button fujinyuyuan_btn = (Button) view.findViewById(R.id.mode5);
        Button fujinyaodian_btn = (Button) view.findViewById(R.id.mode6);
        Button jiuzhenzhuanche_btn = (Button) view.findViewById(R.id.mode61);
        LinearLayout ll_speci = (LinearLayout) view.findViewById(R.id.ll_speci);
        RadioButton rb_yuyue = (RadioButton) view.findViewById(R.id.rb_yuyue);
        RadioButton rb_select = (RadioButton) view.findViewById(R.id.rb_select);
        RadioButton rb_quike = (RadioButton) view.findViewById(R.id.rb_quike);
        my_scroll.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    title1.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= 50) {
                    float scale = (float) y / 50;
                    float alpha = (255 * scale);
                    title1.setBackgroundColor(Color.argb((int) alpha, 2, 185, 157));
                } else {
                    title1.setBackgroundColor(Color.argb((int) 255, 2, 185, 157));
                }
            }

        });
        convenientBanner = ((ConvenientBanner) view.findViewById(R.id.convenientBanner));
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.dots_gray, R.mipmap.dot_white})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        jiuzhenzhuanche_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RouteActivity.class);
                startActivity(intent);
            }
        });
        rb_yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GuahaoActivity.class);
                startActivity(intent);

            }
        });
        rb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZixunActivity.class);
                startActivity(intent);
            }
        });
        rb_quike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PoiKeywordSearchActivity.class);
                intent.putExtra("keyword", "急救");
                getActivity().startActivity(intent);
            }
        });
        menzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //门诊缴费
                Intent intent = new Intent(getActivity(), NoContentActivity.class);
                startActivity(intent);
            }
        });
        tijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //体检预约
                Intent intent = new Intent(getActivity(), NoContentActivity.class);
                startActivity(intent);
            }
        });
        chat_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 右上角聊天按钮
                Intent intent = new Intent(getActivity(), MsgActivity.class);
                startActivity(intent);
            }

        });
        jibingku_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //疾病库页面
                Intent intent = new Intent(getActivity(), JiBingActivity.class);
                startActivity(intent);
            }
        });
        sacn_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 左上角扫描按钮
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }

        });
        wodeyuyue_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 我的预约
                Intent intent = new Intent();
                intent.setClass(getActivity(), YuyueListActivity.class);
                getActivity().startActivity(intent);
            }

        });
        ll_speci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GreenPassActivity.class);
                startActivity(intent);
            }
        });
        dianzibingli_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 电子病历
                Intent intent = new Intent(getActivity(), BingliActivity.class);
                startActivity(intent);
            }

        });
        jianyanbaogao_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 检验报告
                Intent intent = new Intent();
                intent.setClass(getActivity(), JianyanBaogaoActivity.class);
                getActivity().startActivity(intent);
            }

        });
        fujinyuyuan_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 附近医院
                Intent intent = new Intent();
                intent.setClass(getActivity(), PoiKeywordSearchActivity.class);
                intent.putExtra("keyword", "医院");
                getActivity().startActivity(intent);

            }

        });
        fujinyaodian_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 附近药房
                Intent intent = new Intent();
                intent.setClass(getActivity(), PoiKeywordSearchActivity.class);
                intent.putExtra("keyword", "药房");
                getActivity().startActivity(intent);

            }

        });

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 200) {
                String num = data.getStringExtra("result");
                Intent intent = null;
                if (isValidURI(num)) {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(num);
                    intent.setData(content_url);
                } else {
                    intent = new Intent(getActivity(),
                            ScanresultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("num", num);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            } else if (resultCode == 0) {
            }
        } else {
            return;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    // 开始自动翻页

    @Override

    public void onResume() {

        super.onResume();

        //开始自动翻页
        updateCount();
        convenientBanner.startTurning(2000);
    }


    // 停止自动翻页

    @Override

    public void onPause() {

        super.onPause();

        //停止翻页
        convenientBanner.stopTurning();

    }

    public static boolean isValidURI(String uri) {
        if (uri == null || uri.indexOf(' ') >= 0 || uri.indexOf('\n') >= 0) {
            return false;
        }
        String scheme = Uri.parse(uri).getScheme();
        if (scheme == null) {
            return false;
        }

        // Look for period in a domain but followed by at least a two-char TLD
        // Forget strings that don't have a valid-looking protocol
        int period = uri.indexOf('.');
        if (period >= uri.length() - 2) {
            return false;
        }
        int colon = uri.indexOf(':');
        if (period < 0 && colon < 0) {
            return false;
        }
        if (colon >= 0) {
            if (period < 0 || period > colon) {
                // colon ends the protocol
                for (int i = 0; i < colon; i++) {
                    char c = uri.charAt(i);
                    if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                        return false;
                    }
                }
            } else {
                // colon starts the port; crudely look for at least two numbers
                if (colon >= uri.length() - 2) {
                    return false;
                }
                for (int i = colon + 1; i < colon + 3; i++) {
                    char c = uri.charAt(i);
                    if (c < '0' || c > '9') {
                        return false;
                    }
                }
            }
        }
        return true;
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

}