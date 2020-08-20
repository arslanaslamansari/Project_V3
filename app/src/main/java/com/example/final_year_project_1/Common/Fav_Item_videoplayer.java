package com.example.final_year_project_1.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.final_year_project_1.R;

import static android.widget.Toast.LENGTH_SHORT;

public class Fav_Item_videoplayer extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav__item_videoplayer);
        videoView = findViewById(R.id.videoplayer);

        String Videouri = getIntent().getStringExtra("VideoUri");
        Uri uri = Uri.parse(Videouri);


        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void favoriteitembackbtn(View view) {
        finish();
    }
}