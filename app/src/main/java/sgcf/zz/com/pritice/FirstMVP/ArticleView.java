package sgcf.zz.com.pritice.FirstMVP;

import sgcf.zz.com.pritice.Bean.HomeArticleBean;
import sgcf.zz.com.pritice.Bean.HomeBannerBean;

/**
 * Created by admin
 * Date:  2018/3/21.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public interface ArticleView {
    void showArticleData(HomeArticleBean data);

    void setBanner(HomeBannerBean bannerBean);

    void loadMoreData(HomeArticleBean homeArticleBean);
}
