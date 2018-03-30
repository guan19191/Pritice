package sgcf.zz.com.pritice.SecondMVP;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M, V extends BaseContract.BaseView> {

    private CompositeDisposable disposables;// 管理Destroy取消订阅者者

    protected M mModel;
    protected V mView;

    protected Context mContext;

    public BasePresenter(V view) {
        mView = view;
        initContext(view);
        mModel = createModel();
    }

    protected void initContext(V view) {
        if (view instanceof Activity) {
            //Activity
            mContext = (Activity) view;
        } else {
            mContext = ((Fragment) view).getActivity();
        }

    }

    public boolean addRx(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
        return true;
    }

    public Context getContext() {
        return mContext;
    }

    protected abstract M createModel();


    public void detachView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }


}