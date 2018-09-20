package com.contentarcade.adnan.shapedblurview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.contentarcade.adnan.shapedblurlibrary.GaussianBlur;
import com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout;

public class MainActivity extends AppCompatActivity {

    private SeekBar radiusSeekbar;
    private SeekBar sizeSeekbar;
    private SeekBar blurAreaSeekbar;
    private ImageView actualImage;
    private ImageView blurredImage;
    TextView radius_txt, size_txt;
    int viewHeight;
    int viewWeight;
    int progressofCircle = 250;
    int progressofSquare = 500;
    int progressofRectangle = 500;
    int progressofCut = 500;
    ShapeLayout shapeLayout ;//= new shapeLayout(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actualImage = (ImageView) findViewById(R.id.actual_image);
        blurredImage = (ImageView) findViewById(R.id.blurred_image);
        radius_txt = (TextView) findViewById(R.id.radius_label);
        size_txt = (TextView) findViewById(R.id.size_label);

        shapeLayout = (ShapeLayout) findViewById(R.id.shape_layout_overlay);

        actualImage.setImageResource(R.drawable.home);  //actual image and blured image must be save

        radius_seekbar_method();
        size_seekbar_method();
        blur_area_seekbar_method();

        radiusSeekbar.setProgress(GaussianBlur.MAX_RADIUS);
        blurAreaSeekbar.setProgress(progressofSquare);
        radius_txt.setText("Radius ("+GaussianBlur.MAX_RADIUS+")");
        size_txt.setText("Radius ("+GaussianBlur.MAX_SIZE+")");

        applyBlurView(GaussianBlur.MAX_RADIUS, GaussianBlur.MAX_SIZE);
        shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.SQUARE);

    }

    private void applyBlurView(int r, int size) {
        GaussianBlur.with(this)
                .size(size)
                .radius(r)
                .put(R.drawable.home, blurredImage);
        // .put()  also accepts bitmap and drawable

    }


    // ----- Layout Views Click  Methods
    public void rectangle_click(View view) {
        shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.RECTANGLE);
        blurAreaSeekbar.setProgress(progressofRectangle);
    }

    public void circle_click(View view) {
        shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.CIRCLE);
        blurAreaSeekbar.setProgress(progressofCircle);
    }

    public void square_click(View view) {
        shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.SQUARE);
        blurAreaSeekbar.setProgress(progressofSquare);
    }

    public void cut_click(View view) {
        shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.CUT);
        blurAreaSeekbar.setProgress(progressofCut);
    }
    // ----- Layout Views Click  Methods

    // all seekbar methods --------------
    public void radius_seekbar_method(){


        radiusSeekbar = (AppCompatSeekBar) findViewById(R.id.blur_radius_seekbar);
        radiusSeekbar.setProgress(GaussianBlur.MAX_RADIUS);

        radiusSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                radius_txt.setText("Radius (" +progress+" )");

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (progressChangedValue>0){
                    shapeLayout.setVisibility(View.VISIBLE);
                    applyBlurView(progressChangedValue, sizeSeekbar.getProgress());
                }else{
                    shapeLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void size_seekbar_method(){
        sizeSeekbar = (SeekBar) findViewById(R.id.image_size_seekbar);
        sizeSeekbar.setProgress(GaussianBlur.MAX_SIZE);

        sizeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                size_txt.setText("Size (" +progress+" )");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                applyBlurView(radiusSeekbar.getProgress(), progressChangedValue );
            }
        });
    }

    public void blur_area_seekbar_method(){
        blurAreaSeekbar = (SeekBar) findViewById(R.id.blur_area_seekbar);
     //   Toast.makeText(this, "radius "+viewWeight, Toast.LENGTH_SHORT).show();
        blurAreaSeekbar.setMax(720);
        blurAreaSeekbar.setProgress(progressofSquare);

        blurAreaSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (shapeLayout.getType().equals(ShapeLayout.ShapeType.CIRCLE)){
                    progressofCircle = progress;
                }else if (shapeLayout.getType().equals(ShapeLayout.ShapeType.SQUARE)){
                    progressofSquare = progress;
                }
                else if (shapeLayout.getType().equals(ShapeLayout.ShapeType.RECTANGLE)){
                    progressofRectangle = progress;
                }
                progressChangedValue = progress;
                shapeLayout.resetShapeSize(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {

                //  applyBlur(radius_seekbar.getProgress(), progressChangedValue );
            }
        });
    }

}
