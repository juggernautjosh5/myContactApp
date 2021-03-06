package com.example.macbookstudiopro.mycontactapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editGender;
    String[] fields;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        //Add the layout bars
        editName = (EditText) findViewById(R.id.editText_Name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editGender = (EditText) findViewById(R.id.editText_Gender);
        fields = new String[]{"NAME: ", "AGE: ", "GENDER: "};
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editGender.getText().toString());

        if(isInserted == true){
            Log.d("MyContact", "Success inserting data");
            //Insert Toast mesage here.......
            Toast.makeText(v.getContext(), "Data Added", Toast.LENGTH_LONG).show();
        } else{
            Log.d("MyContact", "Failure inserting data");
            //Insert Toast mesage here.......
            Toast.makeText(v.getContext(), "Data was not added", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //It's empty
            showMessage("Error", "No Data Found in DataBase");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        //SetUp Loop with Coursor using moveToNext
        for(int i = 0; i<res.getCount(); i++){
            for(int j = 1; j<=3; j++){
                buffer.append(fields[j-1]);
                buffer.append(res.getString(j));
                buffer.append("\n");
            }
            buffer.append("\n");
            res.moveToNext();
        }


        showMessage("Data Found", buffer.toString());
        System.out.println(buffer);
    }

    public void showMessage(String error, String s){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(error);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.show();
    }

    public void changeView(View v){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);

    }

    /*public void searchName(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //It's empty
            showMessage("Error", "No Data Found in DataBase");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        //SetUp Loop with Coursor using moveToNext
        for(int i = 0; i<res.getCount(); i++){

            if(res.getString(1).equals(search.getText().toString())){
                buffer.append("ID: " + i);
                for(int j = 1; j<=3; j++){
                    buffer.append(fields[j-1]);
                    buffer.append(res.getString(j));
                    buffer.append("\n");
                }
                break;
            }
            res.moveToNext();

        }
        if (buffer.toString().isEmpty()){
            showMessage("Error", "Name not found");
            return;
        }


        showMessage("Data Found", buffer.toString());
    }*/
}
