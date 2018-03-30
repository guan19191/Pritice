package sgcf.zz.com.pritice.SecondMVP;

/**
 * Created by admin
 * Date:  2018/3/19.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */

public interface BaseContract {

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void showError();

        void complete();
    }

}
