package com.example.bened.giorafeidman;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class SongAdapter extends ArrayAdapter<SongTitle> {

    private static final String LOG_TAG = SongAdapter.class.getSimpleName();

    //constructor
    public SongAdapter(Activity context, ArrayList<SongTitle> songTitles) {
        super(context, 0, songTitles);

    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_song_wrapper, parent, false);
        }


        // Get the {@link SongTitle} object located at this position in the list
        SongTitle currentSongTitle = getItem(position);

        // Find the TextView in the list_song_wrapper layout displaying the title
        TextView titleWrapper = listItemView.findViewById(R.id.title_wrapper);
        // Get the title  and set this text on this TextView
        if (currentSongTitle != null) {
            titleWrapper.setText(currentSongTitle.getTitle());
        }

        // Find the TextView in the list_song_wrapper layout displaying the album
        TextView albumWrapper = listItemView.findViewById(R.id.album_wrapper);
        // Get the album  and set this text on this TextView
        if (currentSongTitle != null) {
            albumWrapper.setText(currentSongTitle.getAlbum());
        }

        // Find the TextView in the list_song_wrapper layout displaying the date
        TextView dateWrapper = listItemView.findViewById(R.id.date_wrapper);
        // Get the date and set this text on this TextView
        if (currentSongTitle != null) {
            dateWrapper.setText(currentSongTitle.getDate());
        }

        // Return the whole list item layout
        return listItemView;

    }
}