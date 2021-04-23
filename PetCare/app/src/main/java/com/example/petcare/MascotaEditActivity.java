package com.example.petcare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MascotaEditActivity extends AppCompatActivity {
    Button btnGuardar, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_edit);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);


        btnGuardar = (Button) findViewById(R.id.BtnEditPet);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getApplicationContext(), VacunaActivity.class);
                startActivity(i);
                finish();*/
                onBackPressed();
            }
        });
        btnRemove = (Button) findViewById(R.id.BtnRemovePet);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MascotaActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}