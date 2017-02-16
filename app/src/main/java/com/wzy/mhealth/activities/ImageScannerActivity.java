package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ScanImage;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageScannerActivity extends AppCompatActivity {
    List<String> imageList = new ArrayList<>();
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 32:
                    ScanImage scanImage = (ScanImage) msg.obj;
                    if (scanImage.getStatus().equals("1")) {
                        imageList.clear();
                        imageList.addAll(scanImage.getMdicalPicture());
//                        if(imageList.size()<=1){
//                            imageList.addAll(scanImage.getMdicalPicture());
//                        }
                        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                            @Override
                            public NetworkImageHolderView createHolder() {
                                return new NetworkImageHolderView();
                            }
                        }, imageList).setPageIndicator(new int[]{R.mipmap.dots_gray, R.mipmap.common_msg_tips}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                    } else {
                        Toast.makeText(ImageScannerActivity.this, scanImage.getData(), Toast.LENGTH_LONG).show();
                    }
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scanner);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        TiUser tiUser = new TiUser();
        tiUser.setName("" + name);
        String url = Constants.SERVER_URL + "CaseManageByIdServlet";
        MyHttpUtils.handData(handler, 32, url, tiUser);

    }

    @OnClick({R.id.leftBtn, R.id.convenientBanner})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
