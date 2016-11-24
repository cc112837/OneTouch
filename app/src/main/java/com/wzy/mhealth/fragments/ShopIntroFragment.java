package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ShopDetail;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.LocalImageView;

import java.util.ArrayList;

public class ShopIntroFragment extends Fragment {
    private ConvenientBanner cb_shop;
    private SnappingStepper stepperCustom;
    private TextView tv_per, tv_intro, tv_name;
    private ArrayList<String> localImages = new ArrayList<>();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 275:
                    ShopDetail shopDetail=(ShopDetail) msg.obj;
                    localImages.add(shopDetail.getProductImageBig());
                    tv_name.setText(shopDetail.getProductName() + "");
                    tv_intro.setText(shopDetail.getData());
                    tv_per.setText(shopDetail.getProductNewPrice()+"");
                    cb_shop.setPages(
                            new CBViewHolderCreator<LocalImageView>() {
                                @Override
                                public LocalImageView createHolder() {
                                    return new LocalImageView();
                                }
                            }, localImages)
                            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                            .setPageIndicator(new int[]{R.mipmap.dots_gray, R.mipmap.dot_white})
                                    //设置指示器的方向
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_intro, container, false);
        ((ShopDetailActivity)getActivity()).setValuenum(1);
        String id = ((ShopDetailActivity) getActivity()).getId();
        String url= Constants.SERVER_URL+"MhealthOneProductServlet";
        TiUser user = new TiUser();
        user.setCardId(id+"");
        MyHttpUtils.handData(handler, 275, url, user);
        init(v);
        return v;
    }

    private void init(View v) {
        cb_shop = (ConvenientBanner) v.findViewById(R.id.cb_shop);
        stepperCustom = (SnappingStepper) v.findViewById(R.id.stepperCustom);
        tv_per = (TextView) v.findViewById(R.id.tv_per);
        tv_intro = (TextView) v.findViewById(R.id.tv_intro);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        stepperCustom.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                switch (view.getId()) {
                    case R.id.stepperCustom:
                        ((ShopDetailActivity)getActivity()).setValuenum(value);
                        break;
                }
            }
        });

    }


}
