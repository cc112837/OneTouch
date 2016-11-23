package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.ShopCommentFragment;
import com.wzy.mhealth.fragments.ShopDetailFragment;
import com.wzy.mhealth.fragments.ShopIntroFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView leftBtn,rightBtn;
    private TextView tv_count,title_0,title,title_2;
    private ViewPager vp_shopdetail;
    private List<Fragment> fragments;
    private Button btn_buy,btn_cart;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        id = getIntent().getStringExtra("id");
        setId(id);
        init();
    }

    private void init() {
        btn_cart=(Button) findViewById(R.id.btn_cart);
        btn_buy=(Button) findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(this);
        btn_cart.setOnClickListener(this);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        rightBtn=(ImageView) findViewById(R.id.rightBtn);
        title_0=(TextView) findViewById(R.id.title_0);
        title=(TextView) findViewById(R.id.title);
        title_2=(TextView) findViewById(R.id.title_2);
        title_0.setOnClickListener(this);
        title.setOnClickListener(this);
        title_2.setOnClickListener(this);
        tv_count=(TextView) findViewById(R.id.tv_count);
        fragments = new ArrayList<>();
        ShopIntroFragment introFragmentFragment = new ShopIntroFragment();
        fragments.add(introFragmentFragment);
        ShopDetailFragment detailFragment = new ShopDetailFragment();
        fragments.add(detailFragment);
        ShopCommentFragment commentFragment = new ShopCommentFragment();
        fragments.add(commentFragment);
        vp_shopdetail=(ViewPager) findViewById(R.id.vp_shopdetail);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        vp_shopdetail.setAdapter(adapter);
        vp_shopdetail.setCurrentItem(0);
        vp_shopdetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    tvTextSet(title_0,title,title_2);
                } else if(position==1){
                    tvTextSet(title,title_0,title_2);
                }else{
                    tvTextSet(title_2,title,title_0);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }
    private void tvTextSet(TextView t1,TextView t2,TextView t3 )
    {
        t1.setBackgroundResource(R.mipmap.line_shop);
        t2.setBackgroundColor(getResources().getColor(R.color.title_green));
        t3.setBackgroundColor(getResources().getColor(R.color.title_green));
        t1.setTextSize(20);
        t2.setTextSize(16);
        t3.setTextSize(16);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.rightBtn:
                intent=new Intent(ShopDetailActivity.this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cart:
                Toast.makeText(ShopDetailActivity.this,"加入购物车加入成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_buy:
                intent=new Intent(ShopDetailActivity.this,ShopBuyActivity.class);
                startActivity(intent);
                break;
            case R.id.title_0:
                vp_shopdetail.setCurrentItem(0);
                tvTextSet(title_0,title,title_2);
                break;
            case R.id.title:
                vp_shopdetail.setCurrentItem(1);
                tvTextSet(title,title_0,title_2);
                break;
            case R.id.title_2:
                vp_shopdetail.setCurrentItem(2);
                tvTextSet(title_2,title,title_0);
                break;
        }
    }
}
