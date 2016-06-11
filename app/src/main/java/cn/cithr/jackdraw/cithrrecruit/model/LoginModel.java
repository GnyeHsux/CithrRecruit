package cn.cithr.jackdraw.cithrrecruit.model;

import android.content.Context;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface LoginModel {
    /**登录的接口方法
     * @param context 上下文
     * @param name 账号
     * @param password 密码
     * @param onLoginListener 监听器
     */
    void loginModel(Context context,String name, String password, OnLoginListener onLoginListener);
}