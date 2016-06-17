package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.presenter.impl.RegisterPresenter;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.LoginActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.view.RegisterView;
import cn.cithr.jackdraw.cithrrecruit.utils.RegexUtils;

/**
 * Created by xusha on 2016/5/24.
 */
public class RegisterFragment extends BaseFragment implements RegisterView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_reg_username)
    EditText mEtRegUsername;
    @BindView(R.id.et_reg_email)
    EditText mEtRegEmail;
    @BindView(R.id.et_reg_pwd)
    EditText mEtRegPwd;
    @BindView(R.id.et_reg_pwd_again)
    EditText mEtRegPwdAgain;
    @BindView(R.id.btn_reg)
    Button mBtnRegNext;


    private RegisterPresenter registerPresenter;
    public HandlerThread registerThread;
    public Handler handler;
    Runnable registerRunnable = new Runnable() {
        @Override
        public void run() {
            registerPresenter.onRegister();
        }
    };

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_register);
        registerPresenter = new RegisterPresenter(view.getContext(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    //注册按钮事件
    @OnClick(R.id.btn_reg)
    public void onClick() {

        String pwd2 = mEtRegPwdAgain.getText().toString().trim();

        //检查输入是否合法
        if (getName().equals("")) {
            mEtRegUsername.setError("用户名不能为空");
        } else if (getEmail().equals("")) {
            mEtRegEmail.setError("邮箱不能为空");
        } else if (getPassword().equals("")) {
            mEtRegPwd.setError("密码不能为空");
        } else if (getPassword().length() < 6) {
            mEtRegPwd.setError("密码不能小于6位数");
        } else if (pwd2.equals("")) {
            mEtRegPwdAgain.setError("密码不能为空");
        } else if (pwd2.length() < 6) {
            mEtRegPwd.setError("密码不能小于6位数");
        } else if (!RegexUtils.checkEmail(getEmail())) {
            mEtRegEmail.setError("邮箱格式不正确");
        } else if (!getPassword().equals(pwd2)) {
            mEtRegPwdAgain.setError("密码不一致，请再次确认");
        } else {
            //执行注册事件
            registerThread = new HandlerThread("registerThread");
            registerThread.start();
            handler = new Handler(registerThread.getLooper());
            handler.post(registerRunnable);
        }

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return mEtRegUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtRegPwd.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return mEtRegEmail.getText().toString().trim();
    }

    @Override
    public void moveToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != handler) {
            handler.removeCallbacks(registerRunnable);//销毁线程
        }
    }

}