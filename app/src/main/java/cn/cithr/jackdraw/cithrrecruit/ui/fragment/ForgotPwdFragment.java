package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.utils.RegexUtils;

/**
 * Created by xusha on 2016/5/24.
 */
public class ForgotPwdFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_forgot_phone)
    EditText mEtForgotPhone;
    @BindView(R.id.et_forgot_code)
    EditText mEtForgotCode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.btn_submit_code)
    Button mBtnSubmit;

    private TimeCount mTimeCount;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_forgot_pwd);
        // 初始化短信验证SDK
        // SMSSDK.initSDK(this, "12b828c2a7cc0", "5d6f3d3c05942c36cc19681ffd1bacbb");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forgot_pwd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_get_code, R.id.btn_submit_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                String mobile = mEtForgotPhone.getText().toString().trim();

                //检查手机号是否合法
                if (!RegexUtils.checkMobile(mobile)) {
                    mEtForgotPhone.setError("手机号码不正确");
                } else {        //合法则获取验证码
                    mTimeCount = new TimeCount(60000, 1000);
                    mTimeCount.start();
                    //获取验证码
                    //SMSSDK.getVerificationCode("86", etPhone.getText().toString().trim());
                }
                break;

            case R.id.btn_submit_code:

                //提交验证码成功后，重置密码
                addFragment(new ResetPwdFragment());
                break;
        }
    }


    //计时器
    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnGetCode.setClickable(false);
            mBtnGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            mBtnGetCode.setClickable(true);
            mBtnGetCode.setText("重新获取");
        }
    }
}
