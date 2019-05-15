package com.example.shivu.kiemdien.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.helper.Utils;

public class SettingActivity extends BaseActivity {
    Button btnSave;
    EditText edtApiHost;
    Spinner spnCharset;
    private final String[] charsets = {Utils.ConstantVariable.UTF_8,
            Utils.ConstantVariable.UTF_16};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mapComponent();
        eventComponent();
    }
    private void mapComponent(){
        btnSave = findViewById(R.id.btnSave);
        edtApiHost = findViewById(R.id.edtApiHost);
        edtApiHost.setText(utils.getApiHost());
        spnCharset = findViewById(R.id.spnCharset);
        ArrayAdapter<String> adapterCharset = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                charsets);
        adapterCharset.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCharset.setAdapter(adapterCharset);
        int iSelected = findIndexOfCharset(utils.getCharset());
        if(iSelected >= 0){
            spnCharset.setSelection(iSelected);
        }
    }
    private int findIndexOfCharset(String charset){
        for(int i = 0; i < charsets.length; ++i){
            if(charsets[i].equals(charset)){
                return i;
            }
        }
        return -1;
    }
    private void eventComponent(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.SaveApiHost(edtApiHost.getText().toString());
                utils.SaveCharset(charsets[spnCharset.getSelectedItemPosition()]);
                Toast.makeText(SettingActivity.this,
                        R.string.save_successful,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
