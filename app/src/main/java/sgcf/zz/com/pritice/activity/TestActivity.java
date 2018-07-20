package sgcf.zz.com.pritice.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import sgcf.zz.com.pritice.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //代码设置主题  便于封装
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //系统大于5.0
//                Window window = getWindow();
//                WindowManager.LayoutParams attributes = window.getAttributes();
//                attributes.flags |= flagTranslucentNavigation;
//                window.setAttributes(attributes);
//                getWindow().setStatusBarColor(Color.TRANSPARENT);
//            } else {
//                Window window = getWindow();
//                WindowManager.LayoutParams attributes = window.getAttributes();
//                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
//                window.setAttributes(attributes);
//            }


//        }

        //以上方法 设置主题后 可以达到沉浸式状态栏的效果 但是存在问题
        //1.如果 页面中有输入框  弹出键盘后 内容布局会被顶起来
        //2.如果手机有虚拟导航(虚拟按键 默认背景为黑色) 背景色会变成灰色

            /*该方案会导致一个问题就是导航栏颜色变灰。经测试，在 5.x 以下导航栏透明是可以生效的，
             但 5.x 以上导航栏会变灰色（正常情况下我们期望导航栏保持默认颜色黑色不变），
             但因为设置了FLAG_TRANSLUCENT_NAVIGATION，所以即使代码中设置 getWindow().setNavigationBarColor(Color.BLACK);
             也是不起作用的。但如果不设置该 FLAG ，状态栏又无法被置为隐藏和设置透明
             */
//        fullScreen(this);
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 添加状态栏占位视图
     *
     * @param activity
     */
    private void addStatusViewWithColor(Activity activity, int color) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight());
        statusBarView.setBackgroundColor(color);
        contentView.addView(statusBarView, lp);
    }


}
