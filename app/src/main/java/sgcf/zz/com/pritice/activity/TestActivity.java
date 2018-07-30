package sgcf.zz.com.pritice.activity;

import android.app.ActionBar;
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
import sgcf.zz.com.pritice.util.MyStatusBarUtil;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MyStatusBarUtil.transparencyStatusBar(this);
//        MyStatusBarUtil.fullScreen(this);

//设置 paddingTop
//        ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0, getStatusBarHeight(mActivity), 0, 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //5.0 以上直接设置状态栏颜色
//            activity.getWindow().setStatusBarColor(color);
//        } else {
//            //根布局添加占位状态栏
//            ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
//            View statusBarView = new View(activity);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    getStatusBarHeight(activity));
//            statusBarView.setBackgroundColor(color);
//            decorView.addView(statusBarView, lp);
//        }






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
