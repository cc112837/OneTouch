package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.TestItem;

import java.util.ArrayList;

/**
 * 项目名称：mhealth
 * 类描述：体检报告适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/28 15:24
 * 修改人：Administrator
 * 修改时间：2016/6/28 15:24
 * 修改备注：
 */
public class RecordListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TestItem> collist;


    public RecordListAdapter(Context context,ArrayList<TestItem> collist) {
        this.context = context;
        this.collist=collist;
        inflater = LayoutInflater.from(context);
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return collist.size();
    }


    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return collist.get(groupPosition).getListHash().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        ;
        return collist.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return collist.get(groupPosition).getListHash();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.datatime, null);
            groupHolder.iv_updown = (ImageView) convertView.findViewById(R.id.iv_updown);
            groupHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.tv_time.setText(collist.get(groupPosition).getHeader());

        if (isExpanded)// ture is Expanded or false is not isExpanded
        {
            groupHolder.iv_updown.setImageResource(R.mipmap.icon_more_down);
        } else {
            groupHolder.iv_updown.setImageResource(R.mipmap.icon_more);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.child, null);
            childHolder.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            childHolder.tv_content = (TextView) convertView
                    .findViewById(R.id.tv_content);
            childHolder.tv_cp = (TextView) convertView
                    .findViewById(R.id.tv_cp);
            childHolder.tv_usual = (TextView) convertView.findViewById(R.id.tv_usual);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.tv_name.setText(collist.get(groupPosition).getListHash().get(childPosition).getName()+"");
        childHolder.tv_content.setText(collist.get(groupPosition).getListHash().get(childPosition).getCvalue()+"");

        if((collist.get(groupPosition).getListHash().get(childPosition).getDefau()==null)&&(collist.get(groupPosition).getListHash().get(childPosition).getUsual()==null)){
            childHolder.tv_cp.setVisibility(View.GONE);
            childHolder.tv_usual.setVisibility(View.GONE);
        }
            else{
            childHolder.tv_cp.setVisibility(View.VISIBLE);
            childHolder.tv_usual.setVisibility(View.VISIBLE);
            childHolder.tv_cp.setText(collist.get(groupPosition).getListHash().get(childPosition).getDefau() + "");
            childHolder.tv_usual.setText("正常值："+collist.get(groupPosition).getListHash().get(childPosition).getUsual()+"");
        }


        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

class ChildHolder {
    TextView tv_name;
    TextView tv_content;
    TextView tv_usual;
    TextView tv_cp;
}

