package com.wzy.mhealth.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.TaocanEntity;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 首页推荐套餐
*/
public class TaocanHomeAdapter extends BaseAdapter {

    private Context context;
    private List<TaocanEntity.DataEntity> list;
    private LayoutInflater mInflater;

    public TaocanHomeAdapter(Context context, List<TaocanEntity.DataEntity> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return MyUtils.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_iten_hometaocan, null);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tv_oldprice = (TextView) convertView.findViewById(R.id.tv_oldprice);
            viewHolder.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            viewHolder.tv_newprice = (TextView) convertView
                    .findViewById(R.id.tv_newprice);
            viewHolder.tv_sale = (TextView) convertView
                    .findViewById(R.id.tv_sale);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName() + "");
        ImageLoader.getInstance().displayImage(list.get(position).getImage(), viewHolder.iv_img, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
        viewHolder.tv_oldprice.setText("原价：¥" + list.get(position).getOldPrice());
        viewHolder.tv_newprice.setText("优惠价：¥" + list.get(position).getNewPrice());
        viewHolder.tv_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        viewHolder.tv_sale.setText("已售出：" + list.get(position).getTaocanNum());
        return convertView;

    }

    static class ViewHolder {
        public ImageView iv_img;
        public TextView tv_name, tv_sale;
        public TextView tv_oldprice, tv_newprice;
    }

}
