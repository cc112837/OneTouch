package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.TaocanDetailAcitivty;
import com.wzy.mhealth.activities.ZhixingIntroduceActivity;
import com.wzy.mhealth.holder.ContentHolder;
import com.wzy.mhealth.holder.FootHolder;
import com.wzy.mhealth.holder.TaocanHeaderHolder;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：套餐的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:38
 * 修改备注：
 */
public class TaocanAllAdapter extends SectionedRecyclerViewAdapter<TaocanHeaderHolder, ContentHolder, FootHolder> {

    public List<Tijian.DataEntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;//记录下哪个section是被打开的

    public TaocanAllAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(List<Tijian.DataEntity> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return MyUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).getTaocanId().size();
        if (count >= 2 && !mBooleanMap.get(section)) {
            count = 2;
        }
        return MyUtils.isEmpty(allTagList.get(section).getTaocanId()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return true;
    }

    @Override
    protected TaocanHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new TaocanHeaderHolder(mInflater.inflate(R.layout.list_taocan_item, parent, false));
    }

    @Override
    protected FootHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new FootHolder(mInflater.inflate(R.layout.taocan_footer_item, parent, false));
    }

    @Override
    protected ContentHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(mInflater.inflate(R.layout.list_item_fuchan_content, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(TaocanHeaderHolder holder, final int section) {
        switch (allTagList.get(section).getLevel()) {
            case 5:
                holder.iv_5.setImageResource(R.mipmap.goldstar1);
            case 4:
                holder.iv_4.setImageResource(R.mipmap.goldstar1);
            case 3:
                holder.iv_3.setImageResource(R.mipmap.goldstar1);
            case 2:
                holder.iv_2.setImageResource(R.mipmap.goldstar1);
            case 1:
                holder.iv_1.setImageResource(R.mipmap.goldstar1);
            default:
                break;
        }
        holder.tv_address.setText(allTagList.get(section).getAdress() + "");
        holder.tv_nametijan.setText(allTagList.get(section).getCenterName() + "");
        ImageLoader.getInstance().displayImage(allTagList.get(section).getImg(), holder.iv_taocan, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImage);
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhixingIntroduceActivity.class);
                intent.putExtra("content", allTagList.get(section).getDetails() + "");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected void onBindSectionFooterViewHolder(final FootHolder holder, final int section) {

        holder.ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? "查看剩余" + (allTagList.get(section).getTaocanId().size() - 2) + "个套餐" : "收起";
                mBooleanMap.put(section, !isOpen);
                holder.tv_more.setText(text);
                notifyDataSetChanged();
            }
        });
        holder.tv_more.setText(mBooleanMap.get(section) ? "收起" : "查看剩余" + (allTagList.get(section).getTaocanId().size() - 2) + "个套餐");
    }

    @Override
    protected void onBindItemViewHolder(ContentHolder holder, final int section, final int position) {
        holder.beforeprice.setText("¥" + allTagList.get(section).getTaocanId().get(position).getNewPrice() + "");
        holder.afterprice.setText("¥" + allTagList.get(section).getTaocanId().get(position).getOldPrice() + "");
        holder.name.setText(allTagList.get(section).getTaocanId().get(position).getName() + "");
        holder.tv_sale.setText("已售套餐：" + allTagList.get(section).getTaocanId().get(position).getTaocanNum());
        holder.ll_taocancontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TaocanDetailAcitivty.class);
                intent.putExtra("id", allTagList.get(section).getTaocanId().get(position).getTaoId() + "");
                mContext.startActivity(intent);
            }
        });
    }

}








