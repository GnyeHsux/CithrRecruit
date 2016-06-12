package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/5/24.
 */
public class RegisterFragment extends BaseFragment {
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

    private String userName = "", email = "", pwd1 = "", pwd2 = "";

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar,R.string.title_register);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    //注册按钮事件
    @OnClick(R.id.btn_reg)
    public void onClick() {

    }
}
