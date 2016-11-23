package com.wzy.mhealth.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wzy.mhealth.R;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/11/23 9:47
 * 修改人：Administrator
 * 修改时间：2016/11/23 9:47
 * 修改备注：
 */
public class HeaderHolder extends RecyclerView.ViewHolder {
    public ImageView shop_header;
    public HeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        shop_header = (ImageView) itemView.findViewById(R.id.shop_header);
    }
}
