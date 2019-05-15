package com.example.shivu.kiemdien.model.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String idStudent;
    private String barcode;
    private String fullName;
    private String dateChecked;
    private String linkImage;
    public Student(){
        idStudent=barcode=fullName=dateChecked=linkImage="";
    }
    public Student(String idStudent, String barcode, String fullName) {
        this.idStudent = idStudent;
        this.barcode = barcode;
        this.fullName = fullName;
        this.dateChecked="";
        this.linkImage="";
    }

    public Student(String idStudent, String barcode, String fullName, String dateChecked) {
        this.idStudent = idStudent;
        this.barcode = barcode;
        this.fullName = fullName;
        this.dateChecked = dateChecked;
        this.linkImage="";
    }
    public static Student fromJson(String json){
        return new Gson().fromJson(json,Student.class);
    }
    @Override
    public String toString() {
        return idStudent +","+barcode+","+fullName+","+dateChecked;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }


    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(String dateChecked) {
        this.dateChecked = dateChecked;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
