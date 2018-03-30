package sgcf.zz.com.pritice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import sgcf.zz.com.pritice.R;

public class RxJavaActivity extends AppCompatActivity {

    private static final java.lang.String TAG = "RxJava";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);



        /**
         * 按步骤调用
         */
        //1、创建 被观察者对象  observable
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onNext(4);
//                emitter.onComplete();
//            }
//        });
//        //2、创建Observer 并定义响应事件行为
//        Observer observer = new Observer<Integer>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.e(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.e(TAG, "对Next事件" + integer + "作出响应");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "对Complete事件作出响应");
//            }
//        };
//        //3、通过订阅（subscribe）连接 观察者 和被观察者
//        observable.subscribe(observer);


        /**
         * 链式调用
         */
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onComplete();
//                emitter.onError(new Exception("error"));
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "开始采用subscribe连接");

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "对Next事件" + s + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "对onComplete事件作出响应");
            }
        });


    }


}
