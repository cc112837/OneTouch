package com.wzy.mhealth.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/11 14:38
 * 修改人：Administrator
 * 修改时间：2016/10/11 14:38
 * 修改备注：
 */
public class MaternityParentVIew extends ListView {
    public MaternityParentVIew(Context context) {
        super(context);
    }

    public MaternityParentVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaternityParentVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
