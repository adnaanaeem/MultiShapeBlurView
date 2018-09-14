package com.contentarcade.adnan.shapedblurlibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Adnan Naeem on 14/09/18.
 */

public class CutLayout extends FrameLayout {

    int heightOfCut = 200;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Path path = new Path();

    public CutLayout(Context context) {
        this(context, null);
    }

    public CutLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CutLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

        paint.setXfermode(pdMode);
        path.reset();
      //  path.addCircle( getWidth()/2, getHeight()/2, 300, Path.Direction.CW);

        //Defining 4 points to clear with PorterDuff.Mode.CLEAR
        path.moveTo(0, 0);
        path.lineTo(0, (float) (getHeight() * 0.66));
        path.lineTo(getWidth(), (float) (getHeight() * 0.33));
        path.lineTo(getWidth(), 0);

        path.close();
        canvas.drawPath(path, paint);
        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);
    }

    public void resetShapeSize(int size){
        this.heightOfCut = size+200;
        this.invalidate();
    }


}