package sgcf.zz.com.pritice.NewWork;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sgcf.zz.com.pritice.Bean.BaseResponseBean;
import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;
import sgcf.zz.com.pritice.Bean.UsefulSiteBean;


/**
 * Created by admin
 * Date:  2018/3/8.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public interface ApiService {

    @GET("article/list/1/json")
    Observable<HomeArticleBean> getCall();

    @GET("article/list/{pageIndex}/json")
    Observable<HomeArticleBean> getArticle(@Path("pageIndex") int pageIndex);

    @GET("banner/json")
    Observable<HomeBannerBean> getBanner();

    @GET("friend/json")
    Observer<BaseResponseBean<UsefulSiteBean>> getUsefulSite();
}
