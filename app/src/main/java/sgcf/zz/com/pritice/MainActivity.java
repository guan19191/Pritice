package sgcf.zz.com.pritice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sgcf.zz.com.pritice.bean.BaseResponseBean;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.net.RetrofitFactory;
import sgcf.zz.com.pritice.net.Service;
import sgcf.zz.com.pritice.util.LogUtils;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rv_home;
    private Service service;
    private Call<ResponseBody> homeArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
        LogUtils.e("测试LogUtil");
    }


    private void initView() {
        rv_home = (RecyclerView) findViewById(R.id.rv_home);
        service = RetrofitFactory.getInstance().getService();
        homeArticle = service.getHomeArticle(0);

        homeArticle.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "onResponse: " + response.body().string());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (homeArticle != null) {
            homeArticle.cancel();
        }
    }
}
