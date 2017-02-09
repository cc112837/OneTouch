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

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.UserInfoAddActivity;
import com.wzy.mhealth.adapter.UserManageAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMangerFragment extends Fragment {

    @Bind(R.id.lv_show)
    ListView lvShow;
    @Bind(R.id.tv_how)
    TextView tvHow;
    private List<SelfHealth.DataEntity> list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 298:
                    lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity(),UserInfoAddActivity.class);
                            intent.putExtra("flag","see");
                            intent.putExtra("name","");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_manger, container, false);
        ButterKnife.bind(this, view);
        String url= Constants.SERVER_URL+"";
        MyHttpUtils.handData(handler,298,url,null);
        UserManageAdapter userManageAdapter = new UserManageAdapter(getActivity(), list);
        lvShow.setAdapter(userManageAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_how)
    public void onClick() {
        Intent intent=new Intent(getActivity(),UserInfoAddActivity.class);
        intent.putExtra("flag","new");
        startActivity(intent);
    }
}
