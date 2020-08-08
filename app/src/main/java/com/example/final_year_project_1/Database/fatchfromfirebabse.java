package com.example.final_year_project_1.Database;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_year_project_1.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class fatchfromfirebabse extends AppCompatActivity  {
    VideoView videoView;
    EditText editText;
    Button btn;
    Uri videouri;
    MediaController mc;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fatchfromfirebase);

        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();

        dialog = new ProgressDialog(fatchfromfirebabse.this);

        videoView = findViewById(R.id.videoView1);
        editText = findViewById(R.id.editText1);
        btn = findViewById(R.id.button1);




       /* videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.start));
        videoView.requestFocus();
        videoView.start();*/
    }


    public void btnclick(View v){
        String search = editText.getText().toString();
        if (search.equals("a")){
          //  videouri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8");
       //     dialog.setMessage("please wait.");
      //     dialog.show();
            videoView.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8"));
        //    videoView.requestFocus();
            videoView.start();

        //    dialog.dismiss();
           /* mc = new MediaController(fatchfromfirebabse.this);
            videoView.requestFocus();
            videoView.start();*/
        }
        else if (search.equals("b")){
         //   videouri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/bay.mp4?alt=media&token=794a7f1b-faf4-4838-aa72-629b60cf4460");

         //   pd.setMessage("");
         //   pd.show();

            videoView.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/bay.mp4?alt=media&token=794a7f1b-faf4-4838-aa72-629b60cf4460"));
          //  mc = new MediaController(fatchfromfirebabse.this);
           // pd.dismiss();
            videoView.requestFocus();
            videoView.start();
        }
        else if (search.equals("c")){
            dialog.setMessage("please wait.");
            dialog.show();
        }
        }
    }

