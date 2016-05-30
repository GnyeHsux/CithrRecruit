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
import cn.cithr.jackdraw.cithrrecruit.utils.RegexUtils;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;

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
        setToolbar(mToolbar, R.string.title_register);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    //注册按钮事件
    @OnClick(R.id.btn_reg)
    public void onClick() {
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
        }
    }
}