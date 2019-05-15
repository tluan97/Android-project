package com.example.shivu.kiemdien.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivu.kiemdien.R;
import com.example.shivu.kiemdien.helper.CachedFileProvider;
import com.example.shivu.kiemdien.helper.Utils;
import com.example.shivu.kiemdien.model.entity.Student;
import com.example.shivu.kiemdien.presenter.IGetStudentPresenter;
import com.example.shivu.kiemdien.presenter.ISaveStudentPresenter;
import com.example.shivu.kiemdien.presenter.IUpdateStudentPresenter;
import com.example.shivu.kiemdien.presenter.StudentPresenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    public final int READ_FILE_REQUEST_CODE = 100;
    public final int READ_FOLDER_REQUEST_CODE = 200;
    public final int SEND_MAIL_CODE = 300;

    //protected StudentPresenter studentPresenter;

    protected IGetStudentPresenter getStudentPresenter;
    protected ISaveStudentPresenter saveStudentPresenter;
    protected IUpdateStudentPresenter updateStudentPresenter;
    protected Utils utils;

    private Intent intentCheckin,intentListStudent,intentSetting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //studentPresenter = new StudentPresenter(this);
        getStudentPresenter = new StudentPresenter(this);
        saveStudentPresenter = new StudentPresenter(this);
        updateStudentPresenter = new StudentPresenter(this);
        utils = Utils.getInstance(this);
        intentCheckin = new Intent(this,CheckStudentActivity.class);
        intentListStudent = new Intent(this,MainActivity.class);
        intentSetting =new Intent(this,SettingActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOpenFile:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.warning);
                builder.setMessage(R.string.alert_open_file);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("text/*");
                        startActivityForResult(intent,READ_FILE_REQUEST_CODE);
                    }
                });
                builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.menuCheckin:
                if(!(this instanceof CheckStudentActivity)){
                    startActivity(intentCheckin);
                }
                break;
            case R.id.menuListStudent:
                if(!(this instanceof MainActivity)){
                    startActivity(intentListStudent);
                }
                break;
            case R.id.menuSetting:
                if(!(this instanceof SettingActivity)){
                    startActivity(intentSetting);
                }
                break;
            case R.id.menuSaveAs:
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/csv");
                intent.putExtra(intent.EXTRA_TITLE,".csv");
                startActivityForResult(intent,READ_FOLDER_REQUEST_CODE);
                break;
            case R.id.menuSendEmail:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_file_name);

                Button btnAgree = dialog.findViewById(R.id.btnAgree);
                Button btnBack  = dialog.findViewById(R.id.btnBack);

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAgree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fileName = ((EditText)dialog.findViewById(R.id.edtFileName))
                                .getText().toString();
                        if(fileName.isEmpty()){
                            return;
                        }
                        File cacheDir = getCacheDir();
                        if(cacheDir.exists()){
                            for(File child : cacheDir.listFiles()){
                                child.delete();
                            }
                        }
                        try{
                            WriteAllStudentToFile(new FileOutputStream(
                                    new File(getCacheDir(),fileName)));
                            File fileAttachment = new File(getCacheDir(),fileName);
                            String pathCacheFile =
                                    "content://" + CachedFileProvider.AUTHORITY + "/" + fileName;
                            sendMail(BaseActivity.this,"","",
                                    null,Uri.parse(pathCacheFile));
                        }catch (Exception ex){
                            Log.e("ERROR", "onClick: ",ex );
                        }
                    }
                });
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.show();
                dialog.getWindow().setAttributes(lp);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void sendMail(Context context, String mailID, String subject, File attachment, Uri uri)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_EMAIL, mailID);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("vnd.android.cursor.dir/email");
        if (attachment != null){
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
        }
        else if (uri != null){
            intent.putExtra(Intent.EXTRA_STREAM, uri);
        }
        if (!TextUtils.isEmpty(subject)){
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (isAppAvailable(context, "com.google.android.gm")){
            intent.setPackage("com.google.android.gm");
        }
        startActivityForResult(intent, SEND_MAIL_CODE);
    }
    public static Boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        boolean isInstalled;
        try {
            pm.getPackageInfo(appName,PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver()
                    .query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    private void WriteAllStudentToFile(OutputStream outputStream) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                outputStream, utils.getCharset()));
        List<Student> students = getStudentPresenter.GetAll();
        out.write(utils.getColumnsName());
        out.newLine();
        int len = students.size();
        for(int i=0;i<len;++i){
            out.append(students.get(i).toString());
            out.newLine();
        }
        out.close();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == READ_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if (data != null ){
                Uri uriFile = data.getData();
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            getContentResolver().openInputStream(uriFile),utils.getCharset()));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while((line=in.readLine())!=null){
                        content.append(line);
                        content.append('\n');
                    }
                    //Log.i("OPENFILE "+utils.getCharset(), content.toString());
                    saveStudentPresenter.SaveToDb(content.toString());
                    in.close();
                }catch (Exception ex){
                    Log.e("ERROR", "Open file csv: ", ex);
                    Toast.makeText(this,R.string.error_open_file,Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(requestCode == READ_FOLDER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try{
                Uri uri = data.getData();
                WriteAllStudentToFile(getContentResolver().openOutputStream(uri));
                Toast.makeText(this,R.string.save_successful,Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                Log.e("ERROR", "Save file csv: ", ex);
                Toast.makeText(this,R.string.error_save_file,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(this,data.getData().toString(),Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
