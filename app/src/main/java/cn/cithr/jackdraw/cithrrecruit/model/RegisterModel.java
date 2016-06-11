package cn.cithr.jackdraw.cithrrecruit.model;

import android.content.Context;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface RegisterModel {
    void registerModel(Context context,String name,String password,String email,OnRegisterListener onRegisterListener);
}
