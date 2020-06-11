package com.example.bened.giorafeidman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ListMainActivity";
    private static final String STATE_TITLE = "currentItemTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Set the content of this activity
        setContentView(R.layout.activity_main);

        // Create a list of songs in an ArrayList
        final ArrayList<SongTitle> songs = new ArrayList<>();
        songs.add(new SongTitle("The Klezmer's Freilach", "The Incredible Clarinet", "1992", "R.raw.v0"));
        songs.add(new SongTitle("Songs of Rejoicing", "The Magic of the Klezmer", "1986", ""));
        songs.add(new SongTitle("The Happy Nigun", "The Singing Clarinet", "1989", ""));
        songs.add(new SongTitle("Dancing with the Rabbi", "Klassic Klezmer", "1998", ""));
        songs.add(new SongTitle("Let's Be Happy", "Yiddish Soul", "1993", ""));
        songs.add(new SongTitle("Mi Chomocha", "The Singing Clarinet", "1989", ""));
        songs.add(new SongTitle("The dance of joy", "Gershwin & The Klezmer", "1991", ""));
        songs.add(new SongTitle("The Dance Of Fire", "The Dance of Joy", "2009", ""));
        songs.add(new SongTitle("Nigun", "The Magic of the Klezmer", "1986", ""));
        songs.add(new SongTitle("Dance of the souls", "The Singing Clarinet", "1989", ""));

        //Create an Adapter to display the ArrayList in a ListView
        SongAdapter listViewAdapter = new SongAdapter(this, songs);
        GridView listView = findViewById(R.id.list_view_main);
        listView.setAdapter(listViewAdapter);

        //Navigate to an other activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //try to save some instance (but failed)
                Bundle bundleTitle = new Bundle();
                bundleTitle.putSerializable(STATE_TITLE, (Serializable) getTitle());

                //Open an other activity
                Intent displayVideoPlayActivity = new Intent(MainActivity.this, VideoPlayActivity.class);
                startActivity(displayVideoPlayActivity);
            }
        });

    }
}
