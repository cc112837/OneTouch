package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Record;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class RecordListActivity extends Activity {
    private String tag;
    private ImageView leftBtn;
    private ListView lv_showid;
    private ArrayAdapter adapter;
    private List<Record.RowsEntity> rows;
    private List list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 7:
                    Record record = (Record) msg.obj;
                    rows = record.getRows();
                    for (int i=0;i<rows.size();i++){
                        list.add(rows.get(i).getCZSJ().substring(0,10)+"体检报告");
                    }
                    if (record.getTotal() == 0 || record.getTotal() == -1) {
                        Toast.makeText(RecordListActivity.this, "报告尚未生成，请等待......", Toast.LENGTH_LONG).show();
                    } else {
                        adapter.notifyDataSetChanged();
                        lv_showid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(RecordListActivity.this, RecordShowActivity.class);
                                intent.putExtra("stuid", rows.get(position).getTJID());
                                intent.putExtra("session", tag);
                                startActivity(intent);
                            }
                        });
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        Intent intent=getIntent();
        tag=intent.getStringExtra("session");
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_showid=(ListView) findViewById(R.id.lv_showid);
        list=new ArrayList();
        adapter = new ArrayAdapter(
                RecordListActivity.this, R.layout.list_item_text_black,list);
        lv_showid.setAdapter(adapter);
        String renurl = "http://113.201.59.226:8081/Healwis/base/recordAction!app_matchAccept.action?sessid=" +tag;
        MyHttpUtils.handData(handler, 7, renurl, null);
    }
}
