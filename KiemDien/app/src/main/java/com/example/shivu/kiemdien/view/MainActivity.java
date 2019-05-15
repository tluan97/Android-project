package com.example.shivu.kiemdien.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.model.entity.Student;
import com.example.shivu.kiemdien.presenter.StudentAdapter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends BaseActivity {
    ListView lsStudents;
    Spinner spnFilter;
    TextView txtSize;
    StudentAdapter studentAdapter;
    List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // students = new ArrayList<>();
        mapComponent();
        eventComponent();
    }
    void copy(List arr1,final List arr2){
        arr1.clear();
        int len = arr2.size();
        for(int i=0;i<len;++i){
            arr1.add(arr2.get(i));
        }
    }
    private void mapComponent(){
        spnFilter = findViewById(R.id.spnFilter);
        ArrayAdapter<String> adapterFilter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                new String[]{
                    getString(R.string.all_students),
                    getString(R.string.students_checked),
                    getString(R.string.students_not_checked)
        });
        adapterFilter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnFilter.setAdapter(adapterFilter);

        lsStudents = findViewById(R.id.lsStudents);
        students = getStudentPresenter.GetAll();
        studentAdapter = new StudentAdapter(
                MainActivity.this,R.layout.row_student,students);
        lsStudents.setAdapter(studentAdapter);

        txtSize = findViewById(R.id.txtSize);

    }
    private void eventComponent(){
        lsStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(
                        MainActivity.this,DetailStudentActivity.class);
                intent.putExtra(DetailStudentActivity.EXTRA_DETAIL_STUDENT,students.get(i));
                startActivity(intent);
            }
        });

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                refreshData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void refreshData(){
        int itemSelected = spnFilter.getSelectedItemPosition();
        switch (itemSelected){
            case 0:
                //Get all students
                copy(students,getStudentPresenter.GetAll());
                break;
            case 1:
                //Get students who checked
                copy(students,getStudentPresenter.GetStudentChecked());
                break;
            case 2:
                //Get students who not checked
                copy(students,getStudentPresenter.GetStudentNotChecked());
                break;
        }
        txtSize.setText(students.size()+"/"+getStudentPresenter.GetSizeAll());
        studentAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStart() {
        super.onStart();
        refreshData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == READ_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            copy(students,getStudentPresenter.GetAll());
            studentAdapter.notifyDataSetChanged();
        }

    }

}
