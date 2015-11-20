package Models;


import android.app.Activity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
/**
 * Created by Elyzz Barrueta on 18/11/2015.
 */
public class httpAsync {

    public void Conexion(String metodo, Activity sigAct){
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        params.put("key", "value");
        params.put("more", "data");
        client.get("http://192.168.0.128", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        System.out.println("******************** Fallo :( ");
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                        System.out.println("**************** Success :) ");
                        System.out.println(responseString);
                    }


                }
        );

    }
}
