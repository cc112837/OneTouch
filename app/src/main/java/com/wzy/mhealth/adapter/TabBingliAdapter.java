package com.wzy.mhealth.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class TabBingliAdapter extends BaseAdapter {

	private String names;
	private String sexs;
	private String yearsold;
	private String marriages;
	private String haschilds;
	private String shoushus;
	private Context con;
	
	public TabBingliAdapter(String n,String s,String y,String m,String h,String shou,Context c){
		this.names = n;
		this.sexs = s;
		this.yearsold = y;
		this.marriages = m;
		this.shoushus=shou;
		this.haschilds=h;
		this.con = c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = LayoutInflater.from(con);
		View item = mInflater.inflate(R.layout.tab_bingli_list_item, null);
       
        ImageView imgArrow = (ImageView)item.findViewById(R.id.interact_listitem_arrow_icon);
        
        TextView name = (TextView)item.findViewById(R.id.bingli_listitem_name);
        TextView sex = (TextView)item.findViewById(R.id.interact_listitem_sex);
        TextView year = (TextView)item.findViewById(R.id.interact_listitem_year);
        TextView marriage = (TextView)item.findViewById(R.id.bingli_listitem_marriage);
        TextView child = (TextView)item.findViewById(R.id.interact_listitem_child);        
        TextView shoushu = (TextView)item.findViewById(R.id.interact_listitem_shoushu);
      
        name.setText(names);
        sex.setText(sexs);
        year.setText(yearsold);
        marriage.setText(marriages);
        child.setText(haschilds);
        shoushu.setText(shoushus);
        imgArrow.setImageResource(R.mipmap.arrow_right);
   
        return item;
    }
}
