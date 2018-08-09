package sgcf.zz.com.pritice.net;

import android.support.annotation.NonNull;
import android.util.Log;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sgcf.zz.com.pritice.bean.BaseResponseBean;

/**
 *
 */
public abstract class MyRetrofitCallBack<T extends BaseResponseBean<D>, D> implements Callback<T> {


    private static final String TAG = "CallBack";

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            Log.e(TAG, "onResponse: " + response.toString());
            if (response.body() != null) {
                //code为0时 服务器返回成功
                if (response.body().getErrorCode() == 0) {
                    try {
                        D d = response.body().getData();
                        onSuccess(d);
                    } catch (Exception e) {
                        e.printStackTrace();
                        onFailed(false, response.code(), "出现异常");
                    }
                } else {
                    onFailed(true, response.body().getErrorCode(), response.body().getErrorMsg());
                }
            }
        } else {
            onFailed(false, response.code(), response.message());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t instanceof SocketTimeoutException) {
            onFailed(false, -1, "连接超时");
        } else {
            onFailed(false, -2, "网络错误");
        }

    }

    public abstract void onSuccess(D data);

    public abstract void onFailed(boolean isServerResponse, int code, String errorMsg);
}
