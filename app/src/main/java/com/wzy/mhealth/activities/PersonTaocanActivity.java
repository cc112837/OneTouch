package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.model.Tijian;

import java.util.List;

public class PersonTaocanActivity extends Activity{
    private ImageView leftBtn;
    private ListView lv_show;
    TaoCanAdapter adapter;
    private String id, name, tel, add, content, img;
    private ImageView iv_img;
    private TextView tv_name;
    private TextView tv_add;
    private TextView tv_tel;
    private View headview;
    private ImageView iv_tel;
    private List<Tijian.DataEntity.TaocanIdEntity> second;
    private String cityaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_taocan);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        tel = intent.getStringExtra("tel");
        add = intent.getStringExtra("add");
        content = intent.getStringExtra("content");
        img = intent.getStringExtra("img");
        second = (List<Tijian.DataEntity.TaocanIdEntity>) intent.getSerializableExtra("second");
        init();
    }





    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_show = (ListView) findViewById(R.id.lv_show);
        headview = LayoutInflater.from(this).inflate(R.layout.zhidetail_header, null);
        iv_img = (ImageView) headview.findViewById(R.id.iv_img);
        tv_name = (TextView) headview.findViewById(R.id.tv_name);
        tv_add = (TextView) headview.findViewById(R.id.tv_add);
        tv_tel = (TextView) headview.findViewById(R.id.tv_tel);
        ImageView iv_addr = (ImageView) headview.findViewById(R.id.iv_address);
        iv_tel = (ImageView) headview.findViewById(R.id.iv_tel);
        tv_name.setText("" + name);
        headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonTaocanActivity.this, ZhixingIntroduceActivity.class);
                intent.putExtra("content", content + "");
                startActivity(intent);
            }
        });
        tv_add.setText("地址：" + add);
        tv_tel.setText("电话：" + tel);
        iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                startActivity(intent);
            }
        });
        ImageLoader.getInstance().displayImage(img, iv_img);
        adapter = new TaoCanAdapter(this, second);


        iv_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonTaocanActivity.this, RouteActivity.class);
                intent.putExtra("end",add);
                startActivity(intent);

            }
        });

        lv_show.addHeaderView(headview);
        lv_show.setAdapter(adapter);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Intent intent = new Intent(PersonTaocanActivity.this, TaocanDetailAcitivty.class);
                    intent.putExtra("id", second.get(position - 1).getTaoId() + "");
                    startActivityForResult(intent, 456);
                }
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 456:
                finish();
                break;
            default:
                break;
        }
    }

}
