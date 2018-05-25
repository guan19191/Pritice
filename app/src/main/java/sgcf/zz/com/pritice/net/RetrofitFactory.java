package sgcf.zz.com.pritice.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sgcf.zz.com.pritice.NewWork.ApiService;

/**
 *
 */

public class RetrofitFactory {
    private static final String URL = "";
    private static RetrofitFactory mFactory;
    private final Retrofit mRetrofit;

    private RetrofitFactory() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        //拦截器添加  见文章第8条
//        builder.addInterceptor(new CommonInterceptor());
        OkHttpClient client = builder.build();
        //自定义MyGsonConverterFactory 见文章第3条
        mRetrofit = new Retrofit.Builder().baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static RetrofitFactory getFactory() {
        if (mFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mFactory == null) {
                    mFactory = new RetrofitFactory();
                }
            }
        }
        return mFactory;
    }

    public ApiService createService() {
        return mRetrofit.create(ApiService.class);
    }
}
