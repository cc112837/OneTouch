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
import com.wzy.mhealth.model.HuaYanRecord;
import com.wzy.mhealth.model.NoHuaRecord;
import com.wzy.mhealth.model.SubjectTest;
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
    private ArrayList<TestItem> collist;
    private RecordListAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 14:
                    NoHuaRecord noHuaRecord = (NoHuaRecord) msg.obj;
                    collist=new ArrayList<>();
                    for (int i = 0; i < noHuaRecord.getObj().size(); i++) {
                       String  s= noHuaRecord.getObj().get(i).getKSMC();
                        List<SubjectTest> list=new ArrayList<>();
                        for (int j=0;j<noHuaRecord.getObj().get(i).getRESULT().size();j++){
                            String name=noHuaRecord.getObj().get(i).getRESULT().get(j).getCOMPONENTNAME();
                            String value=noHuaRecord.getObj().get(i).getRESULT().get(j).getCVALUE();
                            SubjectTest subjectTest=new SubjectTest(name,value);
                            list.add(subjectTest);
                        }
                        TestItem testItem=new TestItem(s,list);
                        collist.add(testItem);
                    }
                    String huaurl = "http://113.201.59.226:8081/Healwis/base/reportAction!app_assayX.action?sessid=" + ((RecordShowActivity) getActivity()).getSession() + "&studyid=" + ((RecordShowActivity) getActivity()).getStudid();
                    MyHttpUtils.handData(handler, 15, huaurl, null);
                    break;
                case 15:
                    HuaYanRecord huaYanRecord = (HuaYanRecord) msg.obj;
                    for (int i = 0; i < huaYanRecord.getObj().size(); i++) {
                        String s= huaYanRecord.getObj().get(i).getITEMNAME();
                        List<SubjectTest> list=new ArrayList<>();
                        for (int j=0;j<huaYanRecord.getObj().get(i).getRESULT().size();j++){
                            String name=huaYanRecord.getObj().get(i).getRESULT().get(j).getCOMPONENTNAME();
                            String value=huaYanRecord.getObj().get(i).getRESULT().get(j).getHYRESULT();
                            String deau=huaYanRecord.getObj().get(i).getRESULT().get(j).getDEFVALUE();
                            String usual=huaYanRecord.getObj().get(i).getRESULT().get(j).getUNIT();
                            SubjectTest subjectTest=new SubjectTest(name,value,usual,deau);
                            list.add(subjectTest);
                        }
                        TestItem testItem=new TestItem(s,list);
                        collist.add(testItem);
                    }
                    adapter = new RecordListAdapter(getActivity(), collist);
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
        String nourl = "http://113.201.59.226:8081/Healwis/base/reportAction!app_resultX.action?sessid=" + ((RecordShowActivity) getActivity()).getSession() + "&studyid=" + ((RecordShowActivity) getActivity()).getStudid();
        MyHttpUtils.handData(handler, 14, nourl, null);
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
