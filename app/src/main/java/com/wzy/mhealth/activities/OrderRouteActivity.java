package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 订单轨迹跟踪
*/
public class OrderRouteActivity extends Activity implements View.OnClickListener{
    private TextView tv_order,tv_copy,tv_confuse;
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_route);
        init();
    }

    private void init() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        tv_order=(TextView) findViewById(R.id.tv_order);
        tv_copy=(TextView) findViewById(R.id.tv_copy);
        tv_confuse=(TextView) findViewById(R.id.tv_confuse);
        tv_copy.setOnClickListener(this);
        tv_confuse.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tv_order.getText());
                Toast.makeText(OrderRouteActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_confuse:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://m.kuaidi100.com"));
                startActivity(intent);
                break;
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
