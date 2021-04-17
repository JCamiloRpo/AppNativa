package com.example.vacumap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VacunaActivity extends AppCompatActivity {
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        btnAgregar = (Button) findViewById(R.id.BtnAddVac);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaAddActivity.class);
                startActivity(i);
            }
        });
    }
}