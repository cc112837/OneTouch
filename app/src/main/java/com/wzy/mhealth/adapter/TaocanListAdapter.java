package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Tijian;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/23 14:35
 * 修改人：Administrator
 * 修改时间：2016/6/23 14:35
 * 修改备注：
 */
public class TaocanListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<Tijian.DataEntity> list;

    public TaocanListAdapter(Context context, List<Tijian.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = mInflater.inflate(R.layout.list_taocan_item, null);
            viewHolder.tv_nametijan = (TextView) convertView.findViewById(R.id.tv_nametijan);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tv_sale = (TextView) convertView.findViewById(R.id.tv_sale);
            viewHolder.iv_taocan = (ImageView) convertView.findViewById(R.id.iv_taocan);
            viewHolder.iv_1 = (ImageView) convertView.findViewById(R.id.iv_1);
            viewHolder.iv_2 = (ImageView) convertView.findViewById(R.id.iv_2);
            viewHolder.iv_3 = (ImageView) convertView.findViewById(R.id.iv_3);
            viewHolder.iv_4 = (ImageView) convertView.findViewById(R.id.iv_4);
            viewHolder.iv_5 = (ImageView) convertView.findViewById(R.id.iv_5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (list.get(position).getLevel()){
            case 5:
                viewHolder.iv_5.setImageResource(R.mipmap.goldstar1);
            case 4:
                viewHolder.iv_4.setImageResource(R.mipmap.goldstar1);
            case 3:
                viewHolder.iv_3.setImageResource(R.mipmap.goldstar1);
            case 2:
                viewHolder.iv_2.setImageResource(R.mipmap.goldstar1);
            case 1:
                viewHolder.iv_1.setImageResource(R.mipmap.goldstar1);
                default:
                    break;
        }

        viewHolder.tv_sale.setText("已售套餐："+ list.get(position).getNum());
        viewHolder.tv_address.setText(list.get(position).getAdress() + "");
        viewHolder.tv_nametijan.setText(list.get(position).getName() + "");
        ImageLoader.getInstance().displayImage("", viewHolder.iv_taocan,com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImage);
        return convertView;
    }

     class ViewHolder {
        private ImageView iv_taocan, iv_1, iv_2, iv_3, iv_4, iv_5;
        public TextView tv_nametijan;
        public TextView tv_address;
        public TextView tv_sale;

    }

}
