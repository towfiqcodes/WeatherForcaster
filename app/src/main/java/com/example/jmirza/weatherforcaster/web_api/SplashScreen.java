package com.example.jmirza.weatherforcaster.web_api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.jmirza.weatherforcaster.MainActivity;
import com.example.jmirza.weatherforcaster.R;

public class SplashScreen extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );
        text=findViewById(R.id.TextID);
        Animation animation= AnimationUtils.loadAnimation( this, R.anim.myanim);
        text.startAnimation(animation);
        new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent( SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
    }
}
