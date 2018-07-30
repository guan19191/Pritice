package sgcf.zz.com.pritice.presenter;

import java.util.List;

import retrofit2.Call;
import sgcf.zz.com.pritice.MvpView.MainView;
import sgcf.zz.com.pritice.bean.BaseResponseBean;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.bean.HomeBannerDataBean;
import sgcf.zz.com.pritice.mvp.base.BasePresenter;
import sgcf.zz.com.pritice.net.ApiService;
import sgcf.zz.com.pritice.net.MyRetrofitCallBack;
import sgcf.zz.com.pritice.net.RetrofitFactory;

/**
 */
public class MainPresenter extends BasePresenter<MainView> {

    private ApiService service;


    public MainPresenter() {
//        service = RetrofitFactory.getInstance().getService();
        service = RetrofitFactory.getInstance().getService(ApiService.class);
    }

    public void getBannerData() {
        Call<BaseResponseBean<List<HomeBannerDataBean>>> homeBanner = service.getHomeBanner();
        homeBanner.enqueue(new MyRetrofitCallBack<BaseResponseBean<List<HomeBannerDataBean>>, List<HomeBannerDataBean>>() {

            @Override
            public void onSuccess(List<HomeBannerDataBean> data) {
                getView().showBanner(data);
            }

            @Override
            public void onFailed(boolean isServerResponse, int code, String errorMsg) {
                getView().showErrorToast(errorMsg);
            }
        });
    }

    public void getArticleList(int pageIndex) {
        service.getHomeArticle(pageIndex).enqueue(new MyRetrofitCallBack<BaseResponseBean<HomeArticleListBean>, HomeArticleListBean>() {
            @Override
            public void onSuccess(HomeArticleListBean data) {
                if (data != null && data.getDatas() != null && !data.getDatas().isEmpty()) {
                    getView().showArticleList(data.getDatas());
                }
            }

            @Override
            public void onFailed(boolean isServerResponse, int code, String errorMsg) {
                getView().showErrorToast(errorMsg);
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
