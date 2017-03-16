package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Hospital;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：医院列表适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:36
 * 修改备注：
 */
public class HospitalAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Hospital.DataEntity> list;
    public HospitalAdapter(Context context, List<Hospital.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list=list;
        this.context=context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.hospital_item, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_tel = (TextView) convertView.findViewById(R.id.tv_tel);
            viewHolder.iv_tel=(ImageView) convertView.findViewById(R.id.iv_tel);
            viewHolder.iv_add=(ImageView) convertView.findViewById(R.id.iv_add);
            viewHolder.iv_taocan=(ImageView) convertView.findViewById(R.id.iv_taocan);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_tel.setText(list.get(position).getHospitalTelephone()+"");
        viewHolder.tv_name.setText(list.get(position).getHospitalName()+"");
        ImageLoader.getInstance().displayImage(list.get(position).getHospitalImage(),viewHolder.iv_taocan, PhotoUtils.avatarImage);
        viewHolder.iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list.get(position).getHospitalTelephone()));
                context.startActivity(intent);
            }
        });
        viewHolder.tv_address.setText(list.get(position).getHospitalAddress()+"");
        viewHolder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return convertView;
    }
    static class ViewHolder {
        public TextView tv_name;
        public  TextView tv_tel,tv_address;
        private ImageView iv_tel,iv_add,iv_taocan;
    }
}
