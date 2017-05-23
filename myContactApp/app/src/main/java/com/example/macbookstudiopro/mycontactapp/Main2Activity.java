package com.example.macbookstudiopro.mycontactapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    DataBaseHelper myDb;
    String[] fields;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fields = new String[]{"NAME: ", "AGE: ", "GENDER: "};
        search = (EditText) findViewById(R.id.editText_Search);

        myDb = new DataBaseHelper(this);
    }


    public void searchName(View v){
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
                buffer.append("ID: " + i + "\n");
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
    }


    public void showMessage(String error, String s){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(error);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.show();
    }
}
