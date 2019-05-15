package com.example.shivu.kiemdien.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.model.ApiContext;
import com.example.shivu.kiemdien.model.entity.Student;
import com.example.shivu.kiemdien.presenter.IUpdateStudentPresenter;
import com.example.shivu.kiemdien.presenter.StudentPresenter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CheckStudentActivity extends BaseActivity{
    ImageView imgAvatar;
    TextView txtFullName;
    EditText edtIdStudent;
    Button btnScanner, btnCheck, btnTestId;
    private IntentIntegrator qrScanner;
    Student student;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_student);
        initScanner();
        mapComponent();
        eventComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try{ UpdateInfoStudent(); }catch(Exception ex){}
    }

    private void initScanner(){
        qrScanner = new IntentIntegrator(this);
        qrScanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        qrScanner.setPrompt("Scan");
        qrScanner.setCameraId(0);
        qrScanner.setBeepEnabled(false);
        qrScanner.setBarcodeImageEnabled(false);
    }
    private void resetData(){
        student = null;
        edtIdStudent.setText("");
        imgAvatar.setImageResource(R.drawable.ic_image_black_24dp);
        imgAvatar.setVisibility(View.INVISIBLE);
        txtFullName.setText("");
    }
    private void mapComponent(){
        imgAvatar = findViewById(R.id.imgAvatar);
        txtFullName = findViewById(R.id.txtFullName);
        edtIdStudent = findViewById(R.id.edtIdStudent);
        btnScanner = findViewById(R.id.btnScanner);
        btnCheck = findViewById(R.id.btnCheck);
        btnTestId = findViewById(R.id.btnTestId);
    }
    private void eventComponent(){
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckStudent();
            }
        });
        btnTestId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStudent = edtIdStudent.getText().toString();
                student = getStudentPresenter.GetByIdStudent(idStudent);
                try{ UpdateInfoStudent(); }catch (Exception ex){
                    Toast.makeText(CheckStudentActivity.this,
                            R.string.student_not_in_file,Toast.LENGTH_LONG).show();
                }
            }
        });
        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScanner.initiateScan();
            }
        });
    }
    private void CheckStudent(){
        if(student == null){
            Toast.makeText(this,R.string.please_select_student,Toast.LENGTH_LONG).show();
            return;
        }
        if(student.getDateChecked()==null || student.getDateChecked().isEmpty()){
            updateStudentPresenter.CheckInStudent(student);
            resetData();
            Toast.makeText(this,R.string.check_successful,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,
                    getString(R.string.checked)+" "+student.getDateChecked(),
                    Toast.LENGTH_LONG).show();
        }
    }
    private void UpdateInfoStudent()throws NullPointerException{
        if(student == null){
            //Toast.makeText(this,R.string.student_not_in_file,Toast.LENGTH_LONG).show();
            txtFullName.setText("");
            imgAvatar.setImageResource(R.drawable.ic_image_black_24dp);
            imgAvatar.setVisibility(View.INVISIBLE);
            throw new NullPointerException("Student is null");
        } else {
            edtIdStudent.setText(student.getIdStudent());
            txtFullName.setText(student.getFullName());
            imgAvatar.setVisibility(View.VISIBLE);
            (new ApiContext(this)).GetImageStudent(student,imgAvatar);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                String barcode = result.getContents();
                student = getStudentPresenter.GetByBarcode(barcode);
                try{ UpdateInfoStudent(); }catch(Exception ex){
                    Toast.makeText(this,
                            R.string.student_not_in_file,Toast.LENGTH_LONG).show();
                }

                 // Toast.makeText(this, "Scanned: " + barcode, Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
