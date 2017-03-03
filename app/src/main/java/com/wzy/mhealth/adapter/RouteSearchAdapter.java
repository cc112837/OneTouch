package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.wzy.mhealth.R;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 路线查找
*/
public class RouteSearchAdapter extends BaseAdapter {
	private Context context;
	private List<PoiItem> poiItems = null;
	private LayoutInflater mInflater;

	public RouteSearchAdapter(Context context, List<PoiItem> poiItems) {
		this.context = context;
		this.poiItems = poiItems;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return MyUtils.isEmpty(poiItems) ? 0 : poiItems.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.poi_result_list, null);
		}

		TextView PoiName = ((TextView) convertView.findViewById(R.id.poiName));
		TextView poiAddress = (TextView) convertView
				.findViewById(R.id.poiAddress);
		PoiName.setText(poiItems.get(position).getTitle());
		String address = null;
		if (poiItems.get(position).getSnippet() != null) {
			address = poiItems.get(position).getSnippet();
		} else {
			address = "中国";
		}
		poiAddress.setText("地址:" + address);
		return convertView;
	}

}
