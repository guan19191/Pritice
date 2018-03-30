package sgcf.zz.com.pritice.SecondMVP;

import android.util.Log;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

public class RxExceptionHandler<T extends Throwable> implements Consumer<T> {

    private static String TAG = "RxExceptionHandler";


    private static final String TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String JSON_EXCEPTION = "数据获取异常";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";

    private static final String TOKEN_EXCEPTION = "身份信息验证失败！";

    private Consumer<? super Throwable> onError;


    public RxExceptionHandler(Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    @Override
    public void accept(T t) {
        t.printStackTrace();
        if (t instanceof SocketTimeoutException) {
            Log.e(TAG, "onError: SocketTimeoutException---");

        } else if (t instanceof ConnectException) {
            Log.e(TAG, "onError: CONNECT_EXCEPTION---");

        } else if (t instanceof UnknownHostException) {
            Log.e(TAG, "onError: UNKNOWN_HOST_EXCEPTION---");

        } else if (t instanceof JSONException) {
            Log.e("TAG", "onError: JSON_EXCEPTION");

        } else if (t instanceof BaseException) {
            Log.e("TAG", "onError: TokenException");

        } else {
            try {
                onError.accept(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}