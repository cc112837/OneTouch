package com.wzy.mhealth.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler,View.OnClickListener{
    private IWXAPI api;
	private ImageView leftBtn;
	private TextView tv_show;
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what){
				case 288:
					StepInfo retuback=(StepInfo) msg.obj;
					if("1".equals(retuback.getStatus())){
						WXPayEntryActivity.this.finish();
					}
					break;
			}
		}
	};
	
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
		tv_show=(TextView) findViewById(R.id.tv_show);
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
		if(0==resp.errCode){
			// 验证同步信息
			String url= Constants.SERVER_URL+"MhealthShopPayServlet";
			TiUser user=new TiUser();
			user.setName("");
			MyHttpUtils.handData(handler,288,url,user);
		}
		else{
			tv_show.setText("支付结果"+resp.errCode);
			ToastUtil.show(WXPayEntryActivity.this,resp.errStr);
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