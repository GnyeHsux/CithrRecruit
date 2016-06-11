package cn.cithr.jackdraw.cithrrecruit.model.entities;


import android.content.Context;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * WebService类，用于webservice使用
 * Created by Administrator on 2016/5/23.
 */
public class WebService {

    public String mNameSpace;
    public String mWsdl;
    public String mMethod;
    public SoapObject mResultSO;
    public List<Map<String, Object>> mParameterLists
            = new ArrayList<Map<String, Object>>();//参数集合

    /**
     * 不含参数的构造方法
     *
     * @param nameSpace
     * @param wsdl
     * @param method
     */
    public WebService(Context context, String nameSpace, String wsdl, String method) {
        this.mNameSpace = nameSpace;
        this.mWsdl = wsdl;
        this.mMethod = method;
    }

    /**
     * 含参数的构造方法
     *
     * @param nameSpace
     * @param wsdl
     * @param method
     * @param parameterLists
     */
    public WebService(Context context, String nameSpace, String wsdl, String method, List<Map<String, Object>> parameterLists) {
        this.mNameSpace = nameSpace;
        this.mWsdl = wsdl;
        this.mMethod = method;
        this.mParameterLists = parameterLists;
    }


    public SoapObject  getResultSO() {
        SoapObject soapObject = new SoapObject(mNameSpace, mMethod);
        //判断是否需要添加参数
        if (mParameterLists != null) {
            for (int i = 0; i < mParameterLists.size(); ++i) {
                Map map = mParameterLists.get(i);
                Iterator iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Object object = map.get(key);
                    soapObject.addProperty(key, object);
                }
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(mWsdl);
        try {
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                mResultSO = (SoapObject) envelope.bodyIn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mResultSO;
    }

}
