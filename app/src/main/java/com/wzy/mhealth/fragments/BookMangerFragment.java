package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.AidsManagerActivity;
import com.wzy.mhealth.adapter.ManageAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookMangerFragment extends Fragment{
    private ListView lv_show;
    private ManageAdapter manageAdapter;
    private TextView tv_how;
    private List<SelfHealth.DataEntity> list=new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 150:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if ("1".equals(stepInfo.getStatus())) {
                        Toast.makeText(getActivity(), stepInfo.getData(), Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getActivity(), stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
                case 151:
                    SelfHealth selfHealth = (SelfHealth) msg.obj;
                    list.addAll(selfHealth.getData());
                    manageAdapter.notifyDataSetChanged();
                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Intent intent=new Intent(ManageActivity.this,AidsManagerActivity.class);
//                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_book_manger, container, false);
        String url= Constants.SERVER_URL+"";
        MyHttpUtils.handData(handler,151,url,"");
        initView(v);
        return v;
    }
    private void initView(View v) {
        lv_show=(ListView) v.findViewById(R.id.lv_show);
        tv_how=(TextView) v.findViewById(R.id.tv_how);
        manageAdapter=new ManageAdapter(getActivity(),list);
        tv_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AidsManagerActivity.class);
                startActivity(intent);
            }
        });
    }

}
