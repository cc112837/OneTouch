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
import android.widget.GridView;
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
import com.wzy.mhealth.activities.DiseaseActivity;
import com.wzy.mhealth.activities.DoctorDetailActivity;
import com.wzy.mhealth.activities.DoctorLiActivity;
import com.wzy.mhealth.activities.DoctorListActivity;
import com.wzy.mhealth.activities.ExaminationYueActivity;
import com.wzy.mhealth.activities.HospitalActivity;
import com.wzy.mhealth.activities.MsgActivity;
import com.wzy.mhealth.activities.NoContentActivity;
import com.wzy.mhealth.activities.PrivaDoctorActivity;
import com.wzy.mhealth.activities.QuestionActivity;
import com.wzy.mhealth.activities.ScanresultActivity;
import com.wzy.mhealth.activities.ServiceMoreActivity;
import com.wzy.mhealth.activities.SlowAidActivity;
import com.wzy.mhealth.activities.TaocanDetailAcitivty;
import com.wzy.mhealth.activities.TaocanListActivity;
import com.wzy.mhealth.adapter.DoctorHomeAdapter;
import com.wzy.mhealth.adapter.TaocanHomeAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.BannerItem;
import com.wzy.mhealth.model.Doctor;
import com.wzy.mhealth.model.TaocanEntity;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.LocalImageHolderView;
import com.wzy.mhealth.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 首页
 */
public class HomeNewFragment extends Fragment implements View.OnClickListener {
    private ConvenientBanner convenientBanner;
    private ArrayList<BannerItem> localImages = new ArrayList<>();
    private ConversationManager conversationManager = ConversationManager.getInstance();
    private TextView countView;
    private ListView lv_show;
    private GridView gv_doctor;
    private RelativeLayout title1;
    private MyScrollView my_scroll;

