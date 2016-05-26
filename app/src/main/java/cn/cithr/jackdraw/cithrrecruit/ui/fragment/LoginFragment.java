package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.RegisterActivity;

/**
 * Created by xusha on 2016/5/23.
 */
public class LoginFragment extends BaseFragment {
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

    public static LoginFragment newIntance() {
        return new LoginFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_login);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @OnClick({R.id.btn_login, R.id.tv_forget_pwd, R.id.tv_register, R.id.tv_goto_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Toast.makeText(getActivity(), "登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_forget_pwd:
                //Toast.makeText(getActivity(), "忘记密码", Toast.LENGTH_SHORT).show();
                addFragment(new ForgotPwdFragment());
                break;
            case R.id.tv_register:
                //Toast.makeText(getActivity(), "注册", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getHoldingActivity(), RegisterActivity.class));
                break;
            case R.id.tv_goto_home:
                Toast.makeText(getActivity(), "随便逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
