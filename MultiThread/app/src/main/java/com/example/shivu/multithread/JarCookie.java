package com.example.shivu.multithread;

import android.util.Log;
import android.widget.ProgressBar;

import java.util.Random;

public class JarCookie {
    private Object obj;
    private int cookies;
    private Random random;
    private ProgressBar pbJarCookies;
    public JarCookie(ProgressBar pbJarCookies){
        obj = new Object();
        cookies = ConstantVariable.MAX_COOKIES_IN_JAR;
        random = new Random();
        this.pbJarCookies = pbJarCookies;
    }

    public int take(){
        synchronized (obj){
            int cookiesTaked = random.nextInt(cookies + 1 );
            cookies -= cookiesTaked;
            pbJarCookies.setProgress(cookies);
            return cookiesTaked;
        }
    }

    public int bake(){
        synchronized (obj){
            int cookiesBaked = random.nextInt(ConstantVariable.MAX_COOKIES_BAKED + 1);
            int prevCookies = cookies;
            cookies += cookiesBaked;
            cookies = Math.min(cookies, ConstantVariable.MAX_COOKIES_IN_JAR);
            pbJarCookies.setProgress(cookies);
            return cookies - prevCookies;
        }
    }

    public synchronized int getCookies(){
        return cookies;
    }

}
