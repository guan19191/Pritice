package sgcf.zz.com.pritice.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import sgcf.zz.com.pritice.NewWork.OkHttp3Utils;

/**
 * Created by admin
 * Date:  2018/3/22.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

@SuppressLint("Registered")
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initRetrofit() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();//使用 gson coverter，统一日期请求格式

//
    }
}
