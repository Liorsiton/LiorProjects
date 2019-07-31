package com.example.liorita.imagestest;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnChangeImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public  void changeImage(View view){
        Log.i("Test" , "button pressed");
        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageDrawable(getDrawable(R.drawable.dog2));
    }
}
