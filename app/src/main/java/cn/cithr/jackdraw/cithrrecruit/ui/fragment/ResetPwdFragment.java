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
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;

/**
 * Created by xusha on 2016/5/24.
 */
public class ResetPwdFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_new_pwd)
    EditText mEtNewPwd;
    @BindView(R.id.et_new_pwd_again)
    EditText mEtNewPwdAgain;
    @BindView(R.id.btn_submit_new_pwd)
    Button mBtnSubmitNewPwd;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_reset_new_pwd);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_pwd;
    }


    @OnClick(R.id.btn_submit_new_pwd)
    public void onClick() {
        String pwd1 = mEtNewPwd.getText().toString().trim();
        String pwd2 = mEtNewPwdAgain.getText().toString().trim();
        if (pwd1.length() < 6) {
            mEtNewPwd.setError("密码长度不能小于6位");
        } else if (!pwd1.equals(pwd2)) {
            mEtNewPwdAgain.setError("密码不一致，请在确认一遍");
        } else {
            //继续修改密码程序
            ToastUtils.showShort(getHoldingActivity(),"开始修改密码");
        }
    }
}
