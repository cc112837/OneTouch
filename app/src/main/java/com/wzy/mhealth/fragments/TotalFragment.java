package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.RecordListAdapter;
import com.wzy.mhealth.model.ChaTiTime;
import com.wzy.mhealth.model.TestItem;
import com.wzy.mhealth.view.PinnedHeaderExpandableListView;
import com.wzy.mhealth.view.StickyLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener {
    private PinnedHeaderExpandableListView expandableListView;
    private StickyLayout stickyLayout;
    private ArrayList<ChaTiTime> groupList;
    private ArrayList<List<TestItem>> childList;
    private RecordListAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_total, container, false);
        init(v);
        return v;
    }

    private void init(View view) {
        expandableListView = (PinnedHeaderExpandableListView) view.findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout) view.findViewById(R.id.sticky_layout);
        initData();
        adapter = new RecordListAdapter(getActivity(), groupList, childList);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }

    private void initData() {
        groupList = new ArrayList<>();
        ChaTiTime group;
        for (int i = 0; i < 4; i++) {
            group = new ChaTiTime();
            group.setData("报告详情" + i + "条");
            groupList.add(group);
        }

        childList = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<TestItem> childTemp;
            if (i == 0) {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    TestItem people = new TestItem();
                    people.setContent("检查的项目" + j);
                    people.setName("正常");
                    childTemp.add(people);
                }
            } else if (i == 1) {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    TestItem people = new TestItem();
                    people.setContent("检查的项目" + j);
                    people.setName("异常");
                    childTemp.add(people);
                }
            } else {
                childTemp = new ArrayList<>();
                for (int j = 0; j < 13; j++) {
                    TestItem people = new TestItem();
                    people.setContent("检查的项目" + j);
                    people.setName("笨蛋");
                    childTemp.add(people);
                }
            }
            childList.add(childTemp);
        }


    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
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
}
