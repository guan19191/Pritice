package sgcf.zz.com.pritice;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import java.util.List;

import retrofit2.Call;
import sgcf.zz.com.pritice.activity.Test2Activity;
import sgcf.zz.com.pritice.activity.TestActivity;
import sgcf.zz.com.pritice.adapter.HomeAdapter;
import sgcf.zz.com.pritice.bean.BaseResponseBean;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.net.MyRetrofitCallBack;
import sgcf.zz.com.pritice.net.RetrofitFactory;
import sgcf.zz.com.pritice.net.ApiService;
import sgcf.zz.com.pritice.util.LogUtils;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rv_home;
    private ApiService apiService;
    private Call<BaseResponseBean<HomeArticleListBean>> homeArticle;
    private Button btnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
        LogUtils.e("测试LogUtil");
    }


    private void initView() {
        rv_home = (RecyclerView) findViewById(R.id.rv_home);
        btnTest = findViewById(R.id.btn_test);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_home.setLayoutManager(linearLayoutManager);

        apiService = RetrofitFactory.getInstance().getService();
        homeArticle = apiService.getHomeArticle(-1);
        homeArticle.enqueue(new MyRetrofitCallBack<BaseResponseBean<HomeArticleListBean>, HomeArticleListBean>() {
            @Override
            public void onSuccess(HomeArticleListBean data) {
                Log.e(TAG, "onSuccess: " + data.toString());
//                HomeArticleListBean articleListBean = (HomeArticleListBean) data;
                List<HomeArticleListBean.DatasBean> articleDataList = data.getDatas();
                if (articleDataList != null && !articleDataList.isEmpty()) {
                    HomeAdapter homeAdapter = new HomeAdapter(articleDataList, MainActivity.this);
                    rv_home.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onFailed(boolean isServerResponse, int code, String errorMsg) {
                Log.e(TAG, "onFailed: isServerResponse" + isServerResponse + "code" + code + "errorMsg" + errorMsg);
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
            }
        });


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
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
