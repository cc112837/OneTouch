package com.wzy.mhealth.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
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

public class UserManagerActivity extends AppCompatActivity {
    @Bind(R.id.lv_show)
    ListView lvShow;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.tv_how)
    TextView tvHow;
    private String flag;
    private Intent intent;
    private UserManageAdapter userManageAdapter;
    private List<UserManger.DataEntity> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 298:
                    UserManger userManger = (UserManger) msg.obj;
                    list.clear();
                    list.addAll(userManger.getData());
                    userManageAdapter.notifyDataSetChanged();
                    if ("see".equals(flag)) {
                        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent mIntent = new Intent();
                                mIntent.putExtra("name", list.get(position).getName());
                                mIntent.putExtra("id", list.get(position).getUserManageId());
                                // 设置结果，并进行传送
                                setResult(RESULT_OK, mIntent);
                                finish();//此处一定要调用finish()方法
                            }
                        });
                    } else {
                        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(UserManagerActivity.this, UserInfoAddActivity.class);
                                intent.putExtra("flag", "see");
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("user", list.get(position));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    lvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserManagerActivity.this);
                            builder.setMessage("确认移除已添加的用户吗？");
                            builder.setTitle("提示");
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    String url = Constants.SERVER_URL + "UserManagerDeleServlet";
                                    TiUser tiUser = new TiUser();
                                    tiUser.setCardId(list.get(position).getUserManageId() + "");
                                    MyHttpUtils.handData(handler, 300, url, tiUser);

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
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    userManageAdapter.notifyDataSetChanged();
                    Toast.makeText(UserManagerActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        intent = getIntent();
        flag = intent.getStringExtra("flag");
        String url = Constants.SERVER_URL + "UserManagerServlet";
        MyHttpUtils.handData(handler, 298, url, null);
        userManageAdapter = new UserManageAdapter(UserManagerActivity.this, list);
        lvShow.setAdapter(userManageAdapter);
    }

    @OnClick({R.id.leftBtn, R.id.tv_how})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_how:
                Intent intent = new Intent(UserManagerActivity.this, UserInfoAddActivity.class);
                intent.putExtra("flag", "new");
                startActivity(intent);
                break;
        }
    }
}
