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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;
<<<<<<< Temporary merge branch 1
import cn.cithr.jackdraw.cithrrecruit.utils.RegexUtils;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;
=======
import cn.cithr.jackdraw.cithrrecruit.model.entities.Register;
import cn.cithr.jackdraw.cithrrecruit.presenter.impl.LoginPresenter;
import cn.cithr.jackdraw.cithrrecruit.presenter.impl.RegisterPresenter;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.LoginActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.view.RegisterView;
>>>>>>> Temporary merge branch 2

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

<<<<<<< Temporary merge branch 1
    private String userName = "", email = "", pwd1 = "", pwd2 = "";
=======
    private RegisterPresenter registerPresenter;
    public HandlerThread registerThread;
    public Handler handler;
    Runnable registerRunnable = new Runnable() {
        @Override
        public void run() {
            registerPresenter.onRegister();
        }
    };
>>>>>>> Temporary merge branch 2

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_register);
<<<<<<< Temporary merge branch 1
=======
        registerPresenter = new RegisterPresenter(view.getContext(), this);
>>>>>>> Temporary merge branch 2
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    //注册按钮事件
    @OnClick(R.id.btn_reg)
    public void onClick() {
<<<<<<< Temporary merge branch 1
        userName = mEtRegUsername.getText().toString().trim();
        email = mEtRegEmail.getText().toString().trim();
        pwd1 = mEtRegPwd.getText().toString().trim();
        pwd2 = mEtRegPwdAgain.getText().toString().trim();

        //检查输入是否合法
        if (userName.equals("")) {
            mEtRegUsername.setError("用户名不能为空");
        } else if (email.equals("")) {
            mEtRegEmail.setError("邮箱不能为空");
        } else if (pwd1.equals("")) {
            mEtRegPwd.setError("密码不能为空");
        } else if (pwd1.length() < 6) {
            mEtRegPwd.setError("密码不能小于6位数");
        } else if (pwd2.equals("")) {
            mEtRegPwdAgain.setError("密码不能为空");
        } else if (pwd2.length() < 6) {
            mEtRegPwd.setError("密码不能小于6位数");
        } else if (!RegexUtils.checkEmail(email)) {
            mEtRegEmail.setError("邮箱格式不正确");
        } else if (!pwd1.equals(pwd2)) {
            mEtRegPwdAgain.setError("密码不一致，请再次确认");
        } else {
            //执行注册事件
            ToastUtils.showShort(getHoldingActivity(), "开始注册");
=======
        registerThread = new HandlerThread("registerThread");
        registerThread.start();
        handler = new Handler(registerThread.getLooper());
        handler.post(registerRunnable);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return mEtRegUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtRegPwd.getText().toString();
    }

    @Override
    public String getEmail() {
        return mEtRegEmail.getText().toString();
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
>>>>>>> Temporary merge branch 2
        }
    }
}