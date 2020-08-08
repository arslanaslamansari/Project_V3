package com.example.final_year_project_1.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_year_project_1.R;

public class sign_up_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_3);
    }
    public void callVerifyOTPScreen(View view){

    }

    public void callLoginFrom3rdsignUp(View view) {
        Intent intent = new Intent(this,sign_in.class);
        startActivity(intent);

    }
}