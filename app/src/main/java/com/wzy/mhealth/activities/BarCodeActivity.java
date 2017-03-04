package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;
import com.wzy.mhealth.zxingdemo.CreateQRImageTest;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 二维码生成界面
*/
public class BarCodeActivity extends BaActivity {
    private ImageView qrImgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        String contentString = LeanchatUser.getCurrentUser().getObjectId();
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
