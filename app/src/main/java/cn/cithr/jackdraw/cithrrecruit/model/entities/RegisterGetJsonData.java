package cn.cithr.jackdraw.cithrrecruit.model.entities;

import android.content.Context;

import org.ksoap2.serialization.SoapObject;

import java.util.List;
import java.util.Map;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.app.MyApplication;

/**
 * Created by Administrator on 2016/5/31.
 */
public class RegisterGetJsonData extends WebService{

    private MyApplication app;
    private SoapObject soap;

    public RegisterGetJsonData(Context context, String nameSpace, String wsdl, String method, List<Map<String, Object>> parameterLists) {
        super(context, nameSpace, wsdl, method, parameterLists);
        soap = super.getResultSO();
        app = (MyApplication) context.getApplicationContext();
        String result = context.getResources().getString(R.string.result_register);
        app.setJsonData(soap.getProperty(result).toString());
    }
}
