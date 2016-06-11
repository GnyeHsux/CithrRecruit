package cn.cithr.jackdraw.cithrrecruit.app;

import android.app.Application;

/**
 * Created by Administrator on 2016/5/31.
 */
public class MyApplication extends Application {
    private String jsonData;

    @Override
    public void onCreate() {
        super.onCreate();
        setJsonData("");
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
