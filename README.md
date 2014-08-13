# CircleProgessView

It's a progressbar UI component that looks similar to the android L progress bar (material design).

![alt text](https://raw.githubusercontent.com/sockeqwe/CircleProgressBar/master/gif/demo.gif "Sample")


## Dependency

```
compile 'com.hannesdorfmann:circleprogressview:1.0.1'
```

## Usage

In XML:
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      >

    <com.hannesdorfmann.CircleProgressView
        android:layout_width="60dp"
        android:layout_height="60dp"
        
        app:cpvColor="@color/blue"
        app:cpvCircleAnimDuration="4000"
        app:cpvSweepAnimDuration="800"
        app:cpvStrokeWidth="2dp"
        />
 
</FrameLayout>

```


You can also set the look in the theme:

```xml
<style name="SpecialTheme" parent="android:Theme.Holo.Light.DarkActionBar">
    <item name="cpvColor">#12AAFF</item>
    <item name="cpvStrokeWidth">3dp</item>
    <item name="cpvCircleAnimDuration">1500</item>
    <item name="cpvSweepAnimDuration">500</item>
</style>
``` 
