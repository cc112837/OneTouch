package com.wzy.mhealth.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.wzy.mhealth.R;

public class CircularImage extends MaskedImage {  
    public CircularImage(Context paramContext) {  
        super(paramContext);  
        setImageResource(R.mipmap.default_icon);
    }  
  
    public CircularImage(Context paramContext, AttributeSet paramAttributeSet) {  
        super(paramContext, paramAttributeSet); 
        setImageResource(R.mipmap.default_icon);
    }  
  
    public CircularImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {  
        super(paramContext, paramAttributeSet, paramInt);  
        setImageResource(R.mipmap.default_icon);
    }  
    

    
  
    public Bitmap createMask() {  
        int i = getWidth();  
        int j = getHeight();  
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;  
        Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);  
        Canvas localCanvas = new Canvas(localBitmap);  
        Paint localPaint = new Paint(1);
        //// TODO: 2016/2/18 (修改颜色)
        localPaint.setColor(16777216);  //修改的颜色
        float f1 = getWidth();  
        float f2 = getHeight();  
        RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);  
        localCanvas.drawOval(localRectF, localPaint);  
        return localBitmap;  
    }  
}
