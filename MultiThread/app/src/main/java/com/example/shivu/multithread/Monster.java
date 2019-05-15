package com.example.shivu.multithread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.UiThread;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class Monster extends Thread {
    private String name;
    private ProgressBar pbEaten;
    private int cookiesEated;
    private Random random;
    private boolean isRunning;
    private Handler handler;
    public int cookiesTaked;
    private TextView txtMonster;
    public Monster(String name, ProgressBar pbEaten, Handler handler, TextView txtMonster){
        super(name);
        this.name = name;
        this.pbEaten = pbEaten;
        this.random = new Random();
        cookiesEated = ConstantVariable.INIT_COOKIES_FOR_MONSTER;
        isRunning = true;
        this.handler = handler;
        this.txtMonster = txtMonster;
    }
    private int eat(){
        return MainActivity.jarCookie.take();
    }
    private synchronized void win(){
        //MainActivity.Winner(name);
        Message message = new Message();
        message.what = ConstantVariable.CODE_WIN;
        message.obj = name;
        handler.sendMessage(message);
    }
    public void Stop(){
        isRunning =false;
    }
    @Override
    public void run(){
        while(isRunning){
            cookiesTaked = eat();
            txtMonster.setText(" Eated: "+cookiesEated+" / Eating: "+cookiesTaked);
            SystemClock.sleep(random.nextInt(6)*1000);
            if(!isRunning){
                return;
            }
            cookiesEated += cookiesTaked;
            cookiesTaked = 0;
            pbEaten.setProgress(cookiesEated);
            txtMonster.setText(" Eated: "+cookiesEated+" / Eating: "+cookiesTaked);
            if(cookiesEated >= ConstantVariable.COOKIES_FOR_WINNER){
                win();
                return;
            }
        }
    }

    public int getCookiesEated(){
        return cookiesEated;
    }
}
