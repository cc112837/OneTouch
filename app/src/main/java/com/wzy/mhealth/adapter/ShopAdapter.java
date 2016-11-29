package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.holder.DescHolder;
import com.wzy.mhealth.holder.HeaderHolder;
import com.wzy.mhealth.model.Shop;
import com.wzy.mhealth.utils.MyUtils;
import com.wzy.mhealth.utils.ToastUtil;

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
public class ShopAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {

    public List<Shop.DataEntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;//记录下哪个section是被打开的

    public ShopAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(List<Shop.DataEntity> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return MyUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).getProductData().size();
        return MyUtils.isEmpty(allTagList.get(section).getProductData()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.image_header, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescHolder(mInflater.inflate(R.layout.shop_list_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(HeaderHolder holder, int section) {
        ImageLoader.getInstance().displayImage(allTagList.get(section).getShowImage(), holder.shop_header, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
        holder.shop_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mContext, "点击了区头");
            }
        });

    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescHolder holder, final int section, final int position) {
        holder.tv_oldprice.setText("原价：¥" + allTagList.get(section).getProductData().get(position).getProductOldPrice());
        holder.tv_name.setText("" + allTagList.get(section).getProductData().get(position).getProductName());
        holder.tv_newprice.setText("现价：¥" + allTagList.get(section).getProductData().get(position).getProductNewPrice());
        ImageLoader.getInstance().displayImage(allTagList.get(section).getProductData().get(position).getProductImageSmall(), holder.iv_home, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShopDetailActivity.class);
                intent.putExtra("id", allTagList.get(section).getProductData().get(position).getProductId() + "");
                mContext.startActivity(intent);
            }
        });
    }
}








