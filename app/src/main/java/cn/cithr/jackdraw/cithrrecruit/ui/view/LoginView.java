package cn.cithr.jackdraw.cithrrecruit.ui.view;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface LoginView {

    //登录成功跳转界面方法
    void moveToIndex();

    //Toast
    void showToast(String msg);

    //获取界面的姓名
    String getName();

    //获取界面密码
    String getPassword();
}
