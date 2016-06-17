package cn.cithr.jackdraw.cithrrecruit.app;

import android.app.Application;
import android.content.IntentFilter;

import cn.cithr.jackdraw.cithrrecruit.receiver.NetworkChangeReceiver;

/**
 * Created by Administrator on 2016/5/31.
 */
public class MyApplication extends Application {
    private String jsonData;//网络获取的jsondata数据
    private boolean isNetwork;//网络状态
    NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        registerNetworkReceiver();
        setJsonData("");
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public boolean getIsNetwork() {
        return isNetwork;
    }

    public void setIsNetwork(boolean network) {
        this.isNetwork = network;
    }

    public void registerNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(networkChangeReceiver);
    }
}
