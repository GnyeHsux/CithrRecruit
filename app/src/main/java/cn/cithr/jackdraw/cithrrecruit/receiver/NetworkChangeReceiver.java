package cn.cithr.jackdraw.cithrrecruit.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import cn.cithr.jackdraw.cithrrecruit.app.MyApplication;


/**
 * Created by Administrator on 2016/6/12.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private MyApplication app;

    @Override
    public void onReceive(Context context, Intent intent) {
        app = (MyApplication) context.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);//系统服务类，专门用于管理网络链接
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) { //判断是否有网络
            app.setIsNetwork(true);
        } else {
            app.setIsNetwork(false);
        }
    }
}
