package com.example.shivu.kiemdien.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    private static final String SHARED_PREFERENCES_NAME = "config";
    private static final String KEY_API_HOST = "api_host";
    private static final String KEY_COLUMNS_NAME = "columns_name";
    private static final String KEY_CHARSET = "charset";
    private String apiHost;
    private String columnsName;
    private String charset;
    private Context context;
    private static Utils utils;
    public static Utils getInstance(Context context){
        if(utils == null){
            utils = new Utils(context);
        }
        return utils;
    }
    private Utils(Context context){
        this.context = context;
        Update();
    }

    public static class ConstantVariable{
        public static final String UTF_8="UTF-8";
        public static final String UTF_16="UTF-16";
    }

    public String getApiHost() {
        return apiHost;
    }
    public String getColumnsName(){ return columnsName; }
    public String getCharset(){ return charset; }
    private void Update(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        apiHost = sharedPreferences.getString(KEY_API_HOST,"");
        columnsName = sharedPreferences.getString(KEY_COLUMNS_NAME,"");
        charset = sharedPreferences.getString(KEY_CHARSET,ConstantVariable.UTF_8);
    }
    public void SaveApiHost(String newHost){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_API_HOST,newHost);
        editor.apply();
        //editor.commit();
        Update();
    }
    public void SaveColumnsName(String newColumnsName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COLUMNS_NAME,newColumnsName);
        editor.apply();
        //editor.commit();
        Update();
    }
    public void SaveCharset(String newCharset){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CHARSET,newCharset);
        editor.apply();
        //editor.commit();
        Update();
    }
}
