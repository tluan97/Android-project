package com.example.shivu.kiemdien.presenter;

import com.example.shivu.kiemdien.model.entity.Student;

import java.util.List;

public interface IGetStudentPresenter {
    List<Student> GetAll();
    int GetSizeAll();
    Student GetByBarcode(String barcode);
    Student GetByIdStudent(String idStudent);
    List<Student> GetStudentChecked();
    List<Student> GetStudentNotChecked();
}