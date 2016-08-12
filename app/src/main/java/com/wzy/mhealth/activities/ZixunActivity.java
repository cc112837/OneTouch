package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wzy.mhealth.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ZixunActivity extends BaActivity {
    private ListView list;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> fukemap = new HashMap<String, Object>();
        fukemap.put("ItemImage", R.mipmap.zixun_fuke_icon_btn);//
        lstImageItem.add(fukemap);

        HashMap<String, Object> chankemap = new HashMap<String, Object>();
        chankemap.put("ItemImage", R.mipmap.zixun_chanke_icon_btn);//
        lstImageItem.add(chankemap);
        HashMap<String, Object> childmap = new HashMap<String, Object>();
        childmap.put("ItemImage", R.mipmap.zixun_erke_icon_btn);//
        lstImageItem.add(childmap);

        HashMap<String, Object> nankemap = new HashMap<String, Object>();
        nankemap.put("ItemImage", R.mipmap.zixun_nanke_icon_btn);// 男科
        lstImageItem.add(nankemap);

        HashMap<String, Object> pifumap = new HashMap<String, Object>();
        pifumap.put("ItemImage", R.mipmap.zixun_pifuke_icon_btn);// 皮肤
        lstImageItem.add(pifumap);
        HashMap<String, Object> neikemap = new HashMap<String, Object>();
        neikemap.put("ItemImage", R.mipmap.zixun_neike_icon_btn);// 内科
        lstImageItem.add(neikemap);

        HashMap<String, Object> waimap = new HashMap<String, Object>();
        waimap.put("ItemImage", R.mipmap.zixun_waike_icon_btn);// 外科
        lstImageItem.add(waimap);
        HashMap<String, Object> gushangmap = new HashMap<String, Object>();
        gushangmap.put("ItemImage", R.mipmap.zixun_gushangke_icon_btn);// 骨伤
        lstImageItem.add(gushangmap);

        HashMap<String, Object> yankemap = new HashMap<String, Object>();
        yankemap.put("ItemImage", R.mipmap.zixun_yanke_icon_btn);// 眼科
        lstImageItem.add(yankemap);


        HashMap<String, Object> erbimap = new HashMap<String, Object>();
        erbimap.put("ItemImage", R.mipmap.zixun_erbike_icon_btn);// 耳鼻
        lstImageItem.add(erbimap);
        HashMap<String, Object> koumap = new HashMap<String, Object>();
        koumap.put("ItemImage", R.mipmap.zixun_kouke_icon_btn);// 口腔
        lstImageItem.add(koumap);

        HashMap<String, Object> zhengxmap = new HashMap<String, Object>();
        zhengxmap.put("ItemImage", R.mipmap.zixun_zhengxingke_icon_btn);// 整形
        lstImageItem.add(zhengxmap);

        HashMap<String, Object> zhongmap = new HashMap<String, Object>();
        zhongmap.put("ItemImage", R.mipmap.zixun_zhongliuke_icon_btn);// 肿瘤
        lstImageItem.add(zhongmap);

        HashMap<String, Object> jingmap = new HashMap<String, Object>();
        jingmap.put("ItemImage", R.mipmap.zixun_jingske_icon_btn);// 精神
        lstImageItem.add(jingmap);

        HashMap<String, Object> zhongyimap = new HashMap<String, Object>();
        zhongyimap.put("ItemImage", R.mipmap.zixun_zhongyike_icon_btn);// 中医
        lstImageItem.add(zhongyimap);

        HashMap<String, Object> yingyangmap = new HashMap<String, Object>();
        yingyangmap.put("ItemImage", R.mipmap.zixun_yingyangke_icon_btn);// 营养
        lstImageItem.add(yingyangmap);

        HashMap<String, Object> baogaomap = new HashMap<String, Object>();
        baogaomap.put("ItemImage", R.mipmap.zixun_jieduke_icon_btn);// 报告
        lstImageItem.add(baogaomap);

        HashMap<String, Object> jiyinmap = new HashMap<String, Object>();
        jiyinmap.put("ItemImage", R.mipmap.zixun_jiyinke_icon_btn);// 基因
        lstImageItem.add(jiyinmap);
        // 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应

        SimpleAdapter saImageItems = new SimpleAdapter(ZixunActivity.this,
                lstImageItem,// 数据来源
                R.layout.night_item,// night_item的XML实现
                // 动态数组与ImageItem对应的子项
                new String[]{"ItemImage"},
                // ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage});
        gridview.setAdapter(saImageItems);
        // 添加消息处理
        gridview.setOnItemClickListener(new ItemClickListener());
        // 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

    }


    class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0,// The AdapterView where the
                                // click happened
                                View arg1,// The view within the AdapterView that was clicked
                                int arg2,// The position of the view in the adapter
                                long arg3// The row id of the item that was clicked
        ) {
            // 在本例中arg2=arg3
            HashMap<String, Object> item = (HashMap<String, Object>) arg0
                    .getItemAtPosition(arg2);
            Intent intent = new Intent();
            //
            Bundle bundle = new Bundle();
            bundle.putString("keshi", arg2 + 4 + "");
            intent.setClass(ZixunActivity.this, DoctorListActivity.class);

            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
