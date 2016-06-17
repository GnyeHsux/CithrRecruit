package cn.cithr.jackdraw.cithrrecruit.presenter.impl;

import android.content.Context;

import cn.cithr.jackdraw.cithrrecruit.model.LoginModel;
import cn.cithr.jackdraw.cithrrecruit.model.OnLoginListener;
import cn.cithr.jackdraw.cithrrecruit.model.impl.LoginModelImpl;
import cn.cithr.jackdraw.cithrrecruit.ui.view.LoginView;

/**
 * Created by Administrator on 2016/5/30.
 */
public class LoginPresenter implements OnLoginListener {
    private LoginModel mLoginModel;
    private LoginView mLoginView;
    private Context mContext;

    public LoginPresenter(Context context, LoginView loginView) {
        this.mLoginView = loginView;
        this.mContext = context;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void onLoginSuccess(String msg) {
        mLoginView.showToast(msg);
        mLoginView.moveToIndex();
    }

    @Override
    public void onLoginFail() {
        mLoginView.showToast("登录失败");
    }

    @Override
    public void onLoginError(String msg) {
        mLoginView.showToast(msg);
    }

    @Override
    public void onLogin() {
        mLoginModel.loginModel(mContext, mLoginView.getName(), mLoginView.getPassword(), this);
    }
}
