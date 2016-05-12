package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.zxingdemo.CreateQRImageTest;

public class BarCodeActivity extends Activity {
    private ImageView qrImgImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        String contentString = Constants.USER_NAME;
        CreateQRImageTest createQRImageTest = new CreateQRImageTest(qrImgImageView);
        createQRImageTest.createQRImage(contentString);


        //返回箭头
        back();
    }

    private void back() {
        // TODO Auto-generated method stub
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
