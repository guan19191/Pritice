package sgcf.zz.com.pritice.NewWork;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.BuildConfig;
import sgcf.zz.com.pritice.app.Constant;

/**
 * Created by admin
 * Date:  2018/3/22.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public class Api {
    public static Api instance;

    private ApiService service;

    public Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        OkHttpClient okHttpSingletonInstance = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_ANDROID_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpSingletonInstance)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
//        if (instance == null)
//            instance = new Api();
        return instance;
    }

    public Observable<HomeArticleBean> getArticleData(int pageIndex) {
        return service.getArticle(pageIndex);
    }

    public Observable<HomeBannerBean> getBanner() {
        return service.getBanner();
    }
}
