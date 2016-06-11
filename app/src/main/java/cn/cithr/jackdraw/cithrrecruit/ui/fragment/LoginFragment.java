package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.presenter.impl.LoginPresenter;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.RegisterActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.view.LoginView;

/**
 * Created by xusha on 2016/5/23.
 */
public class LoginFragment extends BaseFragment implements LoginView {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView mTvForgetPwd;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_goto_home)
    TextView mTvGotoHome;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private LoginPresenter loginPresenter;
    public HandlerThread loginThread;
    public Handler handler;
    Runnable loginRunnable = new Runnable() {
        @Override
        public void run() {
            loginPresenter.onLogin();
        }
    };

    public static LoginFragment newIntance() {
        return new LoginFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_login);
        loginPresenter = new LoginPresenter(view.getContext(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    //控件点击监控事件
    @OnClick({R.id.btn_login, R.id.tv_forget_pwd, R.id.tv_register, R.id.tv_goto_home})
    public void onClick(View view) {
        switch (view.getId()) {
            //登陆按钮事件
            case R.id.btn_login:
                //loginThread = new HandlerThread(loginRunnable);
                //Toast.makeText(getActivity(), "登陆", Toast.LENGTH_SHORT).show();
                loginThread = new HandlerThread("loginThread");
                loginThread.start();
                handler = new Handler(loginThread.getLooper());
                handler.post(loginRunnable);
                break;
            //忘记密码事件
            case R.id.tv_forget_pwd:
                addFragment(new ForgotPwdFragment());
                break;
            //注册事件
            case R.id.tv_register:
                startActivity(new Intent(getHoldingActivity(), RegisterActivity.class));
                break;
            //随便逛逛事件
            case R.id.tv_goto_home:
                getHoldingActivity().finish();
                break;
        }
    }

    @Override
    public void moveToIndex() {
        startActivity(new Intent(getHoldingActivity(), MainActivity.class));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return mEtName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPwd.getText().toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != handler) {
            handler.removeCallbacks(loginRunnable);//销毁线程
        }
    }
}
