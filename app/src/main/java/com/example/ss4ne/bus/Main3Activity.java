package com.example.ss4ne.bus;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void search(View view)
    {
        EditText txt =(EditText)findViewById(R.id.editText4);
        EditText txt2 =(EditText)findViewById(R.id.editText5);
        String query = "";
        String query2 = "";
        long rowId;

        query = txt.getText().toString();
        query2 = txt2.getText().toString();
        int found=0;
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst()) {
            do {
                if (c.getString(1).equals(query)&& c.getString(2).equals(query2)) {
                    Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                    intent.putExtra("ROW", c.getInt(0));
                    startActivity(intent);
                    found=1;
                    break;
                }
            }while(c.moveToNext());
            if(found==0)
                Toast.makeText(this, "Login unsuccessful", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(this, "Incorrect details", Toast.LENGTH_LONG).show();
        db.close();
    }

}
