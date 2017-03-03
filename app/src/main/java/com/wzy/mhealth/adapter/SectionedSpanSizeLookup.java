package com.wzy.mhealth.adapter;

import android.support.v7.widget.GridLayoutManager;

/**
 * 项目名称：mhealth
 * 类描述：头部伸缩
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 10:01
 * 修改人：Administrator
 * 修改时间：2016/11/23 10:01
 * 修改备注：
 */
public class SectionedSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    protected SectionedRecyclerViewAdapter<?, ?, ?> adapter = null;
    protected GridLayoutManager layoutManager = null;

    public SectionedSpanSizeLookup(SectionedRecyclerViewAdapter<?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {

        if(adapter.isSectionHeaderPosition(position) || adapter.isSectionFooterPosition(position)){
            return layoutManager.getSpanCount();
        }else{
            return 1;
        }

    }
}