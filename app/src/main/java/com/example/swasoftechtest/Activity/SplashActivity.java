package com.example.swasoftechtest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.swasoftechtest.MainActivity;
import com.example.swasoftechtest.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sh = getSharedPreferences("LoginPref", MODE_PRIVATE);
        String s1 = sh.getString("mobile", "");
        String pass=sh.getString("pass","");
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            if (s1.isEmpty()&&pass.isEmpty()){
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));

            }
            else {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
            finish();

                //the current activity will get finished.
            }
        }, 5000);
    }
}