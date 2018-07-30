package sgcf.zz.com.pritice.MvpView;

import java.util.List;

import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.bean.HomeBannerDataBean;
import sgcf.zz.com.pritice.mvp.base.BaseMvpView;

/**
 *
 */
public interface MainView extends BaseMvpView {
    void showBanner(List<HomeBannerDataBean> data);
    void showArticleList(List<HomeArticleListBean.DatasBean> datas);
    void setBottomNavigation();
}
