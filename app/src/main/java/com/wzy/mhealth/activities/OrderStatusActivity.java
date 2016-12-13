package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Retuback;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class OrderStatusActivity extends Activity implements View.OnClickListener {
    private TextView tv_orderstatus, tv_name, tv_ordersubmit, tv_price, back_money, tv_ordernum, tv_orderbuy, tv_bought;
    private ImageView iv_taointro, iv_taocandetail, leftBtn;
    CheckBox iv_1, iv_2, iv_3, iv_4, iv_5;
    private EditText et_comment;
    int grade=5;
    private LinearLayout ll_ordercom;
    String id, name, price, bought, status, creat, num, account, orderid,image;
    Intent intent;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        bought = intent.getStringExtra("bought");
        status = intent.getStringExtra("status");
        creat = intent.getStringExtra("creat");
        status = intent.getStringExtra("status");
        num = intent.getStringExtra("num");//订单编号
        orderid = intent.getStringExtra("orderid");
        image=intent.getStringExtra("image");
        type = intent.getStringExtra("type");
        init();
    }

    private void init() {
        ll_ordercom = (LinearLayout) findViewById(R.id.ll_ordercom);
        tv_orderstatus = (TextView) findViewById(R.id.tv_orderstatus);
        tv_ordersubmit = (TextView) findViewById(R.id.tv_ordersubmit);
        tv_ordersubmit.setOnClickListener(this);
        iv_taointro=(ImageView) findViewById(R.id.iv_taointro);
        ImageLoader.getInstance().displayImage(image,iv_taointro, PhotoUtils.avatarImage);
        et_comment = (EditText) findViewById(R.id.et_comment);
        iv_1 = (CheckBox) findViewById(R.id.iv_1);
        iv_2 = (CheckBox) findViewById(R.id.iv_2);
        iv_3 = (CheckBox) findViewById(R.id.iv_3);
        iv_4 = (CheckBox) findViewById(R.id.iv_4);
        iv_5 = (CheckBox) findViewById(R.id.iv_5);
        iv_1.setOnClickListener(this);
        iv_2.setOnClickListener(this);
        iv_3.setOnClickListener(this);
        iv_4.setOnClickListener(this);
        iv_5.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText("套餐：" + name + "");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.setText("价格：¥" + price + "");
        back_money = (TextView) findViewById(R.id.back_money);

        if (status.equals("0")) {
            ll_ordercom.setVisibility(View.GONE);
            tv_orderstatus.setText("支付成功");
            back_money.setText("申请退款");
            back_money.setEnabled(true);
            back_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderStatusActivity.this);
                    builder.setTitle("申请退款界面");
                    builder.setMessage("你确定要申请退款吗");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
                            back_money.setText("处理中");
                            tv_orderstatus.setText("处理中");
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
            String url = Constants.SERVER_URL + "ChooseEvaluateServlet";
            TiUser user = new TiUser();
            user.setName("" + orderid);
            MyHttpUtils.handData(handler, 262, url, user);
            ll_ordercom.setVisibility(View.VISIBLE);
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("退款完成");
            back_money.setVisibility(View.INVISIBLE);

        } else if (status.equals("2")) {
            String url = Constants.SERVER_URL + "ChooseEvaluateServlet";
            TiUser user = new TiUser();
            user.setName("" + orderid);
            MyHttpUtils.handData(handler, 262, url, user);
            ll_ordercom.setVisibility(View.VISIBLE);
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("已完成");
            back_money.setText("订单完成");
            back_money.setVisibility(View.INVISIBLE);

        } else if (status.equals("3")) {
            ll_ordercom.setVisibility(View.GONE);
            back_money.setEnabled(false);
            back_money.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
            tv_orderstatus.setText("处理中");
            back_money.setText("处理中");

        }

        tv_ordernum = (TextView) findViewById(R.id.tv_ordernum);
        tv_ordernum.setText("订单编号:" + num + "");
        tv_orderbuy = (TextView) findViewById(R.id.tv_orderbuy);
        tv_orderbuy.setText("创建时间:" + creat + "");
        tv_bought = (TextView) findViewById(R.id.tv_bought);
        tv_bought.setText("成交时间:" + bought);


        iv_taointro = (ImageView) findViewById(R.id.iv_taointro);
        iv_taocandetail = (ImageView) findViewById(R.id.iv_taocandetail);
        iv_taocandetail.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 131:
                    Retuback retuback = (Retuback) msg.obj;
                    if (retuback.getStatus().equals("1")) {
                        Toast.makeText(OrderStatusActivity.this, "您的请求已被受理，一般会在3~5个工作日处理完成", Toast.LENGTH_LONG
                        ).show();
                        setResult(345, intent);
                        finish();
                    } else {
                        Toast.makeText(OrderStatusActivity.this, retuback.getMessage(), Toast.LENGTH_LONG
                        ).show();
                    }
                    break;
                case 262:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        ll_ordercom.setVisibility(View.GONE);
                    } else if (stepInfo.getStatus().equals("0")) {
                        ll_ordercom.setVisibility(View.VISIBLE);
                    }
                    break;
                case 263:
                    StepInfo step = (StepInfo) msg.obj;
                    if (step.getStatus().equals("1")) {
                        Toast.makeText(OrderStatusActivity.this, "评价成功", Toast.LENGTH_LONG).show();
                        ll_ordercom.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(OrderStatusActivity.this, "评价失败，请稍候重试", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_taocandetail:
                if("1".equals(type)){
                    Intent intent = new Intent(OrderStatusActivity.this, TaocanDetailAcitivty.class);
                    intent.putExtra("id", id + "");
                    startActivity(intent);
                }
                else if("3".equals(type)){
                    Intent intent = new Intent(OrderStatusActivity.this, PrivaDoctorActivity.class);
                    startActivity(intent);
                }
                else{
                    iv_taocandetail.setVisibility(View.GONE);
                }
                break;
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_ordersubmit:
                String advice = et_comment.getText().toString();
                String uri = Constants.SERVER_URL + "MhealthOrderEvaluateServlet";
                TiUser user = new TiUser();
                user.setName(orderid + "");
                user.setTel(grade + "");
                user.setPass(advice + "");
                MyHttpUtils.handData(handler, 263, uri, user);
                break;
            case R.id.iv_1:
                grade = 1;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.greystar2);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_2:
                grade = 2;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_3:
                grade = 3;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_4:
                grade = 4;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_5:
                grade = 5;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.goldstar1);
                break;
        }
    }
}
