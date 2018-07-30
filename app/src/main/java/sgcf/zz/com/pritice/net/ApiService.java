package sgcf.zz.com.pritice.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sgcf.zz.com.pritice.bean.BaseResponseBean;
import sgcf.zz.com.pritice.bean.HomeArticleListBean;
import sgcf.zz.com.pritice.bean.HomeBannerDataBean;

/**
 */
public interface ApiService {
    //首页文章列表
    @GET("article/list/{index}/json")
    Call<BaseResponseBean<HomeArticleListBean>> getHomeArticle(@Path("index") int pageIndex);

    //首页banner
    @GET("banner/json")
    Call<BaseResponseBean<List<HomeBannerDataBean>>> getHomeBanner();
}
