package com.example.ss4ne.bus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void register(View view)
    {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
    public void login(View view)
    {
        startActivity(new Intent(MainActivity.this, Main3Activity.class));
    }
}