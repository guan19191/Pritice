package sgcf.zz.com.pritice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;

import java.util.List;

import sgcf.zz.com.pritice.FirstMVP.ArticleFragment;
import sgcf.zz.com.pritice.FirstMVP.BaseActivity;
import sgcf.zz.com.pritice.FirstMVP.HomePresenter;
import sgcf.zz.com.pritice.FirstMVP.HomeView;
import sgcf.zz.com.pritice.R;

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {

    private FragmentTabHost tabHome;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        viewPager = findViewById(R.id.view_pager);
        tabHome = findViewById(R.id.tab_home);
    }

    @Override
    public void initData() {
        super.initData();
        ArticleFragment articleFragment = new ArticleFragment();
    }


    @Override
    public void initListener() {
        super.initListener();
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public void setTabView(List<Fragment> fragmentList) {

    }
}