    private List<TaocanEntity.DataEntity> taocanEntitylist = new ArrayList<>();
    private List<Doctor.DataEntity> doctorEntitylist = new ArrayList<>();
    private DoctorHomeAdapter doctorHomeAdapter;
    private TaocanHomeAdapter taocanHomeAdapter;
    private LinearLayout ll_private;
    private LinearLayout ll_group;
    private LinearLayout ll_marry;
    private LinearLayout ll_heali;
    private LinearLayout ll_taocan;
    private LinearLayout ll_taocanmore;
    private LinearLayout ll_near_shop;
    private LinearLayout ll_knownage;
    private LinearLayout ll_nearhospital;
    private LinearLayout ll_servicemore;

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


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 152:
                    final Doctor doctor = (Doctor) msg.obj;
                    if (doctor.getData().size() > 4) {
                        for (int i = 0; i <= 3; i++)
                            doctorEntitylist.add(doctor.getData().get(i));
                    } else {
                        doctorEntitylist.addAll(doctor.getData());
                    }
                    doctorHomeAdapter.notifyDataSetChanged();
                    gv_doctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent();
                            if ("0".equals(doctor.getData().get(position).getId())) {
                                intent.setClass(getActivity(), NoContentActivity.class);
                            } else {
                                intent.setClass(getActivity(), DoctorDetailActivity.class);
                                intent.putExtra("id", doctor.getData().get(position).getDoctorId() + "");
                                intent.putExtra("doctor", doctor.getData().get(position).getId() + "");
                            }
                            startActivity(intent);
                        }
                    });
                    break;
                case 173:
                    TaocanEntity taocanEntity = (TaocanEntity) msg.obj;
                    if (taocanEntity != null) {
                        taocanEntitylist.clear();
                        taocanEntitylist.addAll(taocanEntity.getData());
                        taocanHomeAdapter.notifyDataSetChanged();
                    }
                    break;
                case 174:
                    TaocanEntity taocan = (TaocanEntity) msg.obj;
                    for (int i = 0; i < taocan.getData().size(); i++) {
                        BannerItem bannerItem = new BannerItem();
                        bannerItem.setTitle(taocan.getData().get(i).getName() + "");
                        bannerItem.setUrl(taocan.getData().get(i).getImg() + "");
                        bannerItem.setId(taocan.getData().get(i).getTaoId() + "");
                        localImages.add(bannerItem);
                    }
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
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);


                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), TaocanDetailAcitivty.class);
                            intent.putExtra("id", taocanEntitylist.get(position - 1).getTaoId() + "");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    public void initView(View view) {
        my_scroll = (MyScrollView) view.findViewById(R.id.my_scroll);
        lv_show = (ListView) view.findViewById(R.id.lv_show);
        View headview = LayoutInflater.from(getContext()).inflate(R.layout.main_home, null);
        convenientBanner = (ConvenientBanner) headview.findViewById(R.id.convenientBanner);
        taocanHomeAdapter = new TaocanHomeAdapter(getContext(), taocanEntitylist);
        lv_show.addHeaderView(headview);
        lv_show.setAdapter(taocanHomeAdapter);
        title1 = (RelativeLayout) view.findViewById(R.id.title1);
        countView = (TextView) view.findViewById(R.id.countView);
        Button chat_btn = (Button) view.findViewById(R.id.chat_btn);
        Button sacn_btn = (Button) view.findViewById(R.id.sacn_btn);
        LinearLayout doctor_more = (LinearLayout) headview.findViewById(R.id.doctor_more);
        doctor_more.setOnClickListener(this);
        gv_doctor = (GridView) headview.findViewById(R.id.gv_doctor);
        doctorHomeAdapter = new DoctorHomeAdapter(getContext(), doctorEntitylist);
        gv_doctor.setAdapter(doctorHomeAdapter);
        ll_marry = (LinearLayout) headview.findViewById(R.id.ll_marry);
        ll_marry.setOnClickListener(this);
        ll_heali = (LinearLayout) headview.findViewById(R.id.ll_heali);
        ll_heali.setOnClickListener(this);
        ll_taocan = (LinearLayout) headview.findViewById(R.id.ll_taocan);
        ll_taocan.setOnClickListener(this);
        ll_taocanmore = (LinearLayout) headview.findViewById(R.id.ll_taocanmore);
        ll_taocanmore.setOnClickListener(this);
        ll_near_shop = (LinearLayout) headview.findViewById(R.id.ll_near_shop);
        ll_knownage = (LinearLayout) headview.findViewById(R.id.ll_knownage);
        ll_nearhospital = (LinearLayout) headview.findViewById(R.id.ll_nearhospital);
        ll_servicemore = (LinearLayout) headview.findViewById(R.id.ll_servicemore);
        ll_knownage.setOnClickListener(this);
        ll_nearhospital.setOnClickListener(this);
        ll_servicemore.setOnClickListener(this);
        ll_near_shop.setOnClickListener(this);
        ll_group = (LinearLayout) headview.findViewById(R.id.ll_group);
        ll_group.setOnClickListener(this);
        ll_private = (LinearLayout) headview.findViewById(R.id.ll_private);
        ll_private.setOnClickListener(this);
        my_scroll.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    title1.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= 500) {
                    float scale = (float) y / 500;
                    float alpha = (255 * scale);
                    title1.setBackgroundColor(Color.argb((int) alpha, 2, 185, 157));
                } else {
                    title1.setBackgroundColor(Color.argb((int) 255, 2, 185, 157));
                }
            }

        });
        chat_btn.setOnClickListener(this);
        sacn_btn.setOnClickListener(this);
        String homeurl = Constants.SERVER_URL + "TaoCanManyServlet";
        MyHttpUtils.handData(handler, 174, homeurl, null);
        MyHttpUtils.handData(handler, 173, homeurl, null);
        String ur = Constants.SERVER_URL + "MhealthDoctorOrderServlet";
        TiUser user = new TiUser();
        user.setName("");
        user.setPass("");
        MyHttpUtils.handData(handler, 152, ur, user);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sacn_btn:
                //二维码
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.chat_btn:
                //聊天
                Intent intent1 = new Intent(getActivity(), MsgActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_private:
                //私人医生
                Intent intent2 = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_group:
                //团体体检
                Intent intent3 = new Intent(getActivity(), ExaminationYueActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_near_shop:
                // 慢病诊疗
                Intent intent21 = new Intent(getActivity(), SlowAidActivity.class);
                startActivity(intent21);
                break;
            case R.id.ll_marry:
                //婴幼中心  MarryHospitalActivity
//                Intent intent5 = new Intent(getActivity(), MarryHospitalActivity.class);
//                startActivity(intent5);
                //找医院
                Intent intent5 = new Intent(getActivity(), HospitalActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_heali:
                //李医生 DoctorLiActivity
                Intent intent6 = new Intent(getActivity(), DoctorLiActivity.class);
                startActivity(intent6);
                break;
            case R.id.ll_taocan:
                //套餐列表
                Intent intent7 = new Intent(getActivity(), TaocanListActivity.class);
                startActivity(intent7);
                break;
            case R.id.ll_servicemore:
                //更多服务
                Intent intent8 = new Intent(getActivity(), ServiceMoreActivity.class);
                startActivity(intent8);
                break;
            case R.id.ll_taocanmore:
                //套餐列表
                Intent intent9 = new Intent(getActivity(), TaocanListActivity.class);
                startActivity(intent9);
                break;
            case R.id.ll_knownage:
                //急救知识
                Intent intentt0 = new Intent(getActivity(), DiseaseActivity.class);
                startActivity(intentt0);
                break;
            case R.id.ll_nearhospital:
                //私人医生
                Intent intent4 = new Intent(getActivity(), PrivaDoctorActivity.class);
                startActivity(intent4);
                break;
            case R.id.doctor_more:
                //更多医生
                Intent intent23 = new Intent(getActivity(), DoctorListActivity.class);
                intent23.putExtra("flag","new");
                startActivity(intent23);
                break;

        }

    }
}