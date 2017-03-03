package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ShopDetail;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 商品详情
*/
public class ShopDetailFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private WebView wv_shopdisplay;
    private RadioGroup rg_all;
    private RadioButton tv_intro, tv_detail, tv_bag;
    private String productIntroduce;
    private String productPackaging;
    private String productParameter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 276:
                    ShopDetail shopDetail = (ShopDetail) msg.obj;
                    productIntroduce = shopDetail.getProductIntroduce();
                    productPackaging = shopDetail.getProductPackaging();
                    productParameter = shopDetail.getProductParameter();
                    DisplayMetrics dm = new DisplayMetrics();
                    //取得窗口属性
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                    float density = dm.density;
                    float width = dm.widthPixels / density - 10;
                    wv_shopdisplay.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px!important;}</style></head>" + productIntroduce, "text/html", "utf-8", null);
                    break;
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        wv_shopdisplay = (WebView) v.findViewById(R.id.wv_shopdisplay);
        rg_all = (RadioGroup) v.findViewById(R.id.rg_all);
        tv_intro = (RadioButton) v.findViewById(R.id.tv_intro);
        tv_detail = (RadioButton) v.findViewById(R.id.tv_detail);
        tv_bag = (RadioButton) v.findViewById(R.id.tv_bag);
        String url = Constants.SERVER_URL + "MhealthOneProductDetailServlet";
        TiUser user = new TiUser();
        user.setCardId(((ShopDetailActivity) getActivity()).getId());
        MyHttpUtils.handData(handler, 276, url, user);
        rg_all.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        float width = dm.widthPixels / density - 10;
        String checkcontent = null;

        if (checkedId == tv_intro.getId()) {
            checkcontent=productIntroduce;
        } else if (checkedId == tv_detail.getId()) {
            checkcontent=productParameter;
        } else {//tv_bag
            checkcontent=productPackaging;
        }
        wv_shopdisplay.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px!important;}</style></head>" + checkcontent, "text/html", "utf-8", null);
    }
}
