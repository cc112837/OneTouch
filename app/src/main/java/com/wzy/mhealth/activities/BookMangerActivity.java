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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ManageAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;


public class BookMangerActivity extends AppCompatActivity {
    private ListView lv_show;
    private ManageAdapter manageAdapter;
    private TextView tv_how;
    private List<SelfHealth.DataEntity> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 148:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    Toast.makeText(BookMangerActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
                case 151:
                    SelfHealth selfHealth = (SelfHealth) msg.obj;
                    list.addAll(selfHealth.getData());
                    manageAdapter.notifyDataSetChanged();
                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
                    lv_show.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BookMangerActivity.this);
                            builder.setMessage("确认移除已添加的病历吗？");
                            builder.setTitle("提示");
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    String url = Constants.SERVER_URL + "CaseManageDeleteServlet";
                                    TiUser tiUser = new TiUser();
                                    tiUser.setCardId(list.get(position).getMedicalId()+ "");
                                    MyHttpUtils.handData(handler, 148, url, tiUser);

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
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manger);
        initView();
        String url = Constants.SERVER_URL + "CaseManageServlet";
        MyHttpUtils.handData(handler, 151, url, null);

    }

    private void initView() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        tv_how = (TextView) findViewById(R.id.tv_how);
        manageAdapter = new ManageAdapter(BookMangerActivity.this, list);
        tv_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookMangerActivity.this, AidsManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
