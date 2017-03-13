package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 科室选择
 */
public class SectionActivity extends AppCompatActivity {

    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.titleView)
    TextView titleView;
    @Bind(R.id.office)
    ListView office;
    @Bind(R.id.lv_display)
    ListView lvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.leftBtn)
    public void onClick() {
        finish();
    }
}
