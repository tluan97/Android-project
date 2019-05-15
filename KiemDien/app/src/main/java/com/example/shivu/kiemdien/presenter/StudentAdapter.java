package com.example.shivu.kiemdien.presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.model.ApiContext;
import com.example.shivu.kiemdien.model.entity.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> students;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> students) {
        super(context, resource, students);
        this.context=context;
        this.resource=resource;
        this.students=students;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = convertView.findViewById(R.id.imgAvatar);
            viewHolder.txtFullName = convertView.findViewById(R.id.txtFullName);
            viewHolder.txtIdStudent = convertView.findViewById(R.id.txtIdStudent);
            viewHolder.imgChecked = convertView.findViewById(R.id.imgChecked);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = students.get(position);
        viewHolder.txtIdStudent.setText(student.getIdStudent());
        viewHolder.txtFullName.setText(student.getFullName());
        if(student.getDateChecked().isEmpty()) {
            viewHolder.imgChecked.setVisibility(View.INVISIBLE);
            //convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            viewHolder.imgChecked.setVisibility(View.VISIBLE);
            //convertView.setBackgroundColor(Color.parseColor("#69E2FF"));
        }
        /*if(student.getLinkImage().isEmpty()){
            viewHolder.imgAvatar.setImageResource(R.drawable.ic_image_black_24dp);
        }
        else{
            Glide.with(context)
                    .load(student.getLinkImage())
                    .override(80,80)
                    .centerCrop()
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(viewHolder.imgAvatar);
        }*/
        (new ApiContext(context))
                .GetImageStudent(student,viewHolder.imgAvatar);
        return convertView;
    }

    public class ViewHolder {
        TextView txtFullName, txtIdStudent;
        ImageView imgAvatar, imgChecked;
    }
}
