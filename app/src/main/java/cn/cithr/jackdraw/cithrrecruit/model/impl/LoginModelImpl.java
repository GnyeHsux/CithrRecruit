package cn.cithr.jackdraw.cithrrecruit.model.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.app.MyApplication;
import cn.cithr.jackdraw.cithrrecruit.model.LoginModel;
import cn.cithr.jackdraw.cithrrecruit.model.OnLoginListener;
import cn.cithr.jackdraw.cithrrecruit.model.entities.Login;
import cn.cithr.jackdraw.cithrrecruit.model.entities.LoginGetJsonData;

/**
 * Created by Administrator on 2016/5/30.
 */
public class LoginModelImpl implements LoginModel {

    private List<Map<String, Object>> loginParamLists = new ArrayList<>();
    private MyApplication app;

    @Override
    public void loginModel(Context context, String name, String password, OnLoginListener onLoginListener) {
        String nameSpace = context.getResources().getString(R.string.namespace);
        String wsdl = context.getResources().getString(R.string.wsdl_login_and_registe);
        String method = context.getResources().getString(R.string.method_login);

        initParam(name, password);

        new LoginGetJsonData(context, nameSpace, wsdl, method, loginParamLists);
        app = (MyApplication) context.getApplicationContext();
        Login login = new Login();
        Gson gson = new Gson();
        login = gson.fromJson(app.getJsonData(), Login.class);

        if (login.getResult().equals("true")) {
            if (login.getUserId() != 0) {
                onLoginListener.onLoginSuccess(login.getMsg());
            } else {
                onLoginListener.onLoginError(login.getMsg());
            }
        } else {
            onLoginListener.onLoginFail();
        }
    }

    public void initParam(String name, String password) {
        Map<String, Object> loginParamList = new HashMap<>();
        loginParamList.put("uName", name);
        loginParamList.put("pwd", password);
        loginParamLists.add(loginParamList);
    }
}
