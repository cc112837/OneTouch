package com.wzy.mhealth.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2016/3/31 11:24
*/
public class PackageUtils {
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String getPackageVersion(Context context){
        String version = "1.0";
        //获取PackageManager
        PackageManager packageManager = context.getPackageManager();

        try {
            //获取PackageInfo
            PackageInfo packageInfo =
                    packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            //获取版本号
            version = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return  version;
    }
}
