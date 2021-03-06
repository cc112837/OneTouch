package com.wzy.mhealth.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.MyYuyueActivity;
import com.wzy.mhealth.zxingdemo.CreateQRImageTest;

/**
 *体检预约
 */
public class TijianYuyueFragment extends Fragment {
    private TextView tv_timeid,tv_yuyuecount;
    private ImageView iv_erweima;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd("tijianyue-list-fragment");
    }

    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart("tijianyue-list-fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_tijian_yuyue, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        tv_timeid=(TextView) view.findViewById(R.id.tv_timeid);
        tv_yuyuecount=(TextView) view.findViewById(R.id.tv_yuyuecount);
        iv_erweima=(ImageView) view.findViewById(R.id.iv_erweima);
        String  recordid = ((MyYuyueActivity) getActivity()).getId();
        CreateQRImageTest createQRImageTest = new CreateQRImageTest(iv_erweima);
        createQRImageTest.createQRImage(recordid);
        //// TODO: 2016/6/24 (时间 ，人数，以及二维码生成) 
    }


}
