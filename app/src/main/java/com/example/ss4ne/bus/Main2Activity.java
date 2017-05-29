package com.example.ss4ne.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void onButtonClicked2(View v){

        startActivity(new Intent(Main2Activity.this, MainActivity.class));
    }
        public void onButtonClicked(View v) {
            DBAdapter db = new DBAdapter(this);
            //---add a contact---
            db.open();
            EditText txt2, txt3, txt4;
            String str2, str3, str4;

            txt2 = (EditText) findViewById(R.id.editText);
            txt3 = (EditText) findViewById(R.id.editText2);
            txt4 = (EditText) findViewById(R.id.editText3);

            str2 = txt2.getText().toString();
            str3 = txt3.getText().toString();
            str4 = txt4.getText().toString();
            if (str3 .equalsIgnoreCase(str4))
            {

            long id = db.insertUser(str2, str3);
            if (id != 0)
                Toast.makeText(getApplicationContext(), "Data Inserted Successfully ", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Data could not be inserted ", Toast.LENGTH_LONG).show();
            db.close();
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt2.clearFocus();
        }
        else{
                Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_LONG).show();
                txt3.setText("");
                txt4.setText("");
                txt2.clearFocus();
            }

        }
    }

