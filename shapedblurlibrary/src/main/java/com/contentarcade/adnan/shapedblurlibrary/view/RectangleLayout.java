package com.contentarcade.adnan.shapedblurlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Adnan Naeem on 14/09/18.
 */

public class RectangleLayout extends FrameLayout {

    int heightOfRect=500;
    int widthOfRect=0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Path path = new Path();

    public RectangleLayout(Context context) {
        this(context, null);
    }

    public RectangleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RectangleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

        paint.setXfermode(pdMode);
        path.reset();


        // defaults for understanding this formula
//        float left = 0, top = 0; // basically (X1, Y1)
//        float right = left + 600; // width (distance from X1 to X2)
//        float bottom = top + 600; // height (distance from Y1 to Y2)
//        RectF myRect = new RectF(left, top, right, bottom);
//        // getWidth()/2 is half of screen - half of rect to set X position
//       canvas.translate((getWidth()/2)-(right/2), (getHeight()/2)-(bottom/2));


        widthOfRect = getWidth();

        float left = 0, top = 0; // basically (X1, Y1)
        float right = left + widthOfRect; // width (distance from X1 to X2)
        float bottom = top + heightOfRect; // height (distance from Y1 to Y2)
        RectF myRect = new RectF(left, top, right, bottom);
        // getWidth()/2 is half of screen - half of rect to set X position
        canvas.translate((getWidth()/2)-(right/2), (getHeight()/2)-(bottom/2));


        canvas.drawRect(myRect, paint);
        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);
    }

    public void resetShapeSize(int size){
            this.heightOfRect = size;
            this.invalidate();
    }

    public int circleMaxRadius(){
        return this.widthOfRect/2;
    }

}