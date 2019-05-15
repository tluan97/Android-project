package com.example.shivu.kiemdien.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shivu.kiemdien.helper.Utils;
import com.example.shivu.kiemdien.model.ApiContext;
import com.example.shivu.kiemdien.model.DbContext;
import com.example.shivu.kiemdien.model.entity.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentPresenter implements IGetStudentPresenter,ISaveStudentPresenter
        , IUpdateStudentPresenter{
    //public static StudentPresenter
    private Context context;
    private DbContext dbContext;
    public StudentPresenter(Context context){
        this.context=context;
        dbContext = new DbContext(context);
    }

    @Override
    public List<Student> GetAll() {
        return dbContext.GetAll();
    }

    @Override
    public int GetSizeAll() {
        return dbContext.GetSizeAll();
    }

    @Override
    public Student GetByBarcode(String barcode) {
        return dbContext.GetByBarcode(barcode);
    }
    @Override
    public Student GetByIdStudent(String idStudent){
        return dbContext.GetByIdStudent(idStudent);
    }

    @Override
    public List<Student> GetStudentChecked() {
        return dbContext.GetStudentChecked();
    }

    @Override
    public List<Student> GetStudentNotChecked() {
        return dbContext.GetStudentNotChecked();
    }

    @Override
    public void SaveToDb( String data) {
        String[] line = data.split("\n");
        String[] properties;
        ArrayList<Student> students=new ArrayList<>();
        if(line.length>0){
            Utils utils = Utils.getInstance(context);
            utils.SaveColumnsName(line[0]);
        }
        String dateChecked = "";
        for(int i= 1; i< line.length;++i){
            properties = line[i].split(",");
            if(properties.length<3){
                continue;
            }
            properties[0]=properties[0].trim();
            properties[1]=properties[1].trim();
            properties[2]=properties[2].trim();
            dateChecked = "";
            if(properties.length>3){
                dateChecked=properties[3].trim();
            }
            Student student = new Student(properties[0],properties[1],properties[2],dateChecked);
            students.add(student);
        }
        dbContext.ResetDb();
        int len = students.size();
        for(int i=0;i<len;++i){
            dbContext.Add(students.get(i));
        }
    }

    @Override
    public void CheckInStudent(Student student) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        student.setDateChecked(dateFormat.format(calendar.getTime()));
        dbContext.UpdateDateChecked(student);
    }

}
