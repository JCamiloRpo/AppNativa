package com.example.vacumap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MascotaAddActivity extends AppCompatActivity {
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_add);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        btnGuardar = (Button) findViewById(R.id.BtnSavePet);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}