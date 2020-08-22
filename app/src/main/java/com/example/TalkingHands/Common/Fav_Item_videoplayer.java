package com.example.TalkingHands.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.TalkingHands.R;

public class Fav_Item_videoplayer extends AppCompatActivity {
    VideoView videoView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav__item_videoplayer);
        videoView = findViewById(R.id.videoplayer);

        progressBar = findViewById(R.id.favprogressbar);
        progressBar.setVisibility(View.VISIBLE);

        String Videouri = getIntent().getStringExtra("VideoUri");
        Uri uri = Uri.parse(Videouri);


        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                        // TODO Auto-generated method stub
                        progressBar.setVisibility(View.GONE);
                        //favorite_button.setVisibility(View.VISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        mp.start();
                    }
                });
            }
        });
    }

    public void favoriteitembackbtn(View view) {
        finish();
    }
}