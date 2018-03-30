package sgcf.zz.com.pritice.LiveDataViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import sgcf.zz.com.pritice.Bean.User;

/**
 * Created by admin
 * Date:  2018/3/29.
 * Copyright on 2018 Henan Bijia Industrial Co., Ltd.
 * Desc:Pritice
 */
public class UserProfileViewModel extends ViewModel {
    private String userId;
//    private User user;


    private LiveData<User>  user;
    //初始化传递uid进来
    public void init(String userId) {
        this.userId = userId;
    }

    //提供完整的用户信息
    public LiveData<User> getUser() {
        return user;
    }


}
