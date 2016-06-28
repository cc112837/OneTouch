package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.ChaTiTime;
import com.wzy.mhealth.model.TestItem;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/28 15:24
 * 修改人：Administrator
 * 修改时间：2016/6/28 15:24
 * 修改备注：
 */
public class RecordListAdapter  extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ChaTiTime> timeList;
    private List<List<TestItem>> contentList;


    public RecordListAdapter(Context context, List<ChaTiTime> timeList, List<List<TestItem>> contentList) {
        this.context = context;
        this.timeList = timeList;
        this.contentList = contentList;
        inflater = LayoutInflater.from(context);
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return timeList.size();
    }


    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return contentList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return timeList.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return contentList.get(groupPosition).get(childPosition);
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
            groupHolder.tv_time.setText(timeList.get(groupPosition).getData());
        }


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

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.tv_name.setText(((TestItem) getChild(groupPosition,
                childPosition)).getName());
        childHolder.tv_content.setText(String.valueOf(((TestItem) getChild(
                groupPosition, childPosition)).getContent()));

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
}

