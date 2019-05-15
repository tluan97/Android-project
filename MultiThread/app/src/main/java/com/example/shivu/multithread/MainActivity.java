package com.example.shivu.multithread;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //public static int JarCookies = ConstantVariable.MAX_COOKIES_IN_JAR;
    public static JarCookie jarCookie;
    private Monster monster1, monster2;
    private GrandmaMonster grandmaMonster;
    private ProgressBar pbEatenMonster1, pbEatenMonster2, pbRunning;
    public static ProgressBar pbJar;
    private Button btnCancel, btnStart;
    private TextView txtNameMonster1, txtNameMonster2, txtTimer;
    private TextView txtMonster1,txtMonster2, txtGrandma;
    private String winner;
    private AlertDialog alertDialog;
    private Handler handler;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapComponent();
        resetData();
        eventComponent();
    }
    public void Winner(String name){
        stop();
        if(winner.isEmpty()){
            winner = name;
            alertDialog.setMessage(name + " win.");
            alertDialog.show();
        }
    }
    private void mapComponent(){
        txtNameMonster1 = findViewById(R.id.txtNameMonster1);
        txtNameMonster2 = findViewById(R.id.txtNameMonster2);
        pbEatenMonster1 = findViewById(R.id.pbEatenMonster1);
        pbEatenMonster2 = findViewById(R.id.pbEatenMonster2);

        txtMonster1 = findViewById(R.id.txtMonster1);
        txtMonster2 = findViewById(R.id.txtMonster2);
        txtGrandma = findViewById(R.id.txtGrandma);

        pbJar = findViewById(R.id.pbJar);
        btnCancel = findViewById(R.id.btnCancel);
        btnStart = findViewById(R.id.btnStart);
        pbEatenMonster1.setMax(ConstantVariable.COOKIES_FOR_WINNER);
        pbEatenMonster2.setMax(ConstantVariable.COOKIES_FOR_WINNER);
        pbJar.setMax(ConstantVariable.MAX_COOKIES_IN_JAR);
        pbRunning = findViewById(R.id.pbRunning);
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Match");
        txtTimer = findViewById(R.id.txtTimer);
    }
    private void whoWin(){
        stop();
        int c1 = monster1.getCookiesEated(), c2 = monster2.getCookiesEated();
        if(c1 == c2){
            alertDialog.setMessage("Draw");
            alertDialog.show();
            return;
        }
        if(c1 > c2){
            Winner(monster1.getName());
            return;
        }
        Winner(monster2.getName());
    }
    private void initHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case ConstantVariable.CODE_WIN:
                        Winner((String)msg.obj);
                        break;
                    case ConstantVariable.CODE_OUT_TIME:
                        whoWin();
                        break;
                }
            }
        };
    }
    private void resetData(){
        //JarCookies = ConstantVariable.MAX_COOKIES_IN_JAR;
        jarCookie = new JarCookie(pbJar);
        pbEatenMonster1.setProgress(ConstantVariable.INIT_COOKIES_FOR_MONSTER);
        pbEatenMonster2.setProgress(ConstantVariable.INIT_COOKIES_FOR_MONSTER);
        pbJar.setProgress(ConstantVariable.MAX_COOKIES_IN_JAR);
        txtTimer.setText("0/"+ConstantVariable.TOTAL_TIME+" sec.");
        winner = "";
        initHandler();
        monster1 = new Monster(txtNameMonster1.getText().toString()
                ,pbEatenMonster1,handler,txtMonster1);
        monster2 = new Monster(txtNameMonster2.getText().toString()
                ,pbEatenMonster2,handler,txtMonster2);
        grandmaMonster = new GrandmaMonster(handler,txtGrandma);
        timer = new Timer(handler,txtTimer);
        pbRunning.setVisibility(View.INVISIBLE);
    }
    private void eventComponent(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
                resetData();
                start();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
        });
    }
    private void start(){
        monster1.start();
        monster2.start();
        grandmaMonster.start();
        timer.start();
        pbRunning.setVisibility(View.VISIBLE);
    }
    private void stop(){
        monster1.Stop();
        monster2.Stop();
        grandmaMonster.Stop();
        timer.Stop();
        pbRunning.setVisibility(View.INVISIBLE);

        Log.i("ENDGAME", "mons1 : "+ monster1.getCookiesEated() + "/" + monster1.cookiesTaked);
        Log.i("ENDGAME", "mons2 : "+ monster2.getCookiesEated() + "/" + monster2.cookiesTaked);
        Log.i("ENDGAME", "grandma : "+ grandmaMonster.totalCookiesBaked);
        Log.i("ENDGAME", "jar : "+ jarCookie.getCookies());

    }
}
