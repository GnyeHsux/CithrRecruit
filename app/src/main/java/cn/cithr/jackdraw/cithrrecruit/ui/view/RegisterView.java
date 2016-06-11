package cn.cithr.jackdraw.cithrrecruit.ui.view;


/**
 * Created by Administrator on 2016/6/1.
 */
public interface RegisterView {

    void showToast(String msg);

    String getName();

    String getPassword();

    String getEmail();

    void  moveToLogin();
}
