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
        setToolbar(mToolbar,R.string.title_reset_new_pwd);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_pwd;
    }


    @OnClick(R.id.btn_submit_new_pwd)
    public void onClick() {
    }
}
