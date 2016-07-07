package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.MyYuyueActivity;
import com.wzy.mhealth.adapter.MyexpandableListAdapter;
import com.wzy.mhealth.model.ChaTiContent;
import com.wzy.mhealth.model.ChaTiTime;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.PinnedHeaderExpandableListView;
import com.wzy.mhealth.view.StickyLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XianChaFragment extends Fragment implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener {
    private PinnedHeaderExpandableListView expandableListView;
    private StickyLayout stickyLayout;
    ArrayList<ChaTiContent> childTemp;
    private ArrayList<ChaTiTime> groupList;
    private ArrayList<List<ChaTiContent>> childList;
    private MyexpandableListAdapter adapter;
    String sessid, recordid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xian_cha, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        expandableListView = (PinnedHeaderExpandableListView) view.findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout) view.findViewById(R.id.sticky_layout);
        initData();
        adapter = new MyexpandableListAdapter(getActivity(), groupList, childList);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return true;
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.datatime, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        ChaTiTime firstVisibleGroup = (ChaTiTime) adapter.getGroup(firstVisibleGroupPos);
        TextView textView = (TextView) headerView.findViewById(R.id.tv_time);
        textView.setText(firstVisibleGroup.getData());
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 6:
                    ItemInfo info = (ItemInfo) msg.obj;
                    for(int i=0;i<info.getRows().size();i++){
                        ChaTiContent content = new ChaTiContent();
                        content.setId(info.getRows().get(i).getID());
                        content.setItemcode(info.getRows().get(i).getITEMCODE());
                        content.setStuyid(info.getRows().get(i).getSTUDYID());
                        content.setItemname(info.getRows().get(i).getXMMC());
                        childTemp.add(content);
                    }

                    childList.add(childTemp);
            }
        }
    };

    void initData() {
        groupList = new ArrayList<>();
        ChaTiTime group;
        group = new ChaTiTime();
        group.setData("体检的时间2016-07-20");
        groupList.add(group);

        childList = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (i == 0) {
                childTemp = new ArrayList<>();
                sessid = ((MyYuyueActivity) getActivity()).getSession();
                recordid = ((MyYuyueActivity) getActivity()).getId();
                if ("null".equals(recordid)) {
                    Toast.makeText(getActivity(), "请先进行体检预约", 2000).show();
                } else {
                    String itemurl = "http://113.201.59.226:8081/Healwis/base/itemAction!app_jcxm.action?sessid=" + sessid + "&id=" + recordid;
                    MyHttpUtils.handData(handler, 6, itemurl, null);

                }
            }
        }

    }
}
