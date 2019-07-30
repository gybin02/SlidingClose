# slidingclose
向右滑动关闭界面（仿iOS）


大概效果就是， Activity 向右滑动，滑动超过屏幕的一半，就关闭，否则，恢复原来的状态。

解决了**滑动冲突**。

## 截图
![](https://raw.githubusercontent.com/wangchenyan/slidingclose/master/art/screenshot.gif)

## 源码解析
### 配置透明主题
要想 Activity 滑出屏幕后不遮挡下层 Activity ，需设置透明主题
```
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>

<style name="AppTheme.Slide" parent="@style/AppTheme">
    <!--Required-->
    <item name="android:windowBackground">@android:color/transparent</item>
    <item name="android:windowIsTranslucent">true</item>
    <item name="android:windowAnimationStyle">@style/AppTheme.Slide.Animation</item>
</style>

<style name="AppTheme.Slide.Animation" parent="@android:style/Animation.Activity">
    <item name="android:activityOpenEnterAnimation">@anim/anim_slide_in</item>
    <item name="android:activityOpenExitAnimation">@anim/anim_slide_out</item>
    <item name="android:activityCloseEnterAnimation">@anim/anim_slide_in</item>
    <item name="android:activityCloseExitAnimation">@anim/anim_slide_out</item>
</style>
```
如果需要滑动关闭则指定 Activity 的 theme 为 `AppTheme.Slide` ，否则使用 `AppTheme` 。

这里也添加了 Activity 切换动画，增强体验。


### SlideActivity
继承自 AppCompatActivity ，作为滑动关闭 Activity 的基类，主要是做了绑定操作。
```
public class SlidingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }
}
```
如果不需要滑动关闭，则重写 `enableSliding` 并返回 false 。

## 使用
-     compile 'com.babytree.tool:slide-close:0.0.0.2-SNAPSHOT'
 
- 将 Activity 的基类继承 SlideActivity 。
- 将需要滑动关闭的 Activity 的 theme 指定为 `AppTheme.Slide` 。
- 将不需要滑动关闭的 Activity （如 App 主界面）的 theme 指定为 `AppTheme` ，重写 `enableSliding` 并返回 false 。
