# MultiShapeBlurView [![](https://jitpack.io/v/Adnan865/MultiShapeBlurView.svg)](https://jitpack.io/#Adnan865/MultiShapeBlurView)

This is an Android project. Easy and simple library to apply multi shaped blur filter on images.Like circular view, square view, rectangle view and cutView. The library lets you apply a fast blur effect on any images very fast because the image size will be scaled down before apply the blur effect. Doing it asynchronous or not.
<h2>Sample app</h2>
Please check the sample app and feel free to ask any thing related to this library.

<h2>Setup</h2>
<h4>Step #1. Add the JitPack repository to your build file:</h4>

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

<h4>Step #2. Add the dependency [See Latest Release]</h4> [![](https://jitpack.io/v/Adnan865/MultiShapeBlurView.svg)](https://jitpack.io/#Adnan865/MultiShapeBlurView)


```
dependencies {
	        implementation 'com.github.Adnan865:MultiShapeBlurView:1.0.1'
	}
```
<h4>Step #3. Add the below lines on app module build.gradle file. </h4>

```
defaultConfig {
    ...
    renderscriptTargetApi 19
    renderscriptSupportModeEnabled true
}
```

<h1>Implementation</h1>

<h5>Step #4. Add actual imageview which you want  to blur after hat place <b>shapelayout</b> for blur shape and then place blurred imagevIew in this layout </h5>

```
  <ImageView
            android:id="@+id/actual_image"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="0dp"
            android:scaleType="centerCrop" />

        <com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout
            android:id="@+id/shape_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">

            <ImageView
                android:id="@+id/blurred_imageView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="centerCrop" />

        </com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout>

```

<h5>Step #5. There are multiple shapes layout you can choose single one or all its up to you </h5>
 <h6><b>ShapeLayout</b> is universal layout to get all the views. you can switch views on run time</h6> 

```
  <com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout
            android:id="@+id/shape_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">
  ```
  
<h6>Just add <b>ShapeLayout</b> and then switch views in activity using this statements</h6>

     ShapeLayout shapeLayout = (ShapeLayout) findViewById(R.id.shape_layout_overlay);
     
     shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.RECTANGLE); // by default
     // or 
     shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.CIRCLE);
     // or 
     shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.SQUARE);
     // or
     shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.CUT);

<h5>Step #6. Now put blur method in your activity and call it form anywhere with its parameters </h5>

```
 private void applyBlurView(int r, int size) {
        GaussianBlur.with(this)
                .size(size)
                .radius(r)
                .put(R.drawable.home, blurred_imageView);
        // .put()  also accepts bitmap and drawable

    }
```

<h5>Step #7. Now time to call blur method to apply blur effect </h5>

```
 int blurRadius = 10;
 int blurImageScaledDwonSize = 200;
 actualImage.setImageResource(R.drawable.home);  //actual image and blured image must be set
 applyBlurView(blurRadius, blurImageScaledDwonSize);
```

<h3>If you want to use only specfic shape blur then use these.</h3>

<h4>Circular Blur View.</h4>

```
  <com.contentarcade.adnan.shapedblurlibrary.view.CircleLayout
            android:id="@+id/circle_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">
```
<h4>Rectangular Blur View.</h4>

```
 <com.contentarcade.adnan.shapedblurlibrary.view.RectangleLayout
            android:id="@+id/rectangle_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">
``` 

<h4>Square Blur View.</h4>

```
 <com.contentarcade.adnan.shapedblurlibrary.view.SquareLayout
            android:id="@+id/square_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">
``` 

<h4>AngleCut Blur View.</h4>

```
 <com.contentarcade.adnan.shapedblurlibrary.view.CutLayout
            android:id="@+id/cut_layout_overlay"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:elevation="10dp">
``` 



Click Here For [Sample Layout](https://github.com/Adnan865/MultiShapeBlurView/tree/master/app/src/main/java/com/contentarcade/adnan/shapedblurview) File or  [Implementation](https://github.com/Adnan865/MultiShapeBlurView/tree/master/app/src/main/res/layout) guide




