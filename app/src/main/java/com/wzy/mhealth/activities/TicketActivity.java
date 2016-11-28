package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 优惠劵
 * 创建时间：2016/11/18 16:18
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class TicketActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView leftBtn;
    private LinearLayout ll_electric,ll_header,ll_addticket;
    private RadioGroup rg_all, rg_tickethead, rg_ticketcontent;
    private EditText tv_company,et_cardid,et_bank,et_phonebank,et_adress,et_realize,et_comname;
    private TextView tv_confirm;
    private RadioButton rb_paper, rb_eletri, rb_cross, rb_work, rb_addticket, rb_people, rb_company, rb_computer, rb_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        init();
    }

    private void init() {
        ll_electric = (LinearLayout) findViewById(R.id.ll_electric);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        et_cardid=(EditText) findViewById(R.id.et_cardid);
        et_bank=(EditText) findViewById(R.id.et_bank);
        et_adress=(EditText) findViewById(R.id.et_adress);
        et_realize=(EditText) findViewById(R.id.et_realize);
        et_phonebank=(EditText) findViewById(R.id.et_phonebank);
        et_comname=(EditText) findViewById(R.id.et_comname);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        rg_tickethead = (RadioGroup) findViewById(R.id.rg_tickethead);
        rg_ticketcontent = (RadioGroup) findViewById(R.id.rg_ticketcontent);
        rb_people = (RadioButton) findViewById(R.id.rb_people);
        rb_company = (RadioButton) findViewById(R.id.rb_company);
        rg_all = (RadioGroup) findViewById(R.id.rg_all);
        rb_paper = (RadioButton) findViewById(R.id.rb_paper);
        rb_eletri = (RadioButton) findViewById(R.id.rb_eletri);
        tv_company = (EditText) findViewById(R.id.tv_company);
        rb_addticket = (RadioButton) findViewById(R.id.rb_addticket);
        rb_computer = (RadioButton) findViewById(R.id.rb_computer);
        rb_clear = (RadioButton) findViewById(R.id.rb_clear);
        rb_work = (RadioButton) findViewById(R.id.rb_work);
        ll_header=(LinearLayout) findViewById(R.id.ll_header);
        rb_cross = (RadioButton) findViewById(R.id.rb_cross);
        ll_addticket=(LinearLayout) findViewById(R.id.ll_addticket);
        rg_all.setOnCheckedChangeListener(this);
        rg_tickethead.setOnCheckedChangeListener(this);
        rg_ticketcontent.setOnCheckedChangeListener(this);
        leftBtn.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_confirm:
                String url = et_cardid.getText().toString();
                String url1 = et_bank.getText().toString();
                String url2 = et_phonebank.getText().toString();
                String url3 = et_adress.getText().toString();
                String url4 = et_realize.getText().toString();
                String url5 = et_comname.getText().toString();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == rb_paper.getId()) {
            ll_electric.setVisibility(View.GONE);
            ll_header.setVisibility(View.VISIBLE);
            ll_addticket.setVisibility(View.GONE);
        } else if (checkedId == rb_eletri.getId()) {
            ll_electric.setVisibility(View.VISIBLE);
            ll_addticket.setVisibility(View.GONE);
            ll_header.setVisibility(View.VISIBLE);
        } else if (checkedId == rb_addticket.getId()) {
            ll_addticket.setVisibility(View.VISIBLE);
            ll_electric.setVisibility(View.GONE);
        } else if (checkedId == rb_people.getId()) {
            tv_company.setVisibility(View.GONE);
        } else if (checkedId == rb_company.getId()) {
            tv_company.setVisibility(View.VISIBLE);
        } else if (checkedId == rb_computer.getId()) {

        } else if (checkedId == rb_clear.getId()) {

        } else if (checkedId == rb_cross.getId()) {

        } else {
        }
    }
}
