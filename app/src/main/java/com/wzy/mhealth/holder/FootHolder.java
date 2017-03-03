package com.wzy.mhealth.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

/**
 * 项目名称：mhealth
 * 类描述：套餐展开更多
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 11:35
 * 修改人：Administrator
 * 修改时间：2016/11/23 11:35
 * 修改备注：
 */
public class FootHolder  extends RecyclerView.ViewHolder{
    public LinearLayout ll_more;
    public TextView tv_more;
    public FootHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        tv_more=(TextView) itemView.findViewById(R.id.tv_more);
        ll_more = (LinearLayout) itemView.findViewById(R.id.ll_more);
    }
}