package sgcf.zz.com.pritice.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sgcf.zz.com.pritice.bean.BaseResponseBean;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;

/**
 */
public interface Service {
    //首页文章列表
    @GET("article/list/{index}/json")
    Call<ResponseBody> getHomeArticle(@Path("index") int pageIndex);
}
