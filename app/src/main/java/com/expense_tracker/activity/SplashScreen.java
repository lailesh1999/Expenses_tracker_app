package com.expense_tracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.expense_tracker.R;
import com.expense_tracker.data.local.PreferenceManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PreferenceManager.isLoggedIn(SplashScreen.this) && !PreferenceManager.isLoginExpired(SplashScreen.this)){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                }
                else{
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    PreferenceManager.clear(SplashScreen.this);
                }
                finish();
            }
        },2000);
    }
}