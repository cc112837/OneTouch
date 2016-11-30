package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.UpdaAddressActivity;
import com.wzy.mhealth.model.Address;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：收货地址的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:36
 * 修改备注：
 */
public class AddressAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Address.DataEntity> list;
    public AddressAdapter(Context context, List<Address.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.list_address_item, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_tel = (TextView) convertView.findViewById(R.id.tv_tel);
            viewHolder.iv_add=(ImageView) convertView.findViewById(R.id.iv_add);
            viewHolder.cb_check=(CheckBox) convertView.findViewById(R.id.cb_check);
            viewHolder.tv_addressitem = (TextView) convertView.findViewById(R.id.tv_addressitem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(list.get(position).getSid()==1){
            viewHolder.cb_check.setChecked(true);
        }else{
            viewHolder.cb_check.setChecked(false);
        }

        viewHolder.tv_tel.setText(list.get(position).getTelephone()+"");
        viewHolder.tv_name.setText(list.get(position).getName()+"");
        viewHolder.tv_addressitem.setText(list.get(position).getArea()+list.get(position).getAddress()+"");
        viewHolder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context, UpdaAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id",list.get(position));
                intent.putExtras(bundle);
                intent.putExtra("flag","update");
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    static class ViewHolder {
        public TextView tv_name;
        public  TextView tv_tel,tv_addressitem;
        private ImageView iv_add;
        private CheckBox cb_check;
    }
}
