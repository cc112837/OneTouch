package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzy.mhealth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConclusionFragment extends Fragment {
    private TextView tv_unusual,tv_yi,tv_advise,tv_conclu;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_conclusion, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        tv_unusual=(TextView) v.findViewById(R.id.tv_unusual);
        tv_yi=(TextView) v.findViewById(R.id.tv_yi);
        tv_advise=(TextView) v.findViewById(R.id.tv_advise);
        tv_conclu=(TextView) v.findViewById(R.id.tv_conclu);

    }


}
