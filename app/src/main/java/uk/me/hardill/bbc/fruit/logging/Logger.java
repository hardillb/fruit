package uk.me.hardill.bbc.fruit.logging;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import uk.me.hardill.bbc.fruit.data.FruitBasket;

/**
 * Created by hardillb on 22/04/17.
 */

public class Logger {

    private static Logger singleton;

    private RequestQueue requestQueue;
    private static String baseURL = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats";

    private long loadStartTime;
    private long displayStartTime;

    private Logger(){

    }

    public static Logger init(Context context) {
        if (singleton == null) {
            singleton = new Logger();
            singleton.requestQueue = Volley.newRequestQueue(context);
        }

        return singleton;
    }

    /*
     * Actually sends the stats info
     */
    private void sendData(String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(request);
    }

    public void startLoad(){
        loadStartTime = System.currentTimeMillis();
    }

    public void endLoad() {
        long diff = System.currentTimeMillis() - loadStartTime;
        String url = baseURL + "?event=load&data=" + diff;
        Log.i("endLoad", url);
        sendData(url);
    }

    public void startDisplay(){
        displayStartTime = System.currentTimeMillis();
    }

    public void endDisplay() {
        long diff = System.currentTimeMillis() - loadStartTime;
        String url = baseURL + "?event=display&data=" + diff;
        Log.i("endDisplay", url);
        if (diff < 0) {
            return;
        }
        sendData(url);
    }

    public void logError(String msg) {
        try {
            String url = baseURL + "?event=error&data=" + URLEncoder.encode(msg,"UTF-8");
            Log.i("logError", url);
            sendData(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
