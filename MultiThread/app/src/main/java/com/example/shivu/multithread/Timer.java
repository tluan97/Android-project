package com.example.shivu.multithread;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;



public class Timer extends Thread {
    private int counter;
    private Handler handler;
    private TextView txtTimer;
    private boolean isRunning;
    public Timer(Handler handler, TextView txtTimer){
        counter = 0;
        this.handler = handler;
        this.txtTimer = txtTimer;
        isRunning = true;
    }
    public void Stop(){
        this.isRunning = false;
    }
    @Override
    public void run(){
        while(isRunning && counter <=ConstantVariable.TOTAL_TIME){
            txtTimer.setText(counter + "/" + ConstantVariable.TOTAL_TIME + " sec.");
            if(counter>= ConstantVariable.TOTAL_TIME){
                Message message = new Message();
                message.what = ConstantVariable.CODE_OUT_TIME;
                handler.sendMessage(message);
                return;
            }
            SystemClock.sleep(1000);
            ++counter;
        }
    }

}
