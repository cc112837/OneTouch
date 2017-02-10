package com.wzy.mhealth.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.UserInfoAddActivity;
import com.wzy.mhealth.adapter.UserManageAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.UserManger;
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
    private List<UserManger.DataEntity> list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 298:
                    UserManger userManger=(UserManger) msg.obj;
                    list.clear();
                    list.addAll(userManger.getData());
                    userManageAdapter.notifyDataSetChanged();
                    lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity(),UserInfoAddActivity.class);
                            intent.putExtra("flag","see");
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("user", list.get(position));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    lvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("确认移除已添加图片吗？");
                            builder.setTitle("提示");
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    String url=Constants.SERVER_URL+"UserManagerDeleServlet";
                                    TiUser tiUser=new TiUser();
                                    tiUser.setCardId(list.get(position).getUserManageId()+"");
                                    MyHttpUtils.handData(handler,300,url,tiUser);

                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();
                            return false;
                        }
                    });
                    break;
                case 300:
                    StepInfo stepInfo=(StepInfo) msg.obj;
                    userManageAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),stepInfo.getData(),Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private UserManageAdapter userManageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_manger, container, false);
        ButterKnife.bind(this, view);
        String url= Constants.SERVER_URL+"UserManagerServlet";
        MyHttpUtils.handData(handler,298,url,null);
        userManageAdapter = new UserManageAdapter(getActivity(), list);
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
