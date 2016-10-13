package com.wzy.mhealth.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/10/11 14:40
 * 修改人：Administrator
 * 修改时间：2016/10/11 14:40
 * 修改备注：
 */
public class MaternityChildView extends ListView{
    public MaternityChildView(Context context) {
        super(context);
    }

    public MaternityChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaternityChildView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
