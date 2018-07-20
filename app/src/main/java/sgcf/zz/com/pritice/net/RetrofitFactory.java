package sgcf.zz.com.pritice.net;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sgcf.zz.com.pritice.app.Constant;
import sgcf.zz.com.pritice.app.MyApplication;

/**
 * Retrofit 工厂单例
 */
public class RetrofitFactory {
    private static RetrofitFactory instance;
    private final Retrofit retrofit;
    private static final String BASE_URL = Constant.WAN_ANDROID_URL;
    private static final int MAX_CACHE_SIZE = 10 * 1024 * 1024;
    private static final long readTime = 5 * 1000;

    private RetrofitFactory() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        File httpCacheDirectory = new File(MyApplication.mContext.getExternalCacheDir(), "MyCache");
//        client. .setCache(new Cache(httpCacheDirectory,10 * 1024 * 1024));
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder().addHeader("apikey", "abcdefghijkl").build();
//                return chain.proceed(newRequest);
//            }
//        }).build();//添加header部分
        CookieJar cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return null;
            }
        };


        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(readTime, TimeUnit.MILLISECONDS)
                .writeTimeout(readTime, TimeUnit.MILLISECONDS)
                .cache(new Cache(httpCacheDirectory, MAX_CACHE_SIZE))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }


}
