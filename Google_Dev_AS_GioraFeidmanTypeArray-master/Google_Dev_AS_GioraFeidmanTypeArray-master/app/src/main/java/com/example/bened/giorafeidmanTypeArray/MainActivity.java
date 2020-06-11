package com.example.bened.giorafeidmanTypeArray;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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

            Resources resources = getResources();
            TypedArray title = resources.obtainTypedArray(R.array.title);
            TypedArray album = resources.obtainTypedArray(R.array.album);
            TypedArray date = resources.obtainTypedArray(R.array.date);
            TypedArray video = resources.obtainTypedArray(R.array.video);

            int i; // i stands for the index of each array
            int j; // j stands for the index of the field/item
            int numberOfArray= songs.size();
            int numberOfTitleItem= title.length();

        for(i=0; i<=numberOfArray; i++){
            for (j=0; j<numberOfTitleItem ; j++){
            songs.add(new SongTitle(
                title.getString(j),
                album.getString(j),
                date.getString(j),
                video.getString(j)
               ));

        }
            title.recycle();
            album.recycle();
            date.recycle();
            video.recycle();

        }







       /* songs.add(new SongTitle(
                getString(R.string.title0),
                getString(R.string.album0),
                getString(R.string.date0),
                getString(R.string.video0)));*/


        /*songs.add(new SongTitle(getString(R.string.title0), getString(R.string.album0), getString(R.string.date0), getString(R.string.video0)));
        songs.add(new SongTitle(getString(R.string.title1), getString(R.string.album1), getString(R.string.date1), ""));
        songs.add(new SongTitle(getString(R.string.title2), getString(R.string.album2), getString(R.string.date2), ""));
        songs.add(new SongTitle(getString(R.string.title3), getString(R.string.album3), getString(R.string.date3), ""));
        songs.add(new SongTitle(getString(R.string.title4), getString(R.string.album4), getString(R.string.date4), ""));
        songs.add(new SongTitle(getString(R.string.title5), getString(R.string.album5), getString(R.string.date5), ""));
        songs.add(new SongTitle(getString(R.string.title6), getString(R.string.album6), getString(R.string.date6), ""));
        songs.add(new SongTitle(getString(R.string.title7), getString(R.string.album7), getString(R.string.date7), ""));
        songs.add(new SongTitle(getString(R.string.title8), getString(R.string.album8), getString(R.string.date8), ""));
        songs.add(new SongTitle(getString(R.string.title9), getString(R.string.album9), getString(R.string.date9), ""));*/

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
