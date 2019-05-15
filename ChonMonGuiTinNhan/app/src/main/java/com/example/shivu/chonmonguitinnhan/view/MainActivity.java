package com.example.shivu.chonmonguitinnhan.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shivu.chonmonguitinnhan.R;
import com.example.shivu.chonmonguitinnhan.model.entity.TacoOrder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    RadioGroup rgSize, rgTortilla;
    RadioButton rdoSizeLarge, rdoSizeMedium, rdoTortillaCorn, rdoTortillaFlour;
    CheckBox cbFillingsBeef, cbFillingsChicken, cbFillingsWhiteFish;
    CheckBox cbFillingsCheese, cbFillingsSeaFood, cbFillingsRice;
    CheckBox cbFillingsBeans, cbFillingsPico, cbFillingsGuacamole, cbFillingsLBT;
    CheckBox cbBeverageSoda, cbBeverageCerveza, cbBeverageMargarita, cbBeverageTequila;
    Button btnOrder;
    //private String phoneNo, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapComponent();
        refreshDataComponent();
        eventComponent();
    }
    private void mapComponent(){
        rgSize = findViewById(R.id.rgSize);
        rgTortilla = findViewById(R.id.rgTortilla);

        rdoSizeLarge = findViewById(R.id.rdoSizeLarge);
        rdoSizeMedium=findViewById(R.id.rdoSizeMedium);
        rdoTortillaCorn = findViewById(R.id.rdoTortillaCorn);
        rdoTortillaFlour = findViewById(R.id.rdoTortillaFlour);

        cbFillingsBeef = findViewById(R.id.cbFillingsBeef);
        cbFillingsChicken = findViewById(R.id.cbFillingsChicken);
        cbFillingsWhiteFish = findViewById( R.id.cbFillingsWhiteFish);

        cbFillingsCheese = findViewById( R.id.cbFillingsCheese);
        cbFillingsSeaFood = findViewById(R.id.cbFillingsSeaFood);
        cbFillingsRice = findViewById(R.id.cbFillingsRice);

        cbFillingsBeans = findViewById(R.id.cbFillingsBeans);
        cbFillingsPico = findViewById(R.id.cbFillingPico);
        cbFillingsGuacamole = findViewById(R.id.cbFillingsGuacamode);
        cbFillingsLBT = findViewById(R.id.cbFillingsLBT);

        cbBeverageSoda = findViewById(R.id.cbBeverageSoda);
        cbBeverageCerveza = findViewById(R.id.cbBeverageCerveza);
        cbBeverageMargarita = findViewById(R.id.cbBeverageMargarita);
        cbBeverageTequila = findViewById(R.id.cbBeverageTequila);

        btnOrder = findViewById(R.id.btnOrder);
    }
    private void refreshDataComponent(){
        rdoSizeLarge.setChecked(true);
        rdoTortillaCorn.setChecked(true);

        cbFillingsBeef.setChecked(false);
        cbFillingsChicken.setChecked(false);
        cbFillingsWhiteFish.setChecked(false);
        cbFillingsCheese.setChecked(false);
        cbFillingsSeaFood.setChecked(false);
        cbFillingsRice.setChecked(false);
        cbFillingsBeans.setChecked(false);
        cbFillingsPico.setChecked(false);
        cbFillingsGuacamole.setChecked(false);
        cbFillingsLBT.setChecked(false);

        cbBeverageSoda.setChecked(false);
        cbBeverageCerveza.setChecked(false);
        cbBeverageMargarita.setChecked(false);
        cbBeverageTequila.setChecked(false);
    }
    private void addChecked(CompoundButton compoundButton, ArrayList<String> arr){
        if(compoundButton.isChecked()){
            arr.add(compoundButton.getText().toString());
        }
    }
    private void eventComponent(){
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TacoOrder tacoOrder = new TacoOrder();
                if(rdoSizeLarge.isChecked()) {
                    tacoOrder.setSize(rdoSizeLarge.getText().toString());
                } else if(rdoSizeMedium.isChecked()) {
                    tacoOrder.setSize(rdoSizeMedium.getText().toString());
                }
                if(rdoTortillaCorn.isChecked()) {
                    tacoOrder.setTortilla(rdoTortillaCorn.getText().toString());
                } else if(rdoTortillaFlour.isChecked()) {
                    tacoOrder.setTortilla(rdoTortillaFlour.getText().toString());
                }

                addChecked(cbFillingsBeef,tacoOrder.getFillings());
                addChecked(cbFillingsChicken,tacoOrder.getFillings());
                addChecked(cbFillingsWhiteFish,tacoOrder.getFillings());
                addChecked(cbFillingsCheese,tacoOrder.getFillings());
                addChecked(cbFillingsSeaFood,tacoOrder.getFillings());
                addChecked(cbFillingsRice,tacoOrder.getFillings());
                addChecked(cbFillingsBeans,tacoOrder.getFillings());
                addChecked(cbFillingsPico,tacoOrder.getFillings());
                addChecked(cbFillingsGuacamole,tacoOrder.getFillings());
                addChecked(cbFillingsLBT,tacoOrder.getFillings());

                addChecked(cbBeverageSoda,tacoOrder.getBeverage());
                addChecked(cbBeverageCerveza,tacoOrder.getBeverage());
                addChecked(cbBeverageMargarita,tacoOrder.getBeverage());
                addChecked(cbBeverageTequila,tacoOrder.getBeverage());

                //Toast.makeText(MainActivity.this,tacoOrder.toString(),Toast.LENGTH_LONG).show();
                final String contentOrder = tacoOrder.toString(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.order_confirmation);
                builder.setMessage(contentOrder);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendSMS("5556","I want a taco...\n"+contentOrder);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void sendSMS(String phoneNumber, String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }
   /* protected void sendSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message,
                    null, null);
            refreshDataComponent();
            Toast.makeText(MainActivity.this,R.string.order_success,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message,
                            null, null);
                    refreshDataComponent();
                    Toast.makeText(MainActivity.this,R.string.order_success,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.sms_faild, Toast.LENGTH_LONG).show();
                }
            }
        }

    }*/
}
