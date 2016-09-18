package com.wzy.mhealth.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avoscloud.leanchatlib.event.ImTypeMessageEvent;
import com.avoscloud.leanchatlib.model.Room;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wzy.mhealth.LeanChat.service.ConversationManager;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.CaptureActivity;
import com.wzy.mhealth.activities.DoctorDetailActivity;
import com.wzy.mhealth.activities.DoctorListActivity;
import com.wzy.mhealth.activities.JiBingActivity;
import com.wzy.mhealth.activities.MainActivity;
import com.wzy.mhealth.activities.MsgActivity;
import com.wzy.mhealth.activities.NewsDetailActivity;
import com.wzy.mhealth.activities.NoContentActivity;
import com.wzy.mhealth.activities.PersonTaocanActivity;
import com.wzy.mhealth.activities.PoiKeywordSearchActivity;
import com.wzy.mhealth.activities.ScanresultActivity;
import com.wzy.mhealth.activities.TijianOrderActivity;
import com.wzy.mhealth.activities.TijianRecordActivity;
import com.wzy.mhealth.activities.TijianYueActivity;
import com.wzy.mhealth.adapter.NewsItemAdapter;
import com.wzy.mhealth.model.DoctorEntity;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.LocalImageHolderView;
import com.wzy.mhealth.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeNewFragment extends Fragment {
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ConversationManager conversationManager = ConversationManager.getInstance();
    private TextView countView;
    private ListView lv_show;
    private RelativeLayout title1;
    private List<NewsYang.DataEntity.FlowEntity.ItemsEntity> list;
    private NewsItemAdapter adapter;
    private MyScrollView my_scroll;
    private View ll_doc1, ll_doc2, ll_doc3, ll_doc4;
    private List<DoctorEntity> doctorlist;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_home, container, false);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 21:
                    NewsYang newsYang = (NewsYang) msg.obj;
                    for (int i = 0; i < 3; i++) {
                        list.add(newsYang.getData().getFlow().getItems().get(i));
                    }
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    public void initView(View view) {
        loadLocalImage();
        my_scroll = (MyScrollView) view.findViewById(R.id.my_scroll);
        lv_show = (ListView) view.findViewById(R.id.lv_show);
        list = new ArrayList<>();
        adapter = new NewsItemAdapter(getContext(), list);
        lv_show.setAdapter(adapter);
        String url = "http://api.m.vodjk.com/v1/content?channelid=13&height=210.9375&ip=192.168.1.107&modules=flow%3A1%2Cslider%3A1&page=1&pagesize=20&sign=d2397d89e14070110eba3d4c3c46b40f&time=1408764433&token=3&type=android&width=375";
        MyHttpUtils.handData(handler, 21, url, null);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("content", list.get(position - 1).getContentid() + "");
                startActivity(intent);
            }
        });
        title1 = (RelativeLayout) view.findViewById(R.id.title1);
        countView = (TextView) view.findViewById(R.id.countView);
        Button chat_btn = (Button) view.findViewById(R.id.chat_btn);
        Button sacn_btn = (Button) view.findViewById(R.id.sacn_btn);
        View headview = LayoutInflater.from(getContext()).inflate(R.layout.main_home, null);
        lv_show.addHeaderView(headview);
        ImageView doctor_more = (ImageView) headview.findViewById(R.id.doctor_more);
        doctor_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016/9/5 更多医生
                Intent intent = new Intent(getActivity(), DoctorListActivity.class);
                startActivity(intent);
            }
        });
        ll_doc1 = headview.findViewById(R.id.ll_doc1);
        ll_doc2 = headview.findViewById(R.id.ll_doc2);
        ll_doc3 = headview.findViewById(R.id.ll_doc3);
        ll_doc4 = headview.findViewById(R.id.ll_doc4);
        setContent(ll_doc1);
        setContent(ll_doc2);
        setContent(ll_doc3);
        setContent(ll_doc4);
        doctorlist = new ArrayList<>();
        doctorlist.add(new DoctorEntity("邓珊", "liao", "内科", "主任医师",
                "中国人民解放军总医院（301医院）", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺", "5", "5", "15", "9.2"));
        setClickView(ll_doc4);
        setClickView(ll_doc3);
        setClickView(ll_doc2);
        setClickView(ll_doc1);

        LinearLayout ll_record = (LinearLayout) headview.findViewById(R.id.ll_record);
        ll_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TijianRecordActivity.class);
                startActivity(intent);
            }
        });
        ImageView iv_newsmore = (ImageView) headview.findViewById(R.id.iv_newsmore);
        iv_newsmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(new NewsFragment());
                helper.setIsClearStackBack(true);
                ((MainActivity) getActivity()).changeFragment(helper);
                ((MainActivity) getActivity()).getMain_tabBar().check(R.id.main_news);
            }
        });
        LinearLayout ll_yuyue = (LinearLayout) headview.findViewById(R.id.ll_yuyue);
        ll_yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TijianOrderActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout ll_taocan = (LinearLayout) headview.findViewById(R.id.ll_taocan);
        ll_taocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonTaocanActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout ll_near_shop = (LinearLayout) headview.findViewById(R.id.ll_near_shop);
        LinearLayout ll_knownage = (LinearLayout) headview.findViewById(R.id.ll_knownage);
        LinearLayout ll_nearhospital = (LinearLayout) headview.findViewById(R.id.ll_nearhospital);
        LinearLayout ll_servicemore = (LinearLayout) headview.findViewById(R.id.ll_servicemore);
        ll_knownage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JiBingActivity.class);
                startActivity(intent);
            }
        });
        ll_nearhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 附近医院
                Intent intent = new Intent();
                intent.setClass(getActivity(), PoiKeywordSearchActivity.class);
                intent.putExtra("keyword", "医院");
                getActivity().startActivity(intent);
            }
        });
        ll_servicemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoContentActivity.class);
                startActivity(intent);
            }
        });
        ll_near_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 附近药房
                Intent intent = new Intent();
                intent.setClass(getActivity(), PoiKeywordSearchActivity.class);
                intent.putExtra("keyword", "药房");
                getActivity().startActivity(intent);
            }
        });
        LinearLayout ll_group = (LinearLayout) headview.findViewById(R.id.ll_group);
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), TijianYueActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout ll_private = (LinearLayout) headview.findViewById(R.id.ll_private);
        ll_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        convenientBanner = (ConvenientBanner) headview.findViewById(R.id.convenientBanner);
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
        chat_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 右上角聊天按钮
                Intent intent = new Intent(getActivity(), MsgActivity.class);
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

    }

    public void setClickView(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DoctorDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", doctorlist.get(0));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void setContent(View v) {
        TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
        TextView tv_pre = (TextView) v.findViewById(R.id.tv_pre);
        TextView tv_hos = (TextView) v.findViewById(R.id.tv_hosi);
        tv_name.setText("邓珊");
        tv_pre.setText("主任医生");
        tv_hos.setText("内科");
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
        AVAnalytics.onFragmentStart("home-list-fragment");
        //开始自动翻页
        updateCount();
        convenientBanner.startTurning(2000);
    }


    // 停止自动翻页

    @Override

    public void onPause() {

        super.onPause();
        AVAnalytics.onFragmentEnd("home-list-fragment");
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