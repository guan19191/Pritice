package sgcf.zz.com.pritice.LiveDataViewModel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import sgcf.zz.com.pritice.Bean.User;

/**
 * Created by admin
 * Date:  2018/3/29.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */
public class UserProfileFregment extends Fragment {

    private static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        checkNotNull(getArguments());

        String  uid = null;
        if (getArguments() != null) {
            uid = getArguments().getString(UID_KEY);
        }

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(uid);

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //update UI
            }
        });

    }
}
