package sgcf.zz.com.pritice.fragment

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_home.*
import sgcf.zz.com.pritice.MvpView.HomeView
import sgcf.zz.com.pritice.R
import sgcf.zz.com.pritice.mvp.base.BaseFragment
import sgcf.zz.com.pritice.presenter.HomePresenter

/**
 *
 *
 */
class HomeFragment : BaseFragment<HomeView, HomePresenter>() {
    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun initView(rootView: View?) {
        super.initView(rootView)
        var linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_home.layoutManager = linearLayoutManager
    }

    override fun provideContentViewId(): Int {
        return R.layout.fragment_home
    }


}