package com.example.shivu.kiemdien.helper;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyQueue {
    private static VolleyQueue volleyQueue;
    private RequestQueue requestQueue;
    private VolleyQueue(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized VolleyQueue getInstance(Context context){
        if(volleyQueue == null){
            volleyQueue = new VolleyQueue(context);
        }
        return volleyQueue;
    }
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
