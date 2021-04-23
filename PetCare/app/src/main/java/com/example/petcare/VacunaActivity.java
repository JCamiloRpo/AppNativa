package com.example.petcare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class VacunaActivity extends AppCompatActivity {
    Button btnAgregar;
    VacunaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna);

        initButtons();
        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_profile:
                Intent i = new Intent(getApplicationContext(), MascotaEditActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initButtons(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setTitle("NOMBRE MASCOTA");

        btnAgregar = (Button) findViewById(R.id.BtnAddVac);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaAddActivity.class);
                startActivity(i);
            }
        });
    }

    private void initList(){
        ListView list = (ListView) findViewById(R.id.ListVacunas);

        //Consultar vacunas de una mascota
        ArrayList<VacunaItem> vacunas = new ArrayList<>();
        vacunas.add(new VacunaItem(1, "Vacuna 1", true, "12/12/2000"));
        vacunas.add(new VacunaItem(2, "Vacuna 2", false, "12/12/2000"));
        vacunas.add(new VacunaItem(3, "Vacuna 3", true, "12/12/2000"));
        vacunas.add(new VacunaItem(4, "Vacuna 4", false, "12/12/2000"));
        vacunas.add(new VacunaItem(5, "Vacuna 5", true, "12/12/2000"));

        adapter = new VacunaAdapter(this, vacunas);
        list.setAdapter(adapter);
    }
}