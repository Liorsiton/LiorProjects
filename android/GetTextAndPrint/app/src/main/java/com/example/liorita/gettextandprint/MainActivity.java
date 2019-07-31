package com.example.liorita.gettextandprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  void clickFunction(View view){

        EditText myEditText = (EditText) findViewById(R.id.editTextName);
        Toast.makeText(this  , "Hello there " + myEditText.getText().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
