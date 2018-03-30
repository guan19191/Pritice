package sgcf.zz.com.pritice.FirstMVP;

import android.view.View;
import android.widget.ListView;

import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.adapter.ArticleAdapter;

/**
 * Created by admin
 * Date:  2018/3/30.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */
public class ArticleFragment extends BaseFragment<ArticleView, ArticlePresenter<ArticleView>> implements ArticleView {

    private ListView rvArticleList;
    private ArticleAdapter articleAdapter;
    int pageIndex = 0;

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        rvArticleList = rootView.findViewById(R.id.rv_article_list);
    }

    @Override
    public void initData() {
        super.initData();
        if (mPresenter != null) {
            mPresenter.getArticleData(pageIndex);
        }
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void showArticleData(HomeArticleBean data) {
        if (data.getData() != null) {
            articleAdapter = new ArticleAdapter(data.getData().getDatas(), getActivity());
            rvArticleList.setAdapter(articleAdapter);
        }
    }

    @Override
    public void setBanner(HomeBannerBean bannerBean) {

    }

    @Override
    public void loadMoreData(HomeArticleBean homeArticleBean) {

    }

    @Override
    protected ArticlePresenter<ArticleView> createPresenter() {
        return new ArticlePresenter<>();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_article;
    }
}
