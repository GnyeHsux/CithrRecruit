package cn.cithr.jackdraw.cithrrecruit.model.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.app.MyApplication;
import cn.cithr.jackdraw.cithrrecruit.model.OnRegisterListener;
import cn.cithr.jackdraw.cithrrecruit.model.RegisterModel;
import cn.cithr.jackdraw.cithrrecruit.model.entities.Register;
import cn.cithr.jackdraw.cithrrecruit.model.entities.RegisterGetJsonData;

/**
 * Created by Administrator on 2016/6/1.
 */
public class RegisterModelImpl implements RegisterModel {

    private List<Map<String, Object>> registerParamLists = new ArrayList<>();
    private MyApplication app;

    @Override
    public void registerModel(Context context, String name, String password, String email, OnRegisterListener onRegisterListener) {
        String nameSpace = context.getResources().getString(R.string.namespace);
        String wsdl = context.getResources().getString(R.string.wsdl_login_and_registe);
        String method = context.getResources().getString(R.string.method_register);

        initParams(name, password, email);

        new RegisterGetJsonData(context, nameSpace, wsdl, method, registerParamLists);
        app = (MyApplication) context.getApplicationContext();

        String jsonData = app.getJsonData();
        //如果注册不通过，jsonData数据的长度为42
        if (jsonData.length() > 42) {
            Register register = new Register();
            Gson gson = new Gson();
            register = gson.fromJson(jsonData, Register.class);
            if (register.getResult().equals("true")) {
                onRegisterListener.onRegisterSuccess(register.getMsg());
            }else{
                onRegisterListener.onRegisterFail();
            }
        }else{
            JSONObject json = null;
            try {
                json = new JSONObject(jsonData);
                String result = json.optString("result");
                String msg = json.optString("msg");
                if(result.equals("true")){
                    onRegisterListener.onRegisterError(msg);
                }else{
                    onRegisterListener.onRegisterFail();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void initParams(String name, String password, String email) {
        Map<String, Object> registerParamList = new HashMap<>();
        registerParamList.put("uName", name);
        registerParamList.put("pwd", password);
        registerParamList.put("email", email);
        registerParamLists.add(registerParamList);
    }
}
