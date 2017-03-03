package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.OrderInfoActivity;
import com.wzy.mhealth.model.AppointDoctor;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：预约医生时间选择适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/2/10 15:50
 * 修改人：Administrator
 * 修改时间：2017/2/10 15:50
 * 修改备注：
 */

public class OrderDoctorAdapter extends RecyclerView.Adapter<OrderDoctorAdapter.MyFriViewHolder> {
    private Context context;
    private List<AppointDoctor.DataEntity> frilist;
    private LayoutInflater inflater;
    public String id;

    public String getId() {
        return id;
    }

    public  void setId(String id) {
       this.id=id;
    }

    public OrderDoctorAdapter(Context context, List<AppointDoctor.DataEntity> frilist) {
        this.context = context;
        this.frilist = frilist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyFriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyFriViewHolder holder = new MyFriViewHolder(LayoutInflater.from(
                context).inflate(R.layout.oeder_doctor_item, parent,
                false));
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.ll_a.getLayoutParams();
        params.width = (width - 40) / 8;
        holder.ll_a.setLayoutParams(params);//将设置好的布局参数应用到控件中
        return holder;
    }

    @Override
    public void onBindViewHolder(MyFriViewHolder holder, final int position) {
        if (position == 0) {
            holder.tv_am.setText("上午");
            holder.tv_am.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv_am.setBackgroundResource(R.color.white);
            holder.tv_am.setEnabled(false);
            holder.tv_pm.setEnabled(false);
            holder.tv_pm.setText("下午");
            holder.tv_pm.setBackgroundResource(R.color.white);
            holder.tv_pm.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv_data.setText("");
        } else {
            if (Integer.parseInt(frilist.get(position - 1).getAppointAfter()) > 0) {
                holder.tv_am.setText("立即\n预约");
                holder.tv_am.setEnabled(true);

                holder.tv_am.setTextColor(context.getResources().getColor(R.color.white));
                holder.tv_am.setBackgroundResource(R.color.title_green);
                holder.tv_am.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, OrderInfoActivity.class);
                        intent.putExtra("time",frilist.get(position-1).getAppointTime());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id",getId());
                        intent.putExtra("flag","上午");
                        context.startActivity(intent);
                    }
                });
            } else {
                holder.tv_am.setEnabled(false);
                holder.tv_am.setText("");
                holder.tv_am.setBackgroundResource(R.color.white);
            }
            if (Integer.parseInt(frilist.get(position - 1).getAppointFore()) > 0) {
                holder.tv_pm.setText("立即\n预约");
                holder.tv_pm.setEnabled(true);
                holder.tv_pm.setTextColor(context.getResources().getColor(R.color.white));
                holder.tv_pm.setBackgroundResource(R.color.title_green);
                holder.tv_pm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, OrderInfoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("time",frilist.get(position-1).getAppointTime());
                        intent.putExtra("id",getId());
                        intent.putExtra("flag","下午");
                        context.startActivity(intent);
                    }
                });
            } else {
                holder.tv_pm.setText("");
                holder.tv_pm.setEnabled(false);
                holder.tv_pm.setBackgroundResource(R.color.white);
            }
            holder.tv_data.setText(frilist.get(position - 1).getAppointTime());
        }
    }

    @Override
    public int getItemCount() {
        if (frilist != null)
            return frilist.size();
        return 0;
    }

    class MyFriViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_data, tv_am, tv_pm;
        private LinearLayout ll_a;

        public MyFriViewHolder(View itemView) {
            super(itemView);
            ll_a = (LinearLayout) itemView.findViewById(R.id.ll_a);
            tv_data = ((TextView) itemView.findViewById(R.id.tv_data));
            tv_am = ((TextView) itemView.findViewById(R.id.tv_am));
            tv_pm = ((TextView) itemView.findViewById(R.id.tv_pm));
        }
    }
}
