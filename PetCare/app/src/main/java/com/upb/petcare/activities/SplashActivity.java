package com.upb.petcare.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.upb.petcare.R;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MascotaActivity.class);
                startActivity(i);
                finish();
            }
        };

        Timer t = new Timer();
        t.schedule(tarea, 5000);
    }
}