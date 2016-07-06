package com.wzy.mhealth.adapter;

/**
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/3 14:11
 * 修改人：Administrator
 * 修改时间：2016/6/3 14:11
 * 修改备注：
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.MyYuyueActivity;
import com.wzy.mhealth.model.ChaTiContent;
import com.wzy.mhealth.model.ChaTiTime;
import com.wzy.mhealth.view.SubListView;
import com.wzy.mhealth.zxingdemo.CreateQrYi;

import java.util.List;

/***
 * 适配器
 *
 * @author Administrator
 */
public class MyexpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ChaTiTime> timeList;
    private List<List<ChaTiContent>> contentList;


    public MyexpandableListAdapter(Context context, List<ChaTiTime> timeList, List<List<ChaTiContent>> contentList) {
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
        //return contentList.get(groupPosition).size();
        return 1;
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
        View childView = null;
        if (convertView == null) {
            childView = newChildView(parent, groupPosition);
        } else {
            childView = convertView;
        }
        bindChildView(groupPosition, childPosition, childView);
        return childView;
    }

    private View newChildView(ViewGroup parent, final int groupPosition) {
        View v = inflater.inflate(R.layout.typeclass, null);
        SubListView listView = (SubListView) v.findViewById(R.id.lv_showclassfy);
        View header = inflater.inflate(R.layout.typeclassheader, null);
        listView.addHeaderView(header);
        return v;
    }

    private void bindChildView(int groupPosition, int childPosition,
                               View childView) {
        TextView tv_name = (TextView) childView.findViewById(R.id.tv_name);
        ImageView iv_yiwei=(ImageView) childView.findViewById(R.id.iv_yiwei);
        CreateQrYi createQrYi= new CreateQrYi(iv_yiwei);
        DisplayMetrics metric = new DisplayMetrics();
        ((MyYuyueActivity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        createQrYi.getBarcode(((MyYuyueActivity) context).getEx(), width, 100);
        tv_name.setText(((MyYuyueActivity)context).getEx());
        SubListView listView = (SubListView) childView.findViewById(R.id.lv_showclassfy);
        listView.setAdapter(new TestItemAdapter(context, contentList.get(groupPosition), inflater));
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

class GroupHolder {
    TextView tv_time;
    ImageView iv_updown;
}








