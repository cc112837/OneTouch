package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.avos.avoscloud.AVAnalytics;

/**
 * 项目名称：mhealth
 * 类描述：基类
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/12 10:02
 * 修改人：Administrator
 * 修改时间：2016/8/12 10:02
 * 修改备注：
 */
public class BaActivity extends Activity {
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
