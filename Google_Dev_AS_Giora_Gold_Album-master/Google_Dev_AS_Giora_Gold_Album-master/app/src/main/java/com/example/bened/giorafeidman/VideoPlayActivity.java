package com.example.bened.giorafeidman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {
    private static final String STATE_TITLE = "Title of the current song";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content of this activity
        setContentView(R.layout.activity_video_play);

        // Try to display the value of the title(but failed)
        TextView mTitleTextView = findViewById(R.id.title_video_wrapper);
        mTitleTextView.setText(STATE_TITLE);

        //Navigate to an other activity
        ImageView arrowBack = findViewById(R.id.arrow_back_intent);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayMainActivity = new Intent(VideoPlayActivity.this, MainActivity.class);
                startActivity(displayMainActivity);
            }
        });

        //Set the VideoView
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        //play one video
        videoView.setVideoURI(Uri.parse("android.resource://com.example.bened.giorafeidman/" + R.raw.v0));
        videoView.requestFocus();
        videoView.start();
    }
}