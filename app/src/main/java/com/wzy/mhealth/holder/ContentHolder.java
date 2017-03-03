package com.wzy.mhealth.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

/**
 * 项目名称：mhealth
 * 类描述：套餐展示条目
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 11:31
 * 修改人：Administrator
 * 修改时间：2016/11/23 11:31
 * 修改备注：
 */
public class ContentHolder extends RecyclerView.ViewHolder{
    public LinearLayout ll_taocancontent;
    public TextView name;
    public  TextView afterprice;
    public TextView beforeprice,tv_sale;
    public ContentHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        ll_taocancontent=(LinearLayout) itemView.findViewById(R.id.ll_taocancontent);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_sale=(TextView) itemView.findViewById(R.id.tv_sale);
        afterprice = (TextView) itemView.findViewById(R.id.tv_afterprice);
        beforeprice = (TextView) itemView.findViewById(R.id.tv_beforeprice);
        afterprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
    }
}
