package com.wzy.mhealth.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.UpdaAddressActivity;
import com.wzy.mhealth.model.Address;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/9/14 13:48
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
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_address_item, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_tel = (TextView) convertView.findViewById(R.id.tv_tel);
            viewHolder.iv_add=(ImageView) convertView.findViewById(R.id.iv_add);
            viewHolder.tv_addressitem = (TextView) convertView.findViewById(R.id.tv_addressitem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_tel.setText("");
        viewHolder.tv_name.setText("");
        viewHolder.tv_addressitem.setText("");
        viewHolder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context, UpdaAddressActivity.class);
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
    }
}
