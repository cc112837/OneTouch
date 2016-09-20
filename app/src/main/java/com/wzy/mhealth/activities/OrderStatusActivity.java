package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Retuback;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class OrderStatusActivity extends Activity {
    private TextView tv_orderstatus, tv_name, tv_price, back_money, tv_ordernum, tv_orderbuy, tv_bought;
    private ImageView iv_taointro, iv_taocandetail, leftBtn;
    String id, name, price, bought, status, creat, num, account, orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        bought = intent.getStringExtra("bought");
        status = intent.getStringExtra("status");
        creat = intent.getStringExtra("creat");
        status = intent.getStringExtra("status");
        num = intent.getStringExtra("num");//订单编号
        account = intent.getStringExtra("account");//流水号
        orderid = intent.getStringExtra("orderid");
        init();
    }

    private void init() {
        tv_orderstatus = (TextView) findViewById(R.id.tv_orderstatus);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText("套餐：" + name + "");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.setText("价格：¥" + price + "");
        back_money = (TextView) findViewById(R.id.back_money);

        if (status.equals("0")) {
            tv_orderstatus.setText("支付成功");
            back_money.setText("申请退款");
            back_money.setEnabled(true);
            back_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderStatusActivity.this);
                    builder.setTitle("退款界面");
                    builder.setMessage("你确定要退款吗");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
                            back_money.setText("退款中");
                            tv_orderstatus.setText("退款中");
                            back_money.setEnabled(false);
                            String url = Constants.SERVER_URL + "PayBackServlet";
                            TiUser user = new TiUser();
                            user.setTel(orderid);
                            MyHttpUtils.handData(handler, 131, url, user);
                        }
                    });
                    builder.create().show();
                }
            });
        } else if (status.equals("1")) {
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("退款完成");
            back_money.setText("退款完成");

        } else if (status.equals("2")) {
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("已体检");
            back_money.setText("订单完成");

        } else if (status.equals("3")) {
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("退款中");
            back_money.setText("退款中");

        }

        tv_ordernum = (TextView) findViewById(R.id.tv_ordernum);
        tv_ordernum.setText("订单编号:" + num + "");
        tv_orderbuy = (TextView) findViewById(R.id.tv_orderbuy);
        tv_orderbuy.setText("创建时间:" + creat + "");
        tv_bought = (TextView) findViewById(R.id.tv_bought);
        tv_bought.setText("成交时间:" + bought);


        iv_taointro = (ImageView) findViewById(R.id.iv_taointro);
        iv_taocandetail = (ImageView) findViewById(R.id.iv_taocandetail);
        iv_taocandetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderStatusActivity.this, TaocanDetailAcitivty.class);
                intent.putExtra("id", id + "");
                startActivity(intent);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 131:
                    Retuback retuback = (Retuback) msg.obj;
                    if (retuback.getStatus().equals("1")) {
                        Toast.makeText(OrderStatusActivity.this, "请求已被受理，马上为您处理", Toast.LENGTH_LONG
                        ).show();
                    } else {
                        Toast.makeText(OrderStatusActivity.this, retuback.getMessage(), Toast.LENGTH_LONG
                        ).show();
                    }
                    break;
            }
        }
    };
}
