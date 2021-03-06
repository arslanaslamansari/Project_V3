package com.example.TalkingHands.Admin;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.TalkingHands.Common.AddHistory;
import com.example.TalkingHands.Common.AddToFavourite;
import com.example.TalkingHands.Common.Favorite;
import com.example.TalkingHands.Common.History;
import com.example.TalkingHands.Common.about_us;
import com.example.TalkingHands.Common.sign_in;
import com.example.TalkingHands.Common.user_Profile;
import com.example.TalkingHands.Database.offline_search_helper_class;
import com.example.TalkingHands.Database.online_search_helper_class;
import com.example.TalkingHands.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView, Network_status;
    View natworkstarus_background;

    //TextView textView;
    VideoView videoView;
    EditText editText;
    TextView txvResult;
    Button home_menu, mic_button1,report_button;
    ImageView favorite_button, btn, mic_button;
    protected ImageView menu_icon;
    ProgressDialog dialog;

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    String search = "";
    Uri uri;

    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerintent;
    private String keeper = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu_icon = findViewById(R.id.menu_icon);
        favorite_button = findViewById(R.id.favbutton);

        videoView = findViewById(R.id.videoView);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn1);
        report_button=findViewById(R.id.reportbtnn);


        mic_button = findViewById(R.id.btnSpeak);
        //mic_button1 = findViewById(R.id.btnSpeak1);
        textView = findViewById(R.id.textView);
        Network_status = findViewById(R.id.netorkstatus);
        natworkstarus_background = findViewById(R.id.view2);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.mainactivitypb);

        //dialog = new ProgressDialog(MainActivity.this);
        naviagtionDrawer();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (connected(MainActivity.this)) {
                    search = editText.getText().toString().trim();
                    //Toast.makeText(MainActivity.this, "you're in online mode", LENGTH_SHORT).show();
                    online_search(search);

                } else if (!connected(MainActivity.this)) {
                    //Toast.makeText(MainActivity.this, "you're in offline mode", LENGTH_SHORT).show();
                    offline_search();
                }
            }
        });

        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (connected(MainActivity.this)) {
                    if (fAuth.getCurrentUser() != null) {
                        AddToFavourite fav = new AddToFavourite(search, uri.toString());
                        Toast.makeText(getApplicationContext(), "Added to Favorite", LENGTH_SHORT).show();
                        favorite_button.setVisibility(View.GONE);
                    } else {
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("Login to Use this feature")
                                .show();

                    }
                } else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Connect with internet to use this feature")
                            .show();

                }
            }
        });

        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connected(MainActivity.this)) {

                    if (fAuth.getCurrentUser() != null) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto","tripea009@gmail.com", null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Invalid sign");
                        startActivity(Intent.createChooser(intent, fAuth.getCurrentUser().toString()));
                        report_button.setVisibility(View.GONE);
                    } else {
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("Login to Use this feature")
                                .show();

                    }


                } else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Connect with internet to use this feature")
                            .show();

                }
            }
        });




        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ur-PK");

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                //Toast.makeText(getApplicationContext(), "ready", LENGTH_SHORT).show();

            }

            @Override
            public void onBeginningOfSpeech() {
                //Toast.makeText(getApplicationContext(), "begning", LENGTH_SHORT).show();

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                //Toast.makeText(getApplicationContext(), "end", LENGTH_SHORT).show();

            }

            @Override
            public void onError(int i) {
                //Toast.makeText(getApplicationContext(), "error", LENGTH_SHORT).show();

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null) {
                    keeper = matches.get(0);
                    //textView.setText(keeper);
                    online_search(keeper);
                    //Toast.makeText(getApplicationContext(), "result" + keeper, LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Speak Again", LENGTH_SHORT).show();

                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                Toast.makeText(getApplicationContext(), "didn't complete", LENGTH_SHORT).show();

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


        //https://www.youtube.com/watch?v=sF2Vopirkok
        mic_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /*switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //speechRecognizer.startListening(speechRecognizerintent);
                        textView.setText("listning");
                        keeper= "";
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(getApplicationContext(), "pressed", LENGTH_SHORT).show();
                        //textView.setText("listning");
                        textView.setText("ok");

                        break;
                }*/

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    float reducedvalue = (float) 1.3;
                    mic_button.setScaleX(reducedvalue);
                    mic_button.setScaleY(reducedvalue);
                    textView.setText("listning");
                    speechRecognizer.startListening(speechRecognizerintent);
                    keeper = "";
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mic_button.setScaleX(1);
                    mic_button.setScaleY(1);
                    speechRecognizer.stopListening();
                    textView.setText("");
                    return true;
                } else
                    return false;
            }
        });


    }

    private void offline_search() {
        search = editText.getText().toString().trim();
        offline_search_helper_class object = new offline_search_helper_class();
        int oflineuri;
        oflineuri= object.search(search);

        if(oflineuri!=0){
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + oflineuri));
            videoView.requestFocus();
            videoView.start();
        }
        else{
            Toast.makeText(getApplicationContext(), "Sign not Available", LENGTH_SHORT).show();

        }
    }

    private void online_search(String onlinesearch) {


        if (fAuth.getCurrentUser() != null) {
            if (!onlinesearch.isEmpty()) {
                AddHistory history = new AddHistory(onlinesearch);
            }
        }

        //rootnode = FirebaseDatabase.getInstance();
        //reference = rootnode.getReference("history");
        //reference.setValue(search);
        //  Toast.makeText(getApplicationContext(), "Done", LENGTH_SHORT).show();

        if (onlinesearch.isEmpty()) {
        } else {
            progressBar.setVisibility(View.VISIBLE);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


            online_search_helper_class online_object = new online_search_helper_class();
            uri = online_object.search(onlinesearch);

            if (uri != null) {


                report_button.setClickable(true);
                videoView.setVideoURI(uri);
                //Toast.makeText(getApplicationContext(), "accessed", LENGTH_SHORT).show();
                videoView.requestFocus();
                videoView.start();

                //http://www.quicktips.in/how-to-show-progressbar-while-loading-a-video-in-android-videoview/
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        mp.start();
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                                // TODO Auto-generated method stub
                                favorite_button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                report_button.setVisibility(View.VISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                mp.start();
                            }
                        });
                    }
                });

            } else {
                uri = null;
                progressBar.setVisibility(View.GONE);
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Sign Not Available")
                        .show();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                //Toast.makeText(getApplicationContext(), "Sign Not Found", LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.thumbnail));
        videoView.requestFocus();
        videoView.start();

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
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (item.getItemId() == R.id.nav_fav) {
            if (fAuth.getCurrentUser() != null) {
                Intent i = new Intent(this, Favorite.class);
                startActivity(i);
            } else {
                /*Intent i = new Intent(this, sign_in.class);
                startActivity(i);*/
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Login to Use this feature")
                        .show();
            }


        } else if (item.getItemId() == R.id.nav_hist) {

            if (fAuth.getCurrentUser() != null) {
                Intent i = new Intent(this, History.class);
                startActivity(i);
            } else {
                /*Intent i = new Intent(this, sign_in.class);
                startActivity(i);*/
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Login to Use this feature")
                        .show();
            }

        } else if (item.getItemId() == R.id.nav_login) {

            if (fAuth.getCurrentUser() != null) {
                Intent i = new Intent(this, user_Profile.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, sign_in.class);
                startActivity(i);
            }

        } else if (item.getItemId() == R.id.nav_profile) {
            Intent i = new Intent(this, user_Profile.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_about) {
            Intent i = new Intent(this, about_us.class);
            startActivity(i);
        }
        else
            return super.onOptionsItemSelected(item);

        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobcon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);*/

            if (connected(MainActivity.this)) {
                //Toast.makeText(getApplicationContext(), "wifi enabled", LENGTH_SHORT).show();
                Network_status.setVisibility(View.GONE);
                natworkstarus_background.setVisibility(View.GONE);

            } else {
                // Toast.makeText(getApplicationContext(), "wifi disabled", LENGTH_SHORT).show();
                Network_status.setVisibility(View.VISIBLE);
                natworkstarus_background.setVisibility(View.VISIBLE);
            }
        }
    };


    public void reportsign(View view){


        /*Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "triplea009@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "invalid sign");
        //email.putExtra(Intent.EXTRA_TEXT, message);

//need this to prompts email client only
        email.setType("message/rfc822");
        String userid=fAuth.getCurrentUser().toString();
        startActivity(Intent.createChooser(email, userid));*/
    }
}