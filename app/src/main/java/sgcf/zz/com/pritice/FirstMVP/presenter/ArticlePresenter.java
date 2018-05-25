package sgcf.zz.com.pritice.FirstMVP.presenter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.FirstMVP.view.ArticleView;
import sgcf.zz.com.pritice.NewWork.Api;

/**
 * Created by admin
 * Date:  2018/3/21.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public class ArticlePresenter<A> extends BasePresenter<ArticleView> {
    private static final String TAG = ArticlePresenter.class.getSimpleName();


//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//    {
//        if (BuildConfig.DEBUG) {
//            // Log信息拦截器
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
//
//            //设置 Debug Log 模式
//            builder.addInterceptor(loggingInterceptor);
//        }
//    }
//
//    OkHttpClient client = builder.build();
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .baseUrl(Constant.BASE_ANDROID_URL)
//            .client(client)
//            .build();

    public void getArticleData(int pageIndex) {
        Observable<HomeArticleBean> articleData = Api.getInstance().getArticleData(pageIndex);
        articleData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeArticleBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeArticleBean homeArticleBean) {
                if (homeArticleBean != null && getView() != null) {
                    getView().showArticleData(homeArticleBean);
                }

                Log.e(TAG, homeArticleBean != null ? homeArticleBean.toString() : null);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable<HomeBannerBean> banner = Api.getInstance().getBanner();
        banner.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeBannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBannerBean homeBannerBean) {
                if (homeBannerBean != null && getView() != null) {
                    getView().setBanner(homeBannerBean);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void loadMoreData(int pageIndex) {
        Observable<HomeArticleBean> articleData = Api.getInstance().getArticleData(pageIndex);
        articleData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeArticleBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeArticleBean homeArticleBean) {
                if (homeArticleBean != null && getView() != null) {
                    getView().loadMoreData(homeArticleBean);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
