package com.wzy.mhealth.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：体检
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/24 9:12
 * 修改人：Administrator
 * 修改时间：2016/6/24 9:12
 * 修改备注：
 */
public class TijianAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public TijianAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public TijianAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragments != null) {
            ret = fragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
