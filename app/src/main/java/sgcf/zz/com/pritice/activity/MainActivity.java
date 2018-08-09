package sgcf.zz.com.pritice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import sgcf.zz.com.pritice.MvpView.MainView;
import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.adapter.HomeAdapter;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.bean.HomeBannerDataBean;
import sgcf.zz.com.pritice.mvp.base.BaseMvpActivity;
import sgcf.zz.com.pritice.presenter.MainPresenter;
import sgcf.zz.com.pritice.widget.LoadingDialog;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rv_home;


    private Button btnTest;
    private HomeAdapter homeAdapter;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        loadingDialog = new LoadingDialog(this);
        initView();
        loadingDialog.showDialog();
    }


    public void initView() {
        rv_home = (RecyclerView) findViewById(R.id.rv_home);
        btnTest = findViewById(R.id.btn_test);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home.setLayoutManager(linearLayoutManager);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        if (mPresenter != null) {
            mPresenter.getArticleList(0);
            mPresenter.getBannerData();
        }
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_article;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void showBanner(List<HomeBannerDataBean> data) {

    }

    List<HomeArticleListBean.DatasBean> articleList;

    @Override
    public void showArticleList(List<HomeArticleListBean.DatasBean> datas) {
        if (homeAdapter == null) {
            articleList = datas;
            homeAdapter = new HomeAdapter(articleList, MainActivity.this);
            rv_home.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
            rv_home.setAdapter(homeAdapter);
            homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<Integer> list, int position) {
                    
                }
            });
        } else {
            articleList.addAll(datas);
            homeAdapter.notifyDataSetChanged();
        }

        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void setBottomNavigation() {

    }

    @Override
    public void showLoading() {
        if (loadingDialog != null) {
            loadingDialog.showDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showErrorToast(String errorMsg) {

    }






}
