package com.wzy.mhealth.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.inter.MyRecyItemClickListener;
import com.wzy.mhealth.model.Shop;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品首页的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:38
 * 修改备注：
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Shop.DataEntity> list;
    private MyRecyItemClickListener listener;

    public void setOnItemClickListener(MyRecyItemClickListener listener) {
        this.listener = listener;
    }


    public ShopAdapter(Context context, List<Shop.DataEntity> alllist) {
        this.context = context;
        this.list = alllist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ShopAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.shop_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ShopAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.tv_oldprice.setText("" + list.get(position).getProductOldPrice());
        viewHolder.tv_name.setText("" + list.get(position).getProductName());
        viewHolder.tv_newprice.setText("" + list.get(position).getProductNewPrice());
        ImageLoader.getInstance().displayImage(list.get(position).getProductImageSmall(), viewHolder.iv_home, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_name;
        public TextView tv_oldprice, tv_newprice;
        private ImageView iv_home;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_oldprice = (TextView) itemView.findViewById(R.id.tv_oldprice);
            iv_home = (ImageView) itemView.findViewById(R.id.iv_home);
            tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            tv_newprice = (TextView) itemView.findViewById(R.id.tv_newprice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}





