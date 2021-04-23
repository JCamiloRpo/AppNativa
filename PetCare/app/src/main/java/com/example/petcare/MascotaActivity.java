package com.example.petcare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MascotaActivity extends AppCompatActivity {
    Intent vacuna;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        btnAgregar = (Button) findViewById(R.id.BtnAddPet);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vacuna = new Intent(getApplicationContext(), MascotaAddActivity.class);
                startActivity(vacuna);
            }
        });
    }

    public Intent getVacuna(){ return vacuna;}
}