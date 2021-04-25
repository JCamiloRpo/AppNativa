package com.example.petcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.petcare.R;

public class VacunaEditActivity extends AppCompatActivity {
    Button btnEdit, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna_edit);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        btnEdit = (Button) findViewById(R.id.BtnEditVac);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaActivity.class);
                startActivity(i);
                finish();
                //onBackPressed();
            }
        });

        btnRemove = (Button) findViewById(R.id.BtnRemoveVac);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaActivity.class);
                startActivity(i);
                finish();
                //onBackPressed();
            }
        });
    }
}