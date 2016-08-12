package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.NewsAids;
import com.wzy.mhealth.utils.ReadUtil;

public class JiBingActivity extends BaActivity {
    private ListView lv_display, office;
    private ImageView leftBtn;
    private int pos;
    String[] dess = {"头部", "四肢", "胸，腹部", "生殖", "婴幼儿"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_bing);
        initView();
    }

    //初始化界面控件
    private void initView() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        office = (ListView) findViewById(R.id.office);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(JiBingActivity.this,
                R.layout.list_item_text, dess);
        office.setAdapter(adapter);
        lv_display = (ListView) findViewById(R.id.lv_display);
        final NewsAids newsAids = new Gson().fromJson(ReadUtil.readFromRaw(getApplicationContext()), NewsAids.class);
        String arr[] = new String[newsAids.getHead().size()];
        for (int i = 0; i < newsAids.getHead().size(); i++) {
            arr[i] = newsAids.getHead().get(i).getTitle();
        }
        ArrayAdapter adapter1 = new ArrayAdapter<>(JiBingActivity.this,
                R.layout.list_item_text_black, arr);
        lv_display.setAdapter(adapter1);
        office.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter adapter = null;
                String[] s = null;

                if (position == 0) {
                    s = new String[newsAids.getHead().size()];
                    for (int i = 0; i < newsAids.getHead().size(); i++) {
                        s[i] = newsAids.getHead().get(i).getTitle();
                    }
                } else if (position == 1) {
                    s = new String[newsAids.getHand().size()];
                    for (int i = 0; i < newsAids.getHand().size(); i++) {
                        s[i] = newsAids.getHand().get(i).getTitle();
                    }
                } else if (position == 2) {
                    s = new String[newsAids.getBrest().size()];
                    for (int i = 0; i < newsAids.getBrest().size(); i++) {
                        s[i] = newsAids.getBrest().get(i).getTitle();
                    }
                } else if (position == 3) {
                    s = new String[newsAids.getSpecial().size()];
                    for (int i = 0; i < newsAids.getSpecial().size(); i++) {
                        s[i] = newsAids.getSpecial().get(i).getTitle();
                    }
                } else {
                    s = new String[newsAids.getBaby().size()];
                    for (int i = 0; i < newsAids.getBaby().size(); i++) {
                        s[i] = newsAids.getBaby().get(i).getTitle();
                    }
                }
                adapter = new ArrayAdapter<>(
                        JiBingActivity.this, R.layout.list_item_text_black, s);
                lv_display.setAdapter(adapter);
                pos = position;
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(JiBingActivity.this, JiBingDetailActicity.class);
                String content = null;
                String title=null;
                if (pos == 0) {
                    content = newsAids.getHead().get(position).getDetail();
                    title=newsAids.getHead().get(position).getTitle();
                } else if (pos == 1) {
                    content = newsAids.getHand().get(position).getDetail();
                    title=newsAids.getHand().get(position).getTitle();
                } else if (pos == 2) {
                    content = newsAids.getBrest().get(position).getDetail();
                    title=newsAids.getBrest().get(position).getTitle();
                } else if (pos == 3) {
                    content = newsAids.getSpecial().get(position).getDetail();
                    title=newsAids.getSpecial().get(position).getTitle();
                } else {
                    content = newsAids.getBaby().get(position).getDetail();
                    title=newsAids.getBaby().get(position).getTitle();
                }
                intent.putExtra("content", content);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }

}
