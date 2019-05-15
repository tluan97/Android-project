package com.example.shivu.multithread;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.BoringLayout;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class GrandmaMonster extends Thread {
    private Handler handler;
    private Random random;
    private boolean isRunning;
    public int totalCookiesBaked ;
    public TextView txtGrandma;
    public GrandmaMonster(Handler handler, TextView txtGrandma){
        this.handler = handler;
        this.random = new Random();
        isRunning = true;
        totalCookiesBaked = 0;
        this.txtGrandma = txtGrandma;
    }
    public synchronized void bake(){
        totalCookiesBaked += MainActivity.jarCookie.bake();

    }
    public void Stop(){
        isRunning = false;
    }
    @Override
    public void run(){
        while(isRunning){
            bake();
            txtGrandma.setText(" Baked: "+totalCookiesBaked);
            SystemClock.sleep(ConstantVariable.PERIOD_BAKE * 1000);
        }
    }
}
