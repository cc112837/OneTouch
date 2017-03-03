package com.wzy.mhealth.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

/**
 * 项目名称：mhealth
 * 类描述：商品条目展示
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 10:10
 * 修改人：Administrator
 * 修改时间：2016/11/23 10:10
 * 修改备注：
 */
public class DescHolder extends RecyclerView.ViewHolder {
    public TextView tv_name;
    public TextView tv_oldprice, tv_newprice;
    public ImageView iv_home;
    public LinearLayout ll_content;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        ll_content=(LinearLayout) itemView.findViewById(R.id.ll_content);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_oldprice = (TextView) itemView.findViewById(R.id.tv_oldprice);
        iv_home = (ImageView) itemView.findViewById(R.id.iv_home);
        tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        tv_newprice = (TextView) itemView.findViewById(R.id.tv_newprice);
    }
}