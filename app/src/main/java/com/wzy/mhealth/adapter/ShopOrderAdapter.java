package com.wzy.mhealth.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.ShopCommentActivity;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ShopOrder;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.MyUtils;
import com.wzy.mhealth.utils.ToastUtil;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品首页的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:38
 * 修改备注：
 */
public class ShopOrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<ShopOrder.DataEntity> list;
    private Handler handler;

    public ShopOrderAdapter(Context context, List<ShopOrder.DataEntity> list,Handler handler) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.handler=handler;
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_shoporder_item, null);
            viewHolder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            viewHolder.tv_orderstatus = (TextView) convertView.findViewById(R.id.tv_orderstatus);
            viewHolder.iv_car = (ImageView) convertView.findViewById(R.id.iv_car);
            viewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_shop);
            viewHolder.iv_order = (ImageView) convertView.findViewById(R.id.iv_order);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            viewHolder.tv_click1 = (TextView) convertView.findViewById(R.id.tv_click1);
            viewHolder.tv_click2 = (TextView) convertView.findViewById(R.id.tv_click2);
            viewHolder.tv_shoptitle = (TextView) convertView.findViewById(R.id.tv_shoptitle);
            viewHolder.tv_shopnum = (TextView) convertView.findViewById(R.id.tv_shopnum);
            viewHolder.tv_shoping = (TextView) convertView.findViewById(R.id.tv_shoping);
            viewHolder.ll_only = (LinearLayout) convertView.findViewById(R.id.ll_only);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ll_only.setVisibility(View.VISIBLE);
        viewHolder.ll_only.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("id", "" + list.get(position).getProductId());
                context.startActivity(intent);
            }
        });
        if ("待发货".equals(list.get(position).getShopStatus())) {
            viewHolder.iv_car.setVisibility(View.INVISIBLE);
            viewHolder.iv_delete.setVisibility(View.GONE);
            viewHolder.tv_click1.setVisibility(View.VISIBLE);
            viewHolder.tv_click2.setVisibility(View.VISIBLE);
            viewHolder.tv_click1.setText("提醒发货");
            viewHolder.tv_click2.setText("我要退款");
            viewHolder.tv_click2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.tv_click2.setText("退款中");
                    String url = Constants.SERVER_URL + "MhealthOrderBackServlet";
                    TiUser user = new TiUser();
                    user.setName("" + list.get(position).getOrderId());
                    MyHttpUtils.handData(handler, 294, url, user);
                }
            });
            viewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(context, "已提醒卖家尽快为您发货");
                    String url = Constants.SERVER_URL + "MhealthOrderExpetingServlet";
                    TiUser user = new TiUser();
                    user.setName("" + list.get(position).getOrderId());
                    MyHttpUtils.handData(handler, 292, url, user);
                }
            });
        } else if ("待收货".equals(list.get(position).getShopStatus())) {
            viewHolder.iv_car.setVisibility(View.VISIBLE);
            viewHolder.iv_delete.setVisibility(View.GONE);
            viewHolder.tv_click1.setVisibility(View.VISIBLE);
            viewHolder.tv_click2.setVisibility(View.VISIBLE);
            viewHolder.tv_click1.setText("查看物流");
            viewHolder.tv_click2.setText("确认收货");
            viewHolder.tv_click2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = Constants.SERVER_URL + "MhealthOrderGoodServlet";
                    TiUser user = new TiUser();
                    user.setName("" + list.get(position).getOrderId());
                    MyHttpUtils.handData(handler, 293, url, user);
                }
            });
            viewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(context, "查看物流");
                }
            });
        } else if("已完成".equals(list.get(position).getShopStatus())){
            viewHolder.iv_delete.setVisibility(View.VISIBLE);
            viewHolder.iv_car.setVisibility(View.VISIBLE);
            viewHolder.tv_click1.setVisibility(View.VISIBLE);
            viewHolder.tv_click2.setVisibility(View.VISIBLE);
            viewHolder.tv_click1.setText("再次购买");
            if ("0".equals(list.get(position).getEvaluateStatu())) {
                viewHolder.tv_click2.setText("评价订单");
                viewHolder.tv_click2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ShopCommentActivity.class);
                        intent.putExtra("image", list.get(position).getShopImage());
                        intent.putExtra("id", "" + list.get(position).getOrderId());
                        context.startActivity(intent);
                    }
                });
            } else {
                viewHolder.tv_click2.setText("已评价过");
                viewHolder.tv_click2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.show(context, "您已经评价过了");
                    }
                });
            }

            viewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopDetailActivity.class);
                    intent.putExtra("id", "" + list.get(position).getProductId());
                    context.startActivity(intent);
                }
            });

        }
        else{
            viewHolder.iv_delete.setVisibility(View.VISIBLE);
            viewHolder.iv_car.setVisibility(View.GONE);
            viewHolder.tv_click1.setVisibility(View.GONE);
            viewHolder.tv_click2.setVisibility(View.GONE);
        }
        viewHolder.tv_orderstatus.setText(list.get(position).getShopStatus() + "");//订单状态
        viewHolder.tv_shopname.setText(list.get(position).getBussinessName() + "");
        viewHolder.tv_shoping.setText(list.get(position).getLogistate() + "");
        viewHolder.tv_shoptitle.setText("" + list.get(position).getShopName());
        viewHolder.tv_shopnum.setText("数量：" + list.get(position).getShopNum());
        viewHolder.tv_number.setText("共" + list.get(position).getShopNum() + "件商品");
        viewHolder.tv_price.setText("实付款：¥" + String.format("%.2f", list.get(position).getShopPrice()));
        viewHolder.tv_time.setText("" + list.get(position).getShopDate());

        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("删除提示")
                        .setMessage("您确定要删除这个订单吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uri = Constants.SERVER_URL + "";
                        TiUser user = new TiUser();
                        user.setName(list.get(position).getOrderId() + "");
                        MyHttpUtils.handData(handler,295, uri, user);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        ImageLoader.getInstance().displayImage(list.get(position).getBussinessImage(), viewHolder.iv_order, PhotoUtils.avatarImageOption);
        ImageLoader.getInstance().displayImage(list.get(position).getShopImage(), viewHolder.iv_shop, PhotoUtils.avatarImageOptions);
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_shopname, tv_shoping, tv_time, tv_price, tv_number;
        public TextView tv_orderstatus, tv_click1, tv_click2, tv_shoptitle, tv_shopnum;
        private ImageView iv_order, iv_delete, iv_car, iv_shop;
        private LinearLayout ll_only;
    }
}
