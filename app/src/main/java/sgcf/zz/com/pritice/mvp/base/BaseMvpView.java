package sgcf.zz.com.pritice.mvp.base;

/**
 */
public interface BaseMvpView {
    void showLoading();

    void hideLoading();

    void showErrorToast(String errorMsg);
}
