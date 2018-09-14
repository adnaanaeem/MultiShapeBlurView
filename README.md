# MultiShapeBlurView
This is an Android project. Easy and simple library to apply multy shaped blur filter on images.Like circular view, square view, rectangle view, cutView etc. The library lets you apply a fast blur effect on any images very fast because the image size will be scaled down before apply the blur effect. Doing it asynchronous or not.
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

<h4>Step #2. Add the dependency [See Latest Release](https://jitpack.io) </h4>

```
dependencies {
    implementation 'com.contentarcade.adnan.shapedblurview:+'
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

<h5>Step #4. Put blur method in your activity and call it form anywhere with its parameters </h5>

```
 private void applyBlurView(int r, int size) {
        GaussianBlur.with(this)
                .size(size)
                .radius(r)
                .put(R.drawable.home, blurredImage);
        // .put()  also accepts bitmap and drawable

    }
```

<h5>Step #5. Now time to call blur method to apply blur effect </h5>

```
 // call this method any where with its parameters
 int blurRadius = 10;
 int blurImageScaledDwonSize = 200;
  applyBlurView(blurRadius, blurImageScaledDwonSize);
```

```
//Synchronous blur
Bitmap blurredBitmap = GaussianBlur.with(context).render(R.mipmap.your_image);
imageView.setImageBitmap(blurredBitmap);
   
//Asynchronous blur
GaussianBlur.with(context).put(R.mipmap.your_image, imageView);

//Asynchronous with scaleDown and changing radius
GaussianBlur.with(context).size(300).radius(10).put(R.mipmap.your_image, imageView);
```

[For Sample File of Implementation guide click here](https://github.com/Adnan865/MultiShapeBlurView/tree/master/app/src/main/java/com/contentarcade/adnan/shapedblurview)



