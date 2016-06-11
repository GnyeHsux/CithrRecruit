package cn.cithr.jackdraw.cithrrecruit.model;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface OnLoginListener {
    void onLoginSuccess(String msg);//登录成功

    void onLoginFail();//登录失败

    void onLoginError(String msg);//账号或者密码错误

    void onLogin();
}
