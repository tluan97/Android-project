package com.example.shivu.kiemdien.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.model.entity.Student;

public class DetailStudentActivity extends BaseActivity{
    public static final String EXTRA_DETAIL_STUDENT="DetailStudentActivity.student";
    ImageView imgAvatar;
    TextView txtIdStudent, txtFullName, txtDateChecked;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        mapComponent();

    }

    private void mapComponent(){
        imgAvatar = findViewById(R.id.imgAvatar);
        txtIdStudent = findViewById(R.id.txtIdStudent);
        txtFullName=findViewById(R.id.txtFullName);
        txtDateChecked = findViewById(R.id.txtDateChecked);
        Intent intent = getIntent();
        Student student = (Student)intent.getSerializableExtra(EXTRA_DETAIL_STUDENT);

        txtIdStudent.setText(student.getIdStudent());
        txtFullName.setText(student.getFullName());
        txtDateChecked.setText(student.getDateChecked());
        if(student.getLinkImage().isEmpty()){
            imgAvatar.setImageResource(R.drawable.ic_image_black_24dp);
        }
        else{
            Glide.with(this)
                    .load(student.getLinkImage())
                    //.override(300,300)
                    .centerCrop()
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(imgAvatar);
        }

    }
}
