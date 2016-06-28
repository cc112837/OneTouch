package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.MyexpandableListAdapter;
import com.wzy.mhealth.model.ChaTiContent;
import com.wzy.mhealth.model.ChaTiTime;
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
    private ArrayList<ChaTiTime> groupList;
    private ArrayList<List<ChaTiContent>> childList;
    private MyexpandableListAdapter adapter;

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
        return null;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {

    }

    void initData() {
        groupList = new ArrayList<>();
        ChaTiTime group;
        for (int i = 0; i < 4; i++) {
            group = new ChaTiTime();
            group.setData("体检的时间是第" + i + "次");
            groupList.add(group);
        }

        childList = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<ChaTiContent> childTemp;
            if (i == 0) {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    ChaTiContent people = new ChaTiContent();
                    people.setState(false);
                    people.setItemname("检查项目" + j);
                    people.setWaitcount("剩余22人");
                    people.setName("姓名：哈哈");
                    people.setSex("性别：男");
                    childTemp.add(people);
                }
            } else if (i == 1) {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    ChaTiContent people = new ChaTiContent();
                    people.setState(true);
                    people.setName("姓名：嘻嘻");
                    people.setSex("性别：女");
                    people.setItemname("检查项目" + j);
                    people.setWaitcount("评价");
                    childTemp.add(people);
                }
            } else {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 13; j++) {
                    ChaTiContent people = new ChaTiContent();
                    people.setState(true);
                    people.setName("姓名：猜的");
                    people.setSex("性别：男");
                    people.setItemname("检查项目" + j);
                    people.setWaitcount("剩余12人");
                    childTemp.add(people);
                }
            }
            childList.add(childTemp);
        }

    }
}
