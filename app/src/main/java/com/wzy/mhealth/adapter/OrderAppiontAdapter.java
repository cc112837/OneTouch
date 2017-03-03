package com.wzy.mhealth.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.OrderAppiont;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：医生预约适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/23 14:35
 * 修改人：Administrator
 * 修改时间：2016/6/23 14:35
 * 修改备注：
 */
public class OrderAppiontAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<OrderAppiont.DataEntity> list;
    private Handler handler;

    public OrderAppiontAdapter(Context context, List<OrderAppiont.DataEntity> list, Handler handler) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.handler = handler;
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.order_item_appiont, null);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.zhicheng = (TextView) convertView.findViewById(R.id.zhicheng);
            viewHolder.department = (TextView) convertView.findViewById(R.id.department);
            viewHolder.hospital = (TextView) convertView.findViewById(R.id.hospital);
            viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
            viewHolder.et_name = (TextView) convertView.findViewById(R.id.et_name);
            viewHolder.et_address = (TextView) convertView.findViewById(R.id.et_address);
            viewHolder.tv_appiont = (TextView) convertView.findViewById(R.id.tv_appiont);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getDoctorImage(), viewHolder.photo, PhotoUtils.avatarImage);
        viewHolder.name.setText(list.get(position).getDoctorName());
        viewHolder.zhicheng.setText(list.get(position).getDoctorTitle());
        viewHolder.department.setText(list.get(position).getFirstdep());
        viewHolder.hospital.setText(list.get(position).getHospital());
        viewHolder.tv_data.setText(list.get(position).getClinicTime());
        viewHolder.et_name.setText(list.get(position).getClinicName());
        viewHolder.et_address.setText(list.get(position).getAdrress());
        if ( "0".equals(list.get(position).getClinicStatu())) {
            viewHolder.tv_appiont.setEnabled(true);
            viewHolder.tv_appiont.setText("点击排队就诊");
            viewHolder.tv_appiont.setBackgroundResource(R.drawable.textview_1);
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.tv_appiont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = Constants.SERVER_URL + "PatientAppointStatusServlet";
                    TiUser tiUser = new TiUser();
                    tiUser.setName(list.get(position).getClinicId());
                    MyHttpUtils.handData(handler, 305, url, tiUser);
                    finalViewHolder.tv_appiont.setText("正在排队就诊");
                    finalViewHolder.tv_appiont.setBackgroundResource(R.drawable.textview3);
                }
            });
        } else if ("2".equals(list.get(position).getClinicStatu())) {
            viewHolder.tv_appiont.setEnabled(false);
            viewHolder.tv_appiont.setText("未在预约时间段");
            viewHolder.tv_appiont.setBackgroundResource(R.drawable.textview3);
        } else if("1".equals(list.get(position).getClinicStatu())){
            viewHolder.tv_appiont.setEnabled(false);
            viewHolder.tv_appiont.setText("正在排队就诊");
            viewHolder.tv_appiont.setBackgroundResource(R.drawable.textview3);
        }
        else{
            viewHolder.tv_appiont.setEnabled(false);
            viewHolder.tv_appiont.setText("已结束就诊");
            viewHolder.tv_appiont.setBackgroundResource(R.drawable.textview3);
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView photo;
        public TextView name, zhicheng, department, hospital, tv_data, et_name, et_address, tv_appiont;

    }

}
