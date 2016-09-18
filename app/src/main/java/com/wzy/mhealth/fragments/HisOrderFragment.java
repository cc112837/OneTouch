package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wzy.mhealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HisOrderFragment extends Fragment {
private ListView lv_show;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_his_order, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_show=(ListView) v.findViewById(R.id.lv_show);
    }


}
