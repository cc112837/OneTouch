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

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.RecordShowActivity;
import com.wzy.mhealth.adapter.RecordListAdapter;
import com.wzy.mhealth.model.ChaTiTime;
import com.wzy.mhealth.model.HuaRecord;
import com.wzy.mhealth.model.NoRecord;
import com.wzy.mhealth.model.TestItem;
import com.wzy.mhealth.utils.MyHttpUtils;
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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List childTemp;
            switch (msg.what) {
                case 14:
                    HuaRecord huaRecord = (HuaRecord) msg.obj;
                    //正常值  huaRecord.getRows().get(0).getDEFVALUE();
                    //分区名称huaRecord.getRows().get(i).getXMMC()
                    childTemp = new ArrayList<>();
                    for (int i = 0; i < huaRecord.getRows().size(); i++) {
                        TestItem people = new TestItem();
                        people.setUsusl(huaRecord.getRows().get(i).getDEFVALUE());
                        people.setName(huaRecord.getRows().get(i).getCNAME() + "(" + huaRecord.getRows().get(0).getUNIT() + ")");
                        people.setContent(huaRecord.getRows().get(i).getHYRESULT());
                        childTemp.add(people);
                    }
                    childList.add(childTemp);
                    break;
                case 15:
                    NoRecord noRecord = (NoRecord) msg.obj;
                    //分区名称noRecord.getRows().get(i).getKSMC()
                    childTemp = new ArrayList<>();
                    for (int j = 0; j < noRecord.getRows().size(); j++) {
                        TestItem peopl = new TestItem();
                        peopl.setName(noRecord.getRows().get(j).getCNAME());
                        peopl.setContent(noRecord.getRows().get(j).getMVALUE());
                        childTemp.add(peopl);
                    }
                    childList.add(childTemp);
                    adapter = new RecordListAdapter(getActivity(), groupList, childList);
                    expandableListView.setAdapter(adapter);
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_total, container, false);
        init(v);
        return v;
    }

    private void init(View view) {
        expandableListView = (PinnedHeaderExpandableListView) view.findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout) view.findViewById(R.id.sticky_layout);
        initData();
//        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }

    private void initData() {
        String huaurl = "http://113.201.59.226:8081/Healwis/base/reportAction!app_assay.action?sessid=" + ((RecordShowActivity) getActivity()).getSession() + "&studyid=" + ((RecordShowActivity) getActivity()).getStudid();
        String nourl = "http://113.201.59.226:8081/Healwis/base/reportAction!app_result.action?sessid=" + ((RecordShowActivity) getActivity()).getSession() + "&studyid=" + ((RecordShowActivity) getActivity()).getStudid();
        ChaTiTime group,group1;
        groupList = new ArrayList<>();
        group = new ChaTiTime();
        group.setData("化验区");
        groupList.add(group);
        group1 = new ChaTiTime();
        group1.setData("非化验区");
        groupList.add(group1);
        childList = new ArrayList<>();
        MyHttpUtils.handData(handler, 14, huaurl, null);
        MyHttpUtils.handData(handler, 15, nourl, null);
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
//        ChaTiTime firstVisibleGroup = (ChaTiTime) adapter.getGroup(firstVisibleGroupPos);
//        TextView textView = (TextView) headerView.findViewById(R.id.tv_time);
//        textView.setText(firstVisibleGroup.getData());
    }
}
