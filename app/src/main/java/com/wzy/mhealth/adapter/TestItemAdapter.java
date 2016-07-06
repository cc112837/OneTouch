package com.wzy.mhealth.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.DoctorGradeActivity;
import com.wzy.mhealth.activities.MyYuyueActivity;
import com.wzy.mhealth.model.ChaTiContent;

import java.util.List;

public class TestItemAdapter extends BaseAdapter {
    private List<ChaTiContent> contentList;
    private Context con;
    private LayoutInflater mInflater;

    public TestItemAdapter(Context context, List<ChaTiContent> contentList, LayoutInflater inflater) {
        // TODO Auto-generated constructor stub
        this.con = context;
        this.contentList = contentList;
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return contentList.size();

    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ChildHolder childHolder;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = mInflater.inflate(R.layout.type_item, null);
            childHolder.tv_classname = (TextView) convertView.findViewById(R.id.tv_classname);
            childHolder.tv_waitcount = (Button) convertView.findViewById(R.id.tv_waitcount);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.tv_classname.setText(contentList.get(position).getItemname());
        childHolder.tv_waitcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(con, DoctorGradeActivity.class);
                intent.putExtra("codeid",contentList.get(position).getItemcode());
                intent.putExtra("stuid",contentList.get(position).getStuyid());
                intent.putExtra("session",((MyYuyueActivity)con).getSession());
                intent.putExtra("eid",((MyYuyueActivity)con).getId());
                con.startActivity(intent);
            }
        });
        return convertView;
    }

    class ChildHolder {
        TextView tv_state, tv_classname;
        Button tv_waitcount;
    }
}
