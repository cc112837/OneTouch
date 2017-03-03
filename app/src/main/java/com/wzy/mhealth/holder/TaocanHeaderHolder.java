package com.wzy.mhealth.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

/**
 * 项目名称：mhealth
 * 类描述：套餐头部展示
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 11:30
 * 修改人：Administrator
 * 修改时间：2016/11/23 11:30
 * 修改备注：
 */
public class TaocanHeaderHolder extends RecyclerView.ViewHolder{
    public LinearLayout header;
    public ImageView iv_taocan, iv_1, iv_2, iv_3, iv_4, iv_5;
    public TextView tv_nametijan;
    public TextView tv_address;
    public TaocanHeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        header=(LinearLayout) itemView.findViewById(R.id.header);
        tv_nametijan = (TextView) itemView.findViewById(R.id.tv_nametijan);
        tv_address = (TextView) itemView.findViewById(R.id.tv_address);
        iv_taocan = (ImageView) itemView.findViewById(R.id.iv_taocan);
       iv_1 = (ImageView) itemView.findViewById(R.id.iv_1);
        iv_2 = (ImageView) itemView.findViewById(R.id.iv_2);
        iv_3 = (ImageView) itemView.findViewById(R.id.iv_3);
        iv_4 = (ImageView) itemView.findViewById(R.id.iv_4);
        iv_5 = (ImageView) itemView.findViewById(R.id.iv_5);
    }
}
