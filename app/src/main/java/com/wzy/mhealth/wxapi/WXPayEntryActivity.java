package com.wzy.mhealth.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzy.mhealth.R;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler,View.OnClickListener{
    private IWXAPI api;
	private ImageView leftBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
    	api = WXAPIFactory.createWXAPI(this,"wxee8f5f748fbea43c");
        api.handleIntent(getIntent(), this);
		init();

    }

	private void init() {
		leftBtn=(ImageView) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
			builder.show();
		}
		if(0==resp.errCode){
			// TODO: 2016/12/6 验证同步信息
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.leftBtn:
				finish();
				break;
		}

	}
}