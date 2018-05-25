package sgcf.zz.com.pritice.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import sgcf.zz.com.pritice.activity.ArticleDetailActivity;
import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.FirstMVP.base.BaseActivity;
import sgcf.zz.com.pritice.FirstMVP.presenter.ArticlePresenter;
import sgcf.zz.com.pritice.FirstMVP.view.ArticleView;
import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.adapter.ArticleAdapter;
import sgcf.zz.com.pritice.adapter.HomeTopAdapter;

public class MainActivity extends BaseActivity<ArticleView, ArticlePresenter<ArticleView>> implements ArticleView, OnRefreshListener, OnLoadMoreListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    TextView tvContent;
    private ListView lvArticle;
    private ArticleAdapter articleAdapter;
    private List<HomeArticleBean.DataBean.DatasBean> list;
    private HomeArticleBean.DataBean articleData;
    private HomeTopAdapter homeTopAdapter;
    private BGABanner banner_guide_content;
    private ImageView iv_top;
    private SmartRefreshLayout refreshLayout;


    int pageIndex = 0;
    private int totalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_article);
    }

    @Override
    protected ArticlePresenter<ArticleView> createPresenter() {
        return new ArticlePresenter<>();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_article;
    }

    @Override
    public void initView() {
        super.initView();
        lvArticle = (ListView) findViewById(R.id.lv_article);
        banner_guide_content = (BGABanner) findViewById(R.id.banner_guide_content);
        refreshLayout = findViewById(R.id.refreshLayout);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        rvBanner.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {
        super.initListener();

        refreshLayout.setOnRefreshListener(this);

        refreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        if (mPresenter != null) {
            mPresenter.getArticleData(pageIndex);
        }
    }

    @Override
    public void showArticleData(HomeArticleBean data) {


        articleData = data.getData();
        totalPage = data.getData().getTotal();
        list = data.getData().getDatas();
        articleAdapter = new ArticleAdapter(list, this);
        lvArticle.setAdapter(articleAdapter);
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        lvArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, "onItemClick: " + list.get(position).getLink());
                Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("url", list.get(position).getLink());
                startActivity(intent);
            }
        });

    }


    @Override
    public void setBanner(HomeBannerBean bannerBean) {
        if (bannerBean.getData() != null) {
            Log.i(TAG, "setBanner: " + bannerBean.getData().size());
//            homeTopAdapter = new HomeTopAdapter(bannerBean.getData(), this);
//            rvBanner.setAdapter(homeTopAdapter);
            banner_guide_content.setAdapter(new BGABanner.Adapter<ImageView, HomeBannerBean.DataBean>() {
                @Override
                public void fillBannerItem(BGABanner banner, ImageView itemView, HomeBannerBean.DataBean model, int position) {

                    Glide.with(MainActivity.this).load(model.getImagePath()).into(itemView);
                }
            });
            banner_guide_content.setData(bannerBean.getData(), null);
        } else {
            banner_guide_content.setVisibility(View.GONE);
        }

    }

    @Override
    public void loadMoreData(HomeArticleBean homeArticleBean) {
        if (list != null) {
            list.addAll(homeArticleBean.getData().getDatas());
            articleAdapter.notifyDataSetChanged();
            refreshLayout.finishLoadMore();
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        pageIndex = 1;
        if (mPresenter != null) {
            mPresenter.getArticleData(pageIndex);
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        int nextPage = pageIndex + 1;
        if (mPresenter != null) {
            if (nextPage <= totalPage) {
                mPresenter.loadMoreData(nextPage);
            }

        }

    }

}
