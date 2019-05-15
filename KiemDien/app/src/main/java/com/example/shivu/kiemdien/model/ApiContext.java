package com.example.shivu.kiemdien.model;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.helper.Utils;
import com.example.shivu.kiemdien.helper.VolleyQueue;
import com.example.shivu.kiemdien.model.entity.Student;

public class ApiContext {
    private VolleyQueue volleyQueue;
    private Utils utils;
    private Context context;
    private int width=-1,height=-1;
    public ApiContext(Context context){
        this.context = context;
        this.volleyQueue = VolleyQueue.getInstance(context);
        this.utils = Utils.getInstance(context);
    }
    public ApiContext(Context context,int width, int height){
        this.context = context;
        this.volleyQueue = VolleyQueue.getInstance(context);
        this.utils = Utils.getInstance(context);
        this.width=width;
        this.height=height;
    }
    private void SetImageView(Student student, ImageView imageView){
        if(student==null || student.getLinkImage() == null || student.getLinkImage().isEmpty()){
            imageView.setImageResource(R.drawable.ic_image_black_24dp);
            return;
        }
        if(width < 0 || height < 0){
            Glide.with(context)
                    .load(student.getLinkImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(student.getLinkImage())
                    .override(width,height)
                    .centerCrop()
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(imageView);
        }

    }
    public void GetImageStudent(final Student student , final ImageView imageView){
        RequestQueue requestQueue = volleyQueue.getRequestQueue();
        String url = utils.getApiHost()+"/student/get/"+student.getIdStudent();
        if(!url.startsWith("http://")){
            url = "http://" + url;
        }
        Log.i("REQUEST", "URL: "+url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Student temp_student = null;
                    //Log.i("STUDENT", "onResponse: "+response);
                    try{
                        temp_student = Student.fromJson(response);
                    }catch(Exception ex){
                        //Log.e("ERROR", "ERROR PARSE STUDENT",ex );
                    }

                    if(temp_student != null && temp_student.getLinkImage() != null){
                        student.setLinkImage(temp_student.getLinkImage());
                    }
                    SetImageView(student,imageView);
                    //Log.i("REQUEST", student.toString());
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REQUEST", "ERROR: ", error);
            }
        });
        requestQueue.add(stringRequest);
    }

}
