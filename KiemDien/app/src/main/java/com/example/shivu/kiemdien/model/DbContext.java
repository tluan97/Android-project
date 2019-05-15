package com.example.shivu.kiemdien.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.shivu.kiemdien.model.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class DbContext extends SQLiteOpenHelper {
    private static final String DB_NAME = "studentdb";
    private static final String TABLE_NAME = "student";
    private static final String IDSTUDENT = "idstudent";
    private static final String BARCODE = "barcode";
    private static final String FULLNAME = "fullname";
    private static final String DATECHECKED = "datechecked";
    //private static final String LINKIMAGE="linkimage";
    private Context context;
    public DbContext(Context context){
        super(context,DB_NAME,null,1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                IDSTUDENT +" integer primary key, "+
                BARCODE + " TEXT UNIQUE, "+
                FULLNAME +" TEXT, "+
                DATECHECKED+" TEXT)";
        sqLiteDatabase.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }
    public void ResetDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        db.close();
    }
    public void Add(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IDSTUDENT, student.getIdStudent());
        values.put(BARCODE, student.getBarcode());
        values.put(FULLNAME, student.getFullName());
        values.put(DATECHECKED, student.getDateChecked());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public int UpdateDateChecked(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DATECHECKED,student.getDateChecked());

        int res = db.update(TABLE_NAME,values,IDSTUDENT +"=?",
                new String[] { String.valueOf(student.getIdStudent())});
        db.close();
        return res;
    }
    public List<Student> GetAll() {
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setIdStudent(cursor.getString(0));
                student.setBarcode(cursor.getString(1));
                student.setFullName(cursor.getString(2));
                student.setDateChecked(cursor.getString(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
    public int GetSizeAll() {
        int size = 0;
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                size = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return size;
    }

    public List<Student> GetStudentChecked() {
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + DATECHECKED + " != ''";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setIdStudent(cursor.getString(0));
                student.setBarcode(cursor.getString(1));
                student.setFullName(cursor.getString(2));
                student.setDateChecked(cursor.getString(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }

    public List<Student> GetStudentNotChecked() {
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + DATECHECKED + " = ''";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setIdStudent(cursor.getString(0));
                student.setBarcode(cursor.getString(1));
                student.setFullName(cursor.getString(2));
                student.setDateChecked(cursor.getString(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }

    public Student GetByBarcode(String barcode) {
        Student student = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + BARCODE + " = '" + barcode + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                student = new Student();
                student.setIdStudent(cursor.getString(0));
                student.setBarcode(cursor.getString(1));
                student.setFullName(cursor.getString(2));
                student.setDateChecked(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }
    public Student GetByIdStudent(String idStudent) {
        Student student = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + IDSTUDENT + " = '" + idStudent + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                student = new Student();
                student.setIdStudent(cursor.getString(0));
                student.setBarcode(cursor.getString(1));
                student.setFullName(cursor.getString(2));
                student.setDateChecked(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }
}
