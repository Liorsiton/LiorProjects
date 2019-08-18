package com.example.liorita.listviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.myListView);

        final List<String> friends = new ArrayList<>();

        friends.add("ליאור");
        friends.add("גיא");
        friends.add("רם");
        friends.add("אריק");
        friends.add("ארז");
        friends.add("קובי");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("person tapped", friends.get(position));

                Toast.makeText(getApplicationContext(), "אהלן " + getFriendNick(friends.get(position)), Toast.LENGTH_LONG).show();

            }


        });


    }

    private String getFriendNick(String friend) {
        String nick = null;

            if (friend.equals("אריק")) {
                nick = "הטחון";
            } else if (friend.equals("גיא")) {
                nick = "הסגפן";
            } else if (friend.equals("ליאור")) {
                nick = "הגמל";
            } else if (friend.equals("רם")) {
                nick = "הגרמני";
            } else if (friend.equals("ארז")) {
                nick = "הפנסיונר";
            } else if (friend.equals("קובי")) {
                nick = "העלוב";
            }
            return nick;
    }
}