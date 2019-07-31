package com.example.liorita.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void clikFunction(View view){
        Log.i("Info" , "button pressed");
        
        EditText myEditText = (EditText) findViewById(R.id.editText1);

        Toast.makeText(MainActivity.this, myEditText.getText().toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
