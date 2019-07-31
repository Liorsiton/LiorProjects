package com.example.liorita.guussthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int expectedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        expectedNum = rand.nextInt(20) +1;
    }

    public void makeToast(String str){
        Toast.makeText(this, str , Toast.LENGTH_LONG).show();
    }

    public void  guess(View view){
        Log.i("info" , "the button pressed");
        EditText numbers = (EditText) findViewById(R.id.editTextNumbers);

        int guessInt = Integer.parseInt(numbers.getText().toString());

        if(guessInt > expectedNum){
            makeToast("lower");
        }
        else if(guessInt < expectedNum){
            makeToast("higher");
        }
        else{
            makeToast("you found it!!");
        }
    }
}
