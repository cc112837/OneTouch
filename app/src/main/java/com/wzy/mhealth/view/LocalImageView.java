package com.wzy.mhealth.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.BannerItem;


/**
 * Created by Administrator on 2016/1/26.
 */
public class LocalImageView implements Holder<BannerItem> {
    private View view;
    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.shop_intro_item, null, false);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final BannerItem  data) {
        ((TextView)view.findViewById(R.id.tv_title)).setText(data.getTitle());
        ImageView imageView=(ImageView) view.findViewById(R.id.sdv_background);
        ImageLoader.getInstance().displayImage(data.getUrl(), imageView, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
    }
}