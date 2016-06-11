package cn.cithr.jackdraw.cithrrecruit.presenter.impl;

import android.content.Context;

import cn.cithr.jackdraw.cithrrecruit.model.OnRegisterListener;
import cn.cithr.jackdraw.cithrrecruit.model.RegisterModel;
import cn.cithr.jackdraw.cithrrecruit.model.impl.RegisterModelImpl;
import cn.cithr.jackdraw.cithrrecruit.ui.view.RegisterView;

/**
 * Created by Administrator on 2016/6/1.
 */
public class RegisterPresenter implements OnRegisterListener {

    private RegisterView mRegisterView;
    private RegisterModel mRegisterModel;
    private Context mContext;

    public RegisterPresenter(Context context, RegisterView registerView) {
        this.mRegisterView = registerView;
        this.mRegisterModel = new RegisterModelImpl();
        this.mContext = context;
    }

    @Override
    public void onRegisterSuccess(String msg) {
        mRegisterView.showToast(msg);
        mRegisterView.moveToLogin();
    }

    @Override
    public void onRegisterFail() {

    }

    @Override
    public void onRegisterError(String msg) {
        mRegisterView.showToast(msg);
    }

    @Override
    public void onRegister() {
        mRegisterModel.registerModel(mContext, mRegisterView.getName(), mRegisterView.getPassword(), mRegisterView.getEmail(), this);
    }
}
