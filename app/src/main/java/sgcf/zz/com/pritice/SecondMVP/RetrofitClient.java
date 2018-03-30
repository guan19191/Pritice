package sgcf.zz.com.pritice.SecondMVP;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import sgcf.zz.com.pritice.NewWork.ApiService;
import sgcf.zz.com.pritice.NewWork.LoggingInterceptor;
import sgcf.zz.com.pritice.app.Constant;

public class RetrofitClient {


    private static RetrofitClient instance;

    private OkHttpClient mOkHttpClient;


    private Context mContext;

    private Retrofit mRetrofit;

    private ApiService mApiService;

    private RetrofitClient(Context context) {
        this.mContext = context;
    }

    public static RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }


    private OkHttpClient provideOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
                    .build();

//               .addInterceptor(new HttpParamInterceptor())  //公共参数的封装
//                    .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext))) //cookie 保存方案
        }


        return mOkHttpClient;
    }


    private Retrofit provideRetrofit() {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_ANDROID_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //RxJava2 的CallAdapter
                    .addConverterFactory(FastJsonConverterFactory.create())       // GsonConvertFactory
                    .client(provideOkHttpClient()).build();
        }

        return mRetrofit;
    }

    public ApiService provideApiService() {
        if (mApiService == null) {
            mApiService = provideRetrofit().create(ApiService.class);
        }
        return mApiService;
    }
}