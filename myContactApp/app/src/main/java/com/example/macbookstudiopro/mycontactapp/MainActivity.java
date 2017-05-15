package com.example.macbookstudiopro.mycontactapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editGender;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        //Add the layout bars
        editName = (EditText) findViewById(R.id.editText_Name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editGender = (EditText) findViewById(R.id.editText_Gender);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editGender.getText().toString());

        if(isInserted == true){
            Log.d("MyContact", "Success inserting data");
            //Insert Toast mesage here.......
            Toast.makeText(v.getContext(), "You did it!", Toast.LENGTH_LONG).show();
        } else{
            Log.d("MyContact", "Failure inserting data");
            //Insert Toast mesage here.......
            Toast.makeText(v.getContext(), "You didn't do it!", Toast.LENGTH_LONG).show();
        }
    }
}
