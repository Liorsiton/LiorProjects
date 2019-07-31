package com.example.liorita.findbadcomponentinfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void searchFunction(View view){
        Log.i("Info" ,"search button pressed");
        EditText component = (EditText) findViewById(R.id.editTextFind);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        String textToFind = component.getText().toString();
        if(isComponetExists(textToFind)){
            Log.i("Info","found the compnnent " + textToFind );
            Toast.makeText(MainActivity.this,"the component " + textToFind + " is not good", Toast.LENGTH_LONG).show();
            image.setImageDrawable(getDrawable(R.drawable.x1));
        }else{
            Log.i("Info","compnnent " + textToFind +"not found");
            Toast.makeText(MainActivity.this,"the component " + textToFind + " not found", Toast.LENGTH_LONG).show();
            image.setImageDrawable(getDrawable(R.drawable.v1));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean isComponetExists(String compnent){

       if(compnent.equalsIgnoreCase(Components.E232.toString())){
           return true;
       }
        if(compnent.equalsIgnoreCase(Components.E345.toString())){
            return true;
        }
        if(compnent.equalsIgnoreCase(Components.E418.toString())){
            return true;
        }
        if(compnent.equalsIgnoreCase(Components.E456.toString())){
            return true;
        }
        return false;

    }
}
