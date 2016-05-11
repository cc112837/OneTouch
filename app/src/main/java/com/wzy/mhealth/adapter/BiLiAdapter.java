package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.NewsMedi;

import java.util.List;


/**
 * Created by cc112837@163.com on 2016/3/29.
 */
public class BiLiAdapter extends  BaseAdapter{
    private List<NewsMedi.TngouEntity> list;
    private Context context;
    private LayoutInflater inflater;

    public void clear(){
        list.clear();
    }
    public void setList(List<NewsMedi.TngouEntity> list) {
        this.list = list;
    }

    public BiLiAdapter(List<NewsMedi.TngouEntity> list,Context context) {
        this.list=list;
        this.context=context;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
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
        ViewHolder viewholder = null;
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = inflater.inflate(R.layout.newsmedi_item, null);
            viewholder.iv_img = ((ImageView) convertView.findViewById(R.id.iv_img));
            viewholder.tv_title = ((TextView) convertView.findViewById(R.id.tv_title));
            viewholder.tv_content = ((TextView) convertView.findViewById(R.id.tv_content));
            //添加标记
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
//        BitmapUtils bitmapUtils=new BitmapUtils(context);
//        bitmapUtils.display(viewholder.iv_img, "http://tnfs.tngou.net/image"+list.get(position).getImg());
//                viewholder.tv_title.setText(list.get(position).getTitle());
        viewholder.tv_content.setText(list.get(position).getDescription());
        return convertView;
    }
    final static class ViewHolder {
        ImageView iv_img;
        TextView tv_title;
        TextView tv_content;

    }
}
