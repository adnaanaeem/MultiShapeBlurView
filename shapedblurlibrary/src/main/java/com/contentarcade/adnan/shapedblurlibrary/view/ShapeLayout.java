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

public class ShapeLayout extends FrameLayout {



    // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Drawing_shapes
    String type  = ShapeType.SQUARE;
    int radiusOfCircle = ShapeSize.CIRCLE_SIZE_DEFAULT;
    int heightOfRect= ShapeSize.RECTANGLE_SIZE_DEFAULT;
    int widthOfRect=0;
    int sizeOfSquare= ShapeSize.SQUARE_SIZE_DEFAULT;
    float heightOfCut =ShapeSize.CUT_SIZE_DEFAULT;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);


    public ShapeLayout(Context context) {
        this(context, null);
    }

    public ShapeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShapeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        Path path = new Path();
        paint.setXfermode(pdMode);
        path.reset();

        if (type.equals(ShapeType.SQUARE)){
            float left = 0, top = 0; // basically (X1, Y1)
            float right = left + sizeOfSquare; // width (distance from X1 to X2)
            float bottom = top + sizeOfSquare; // height (distance from Y1 to Y2)
            RectF myRect = new RectF(left, top, right, bottom);
            // getWidth()/2 is half of screen - half of rect to set X position
            canvas.translate((getWidth()/2)-(right/2), (getHeight()/2)-(bottom/2));
            canvas.drawRect(myRect, paint);
        }

        else if (type.equals(ShapeType.RECTANGLE)){
            widthOfRect = getWidth();
            float left = 0, top = 0; // basically (X1, Y1)
            float right = left + widthOfRect; // width (distance from X1 to X2)
            float bottom = top + heightOfRect; // height (distance from Y1 to Y2)
            RectF myRect = new RectF(left, top, right, bottom);
            // getWidth()/2 is half of screen - half of rect to set X position
            canvas.translate((getWidth()/2)-(right/2), (getHeight()/2)-(bottom/2));
            canvas.drawRect(myRect, paint);
        }

        else if (type.equals(ShapeType.CIRCLE)){
            path.addCircle( getWidth()/2, getHeight()/2, radiusOfCircle, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
        else if (type.equals(ShapeType.CUT)){
            //Defining 4 points to clear with PorterDuff.Mode.CLEAR
            path.moveTo(0, 0);

//            path.lineTo(0, (float) (getHeight() * 0.66));
//            path.lineTo(getWidth(), (float) (getHeight() * 0.33));
//            path.lineTo(getWidth(), 0);

            path.lineTo(0, (float) (heightOfCut * 0.66));  // move left side up
            path.lineTo(getWidth(), (float) (heightOfCut * 0.33)); // move right side of line up
            path.lineTo(getWidth(), 0);

            path.close();
            canvas.drawPath(path, paint);
        }

        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);
    }

    public void resetShapeSize(int size){
        if (type.equals(ShapeType.SQUARE)){
            this.sizeOfSquare = size;
        }else if(type.equals(ShapeType.RECTANGLE)){
            this.heightOfRect = size;
        }else if (type.equals(ShapeType.CIRCLE)){
            this.radiusOfCircle = size;
        }
        else if (type.equals(ShapeType.CUT)){
            this.heightOfCut = size+200;
        }
       this.invalidate();
    }

    public void setTypeOfShape(String typeOfShape){
            this.type = typeOfShape;
            this.invalidate();
    }
    public String getType(){
        return type;
    }

    // shape class --------------------------------------
    public class ShapeType{
        public static final String SQUARE = "square";
        public static final String CIRCLE = "circle";
        public static final String RECTANGLE = "rectangle";
        public static final String CUT = "cut";
    }

    public class ShapeSize{
        public static final int SQUARE_SIZE_DEFAULT = 500;
        public static final int CIRCLE_SIZE_DEFAULT = 250;
        public static final int RECTANGLE_SIZE_DEFAULT = 500;
        public static final int CUT_SIZE_DEFAULT = 200;
    }
}