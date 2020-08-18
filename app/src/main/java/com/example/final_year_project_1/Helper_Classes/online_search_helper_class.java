package com.example.final_year_project_1.Helper_Classes;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import android.widget.VideoView;

import static android.widget.Toast.LENGTH_SHORT;

public class online_search_helper_class {

    VideoView videoView;

    public Uri search(String search) {
        //Toast.makeText(Context, "we are in uri search", LENGTH_SHORT).show();
        if (search.equals("a")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8");
        } else if (search.equals("b")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8");
        } else if (search.equals("c")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8");
        } else
            return null;
    }
}
