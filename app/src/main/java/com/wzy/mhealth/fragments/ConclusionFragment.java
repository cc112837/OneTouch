package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.RecordShowActivity;
import com.wzy.mhealth.model.Conclusion;
import com.wzy.mhealth.utils.MyHttpUtils;

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
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 8:
                Conclusion con=(Conclusion)msg.obj;
                if((con.getTotal()!=-1)&&(con.getTotal()!=0)){
                    tv_unusual.setText(con.getRows().get(0).getJCHZ());
                    tv_conclu.setText(con.getRows().get(0).getZJJL());
                    tv_advise.setText(con.getRows().get(0).getYLJY());
                }
                else{
                    Toast.makeText(getActivity(),"暂时未有记录",Toast.LENGTH_LONG).show();
                }
        }
    }
};
    private void init(View v) {
        tv_unusual=(TextView) v.findViewById(R.id.tv_unusual);
        tv_yi=(TextView) v.findViewById(R.id.tv_yi);
        tv_advise=(TextView) v.findViewById(R.id.tv_advise);
        tv_conclu=(TextView) v.findViewById(R.id.tv_conclu);
        String url="http://113.201.59.226:8081/Healwis/base/reportAction!app_summary.action?sessid="+((RecordShowActivity)getActivity()).getSession()+"&studyid="+((RecordShowActivity)getActivity()).getStudid();
        MyHttpUtils.handData(handler,8,url,null);
    }


}
