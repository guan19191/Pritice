package sgcf.zz.com.pritice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.NewWork.ApiService;
import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.app.Constant;

public class RxJavaExampleActivity extends AppCompatActivity {

    private static final java.lang.String TAG = RxJavaExampleActivity.class.getSimpleName();
    TextView tvData;
    private Retrofit retrofit;

    // 设置变量
    // 可重试次数
    private int maxConnectCount = 10;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_example);
//        ButterKnife.bind(this);
        tvData = findViewById(R.id.tv_data);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_ANDROID_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observable<HomeArticleBean> observable = retrofit.create(ApiService.class).getCall();

//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<HomeArticleBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(HomeArticleBean data) {
//                        tvData.setText(data.toString());
//                        Log.e(TAG, data.toString() + "");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "请求失败");
//                        e.printStackTrace();
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


        /**
         *轮询
         */
//        Observable.interval(2, 1, TimeUnit.SECONDS)
//                .doOnNext(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e(TAG, "第 " + aLong + " 次轮询");
//                    }
//                })
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        Log.e(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "对Complete事件作出响应");
//                    }
//                });


        /**
         *重试
         */
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        // 输出异常信息
                        Log.d(TAG, "发生异常 = " + throwable.toString());

                        /**
                         * 需求1：根据异常类型选择是否重试
                         * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                         */

                        if (throwable instanceof IOException) {
                            Log.e(TAG, "属于IO异常，需重试");
                            /**
                             * 需求2：限制重试次数
                             * 即，当已重试次数 < 设置的重试次数，才选择重试
                             */
                            if (currentRetryCount < maxConnectCount) {

                                // 记录重试次数
                                currentRetryCount++;
                                Log.e(TAG, "重试次数 = " + currentRetryCount);

                                /**
                                 * 需求2：实现重试
                                 * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                 *
                                 * 需求3：延迟1段时间再重试
                                 * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                 *
                                 * 需求4：遇到的异常越多，时间越长
                                 * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                 */
                                // 设置等待时间
                                waitRetryTime = 1000 + currentRetryCount * 1000;
                                Log.e(TAG, "等待时间 =" + waitRetryTime);
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);

                            } else {

                                // 若已重试次数> 设置重试次数，则不重试
                                // 通过发送error来停止重试（可在观察者的onError（）中获取信息）
                                return Observable.error(new Throwable("重试次数已超过设置次数 = " + currentRetryCount + "，即 不再重试"));

                            }


                        } else {
                            throwable.printStackTrace();
                            if (throwable instanceof HttpException) {
                                Log.e(TAG, throwable.getMessage());
                                return Observable.error(throwable);
                            } else {
                                Log.e(TAG, "发生了非网络异常");
                                return Observable.error(new Throwable("发生了非网络异常(非IO异常)"));
                            }
                        }

                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeArticleBean homeArticleBean) {
                        // 接收服务器返回的数据
                        Log.e(TAG, "发送成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 获取停止重试的信息
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
