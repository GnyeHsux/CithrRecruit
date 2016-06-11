package cn.cithr.jackdraw.cithrrecruit.model;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface OnRegisterListener {
    void onRegisterSuccess(String msg);

    void onRegisterFail();

    void onRegisterError(String msg);

    void onRegister();
}
