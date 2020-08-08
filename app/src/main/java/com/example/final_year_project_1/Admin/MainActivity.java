package com.example.final_year_project_1.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.final_year_project_1.Common.History;
import com.example.final_year_project_1.Common.sign_in;
import com.example.final_year_project_1.Common.sign_up;
import com.example.final_year_project_1.Database.fatchfromfirebabse;
import com.example.final_year_project_1.Helper_Classes.offline_search_helper_class;
import com.example.final_year_project_1.Helper_Classes.online_search_helper_class;
import com.example.final_year_project_1.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;

    TextView textView;
    VideoView videoView;
    EditText editText;
    TextView txvResult;
    Button home_menu;
    ImageView menu_icon, btn;
    ProgressDialog dialog;

    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu_icon = findViewById(R.id.menu_icon);


        videoView = findViewById(R.id.videoView);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn1);

        dialog = new ProgressDialog(MainActivity.this);
        naviagtionDrawer();


    }

    @Override
    protected void onResume() {
        super.onResume();

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.start));
        videoView.requestFocus();
        videoView.start();


        if (!connected(this)) {

            Toast.makeText(this, "you're in offline mode", LENGTH_SHORT).show();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //offlinesearch();
                    String search = editText.getText().toString();
                    offline_search_helper_class object = new offline_search_helper_class();

                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + object.search(search)));
                    videoView.requestFocus();
                    videoView.start();
                }
            });

        } else {
            Toast.makeText(this, "you're in online mode", LENGTH_SHORT).show();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String search = editText.getText().toString();
                    //  onlinesearch();
                    History history = new History(search);

                    //rootnode = FirebaseDatabase.getInstance();
                    //reference = rootnode.getReference("history");
                    //reference.setValue(search);
                    //  Toast.makeText(getApplicationContext(), "Done", LENGTH_SHORT).show();

                    online_search_helper_class online_object = new online_search_helper_class();

                    videoView.setVideoURI(online_object.search(search));
                    Toast.makeText(getApplicationContext(), "accessed", LENGTH_SHORT).show();
                    videoView.requestFocus();
                    videoView.start();
                }
            });

        }

    }

   /* private void onlinesearch() {
        String search = editText.getText().toString();
        if (search.equals("a")) {
            videoView.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/alif.mp4?alt=media&token=0500b878-f1dd-40dc-9186-ba7438af33e8"));
            videoView.start();
        } else if (search.equals("b")) {
            videoView.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/bay.mp4?alt=media&token=794a7f1b-faf4-4838-aa72-629b60cf4460"));
            videoView.start();
        } else if (search.equals("c")) {
            dialog.setMessage("please wait.");
            dialog.show();
        }
    }

    */


    private void offlinesearch() {

        String search = editText.getText().toString();
        offline_search_helper_class object = new offline_search_helper_class();

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + object.search(search)));
        videoView.requestFocus();
        videoView.start();

       /* if (search.equals("a")) {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alif));
            videoView.requestFocus();
            videoView.start();
        } else if (search.equals("b")) {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bay));
            videoView.requestFocus();
            videoView.start();
        }

        */
    }

    private boolean connected(MainActivity mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobcon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificon != null && wificon.isConnected()) || (mobcon != null && mobcon.isConnected())) {
            return true;
        } else
            return false;
    }


    //Navigation Drawer Functions
    private void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    public void getSpeechInput(View view) {


        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ur-PK");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            makeText(this, "Your Device Don't Support Speech Input", LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //     editText.setText(result.get(0));
                    //      String search = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).toString();
                    //      editText.setText(result.get(0));
                    //      String search= editText.getText().toString();
                    String search = (String) result.get(0);
                    if (search.equals("الف")) {
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alif));
                        videoView.requestFocus();
                        videoView.start();
                    } else if (search.equals("بے")) {
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bay));
                        videoView.requestFocus();
                        videoView.start();
                    } else {
                        makeText(this, "don't find", LENGTH_SHORT).show();
                    }
                }
                break;
        }


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_fav) {
            Toast.makeText(this, "We are working on favourite function", LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.nav_hist) {
            Toast.makeText(this, "We are working on History function", LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.nav_login) {
            Intent i = new Intent(this, sign_in.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_signup) {
            Intent i = new Intent(this, sign_up.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.nav_profile) {
            Intent i = new Intent(this, user_Profile.class);
            startActivity(i);
        }
        else
            return super.onOptionsItemSelected(item);

        return true;
    }


    public void signupfromhome(View view) {
        Intent intent = new Intent(this,sign_up.class);
        startActivity(intent);
    }
}