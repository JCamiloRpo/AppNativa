package com.upb.petcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.upb.petcare.R;
import com.upb.petcare.adapters.VacunaAdapter;
import com.upb.petcare.conexions.ConexionSQLite;
import com.upb.petcare.entities.VacunaItem;

import java.util.ArrayList;

public class VacunaActivity extends AppCompatActivity {
    public static String mascota;
    public static long idMascota;
    Button btnAgregar;
    ActionBar menu;
    static Activity act;
    static VacunaAdapter adapter;
    static ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna);

        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        menu.setTitle(mascota);
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

    private void initComponents(){
        act = this;
        menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setTitle(mascota);

        btnAgregar = findViewById(R.id.BtnAddVac);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VacunaAddActivity.class);
                startActivity(i);
            }
        });

        list = findViewById(R.id.ListVacunas);
        cargarList();
    }

    public static void cargarList(){
        //Consultar vacunas de una mascota
        ArrayList<VacunaItem> vacunas = consultaDatos();

        adapter = new VacunaAdapter(act, vacunas);
        list.setAdapter(adapter);
    }

    private static ArrayList<VacunaItem> consultaDatos(){
        ArrayList<VacunaItem> vacunas = new ArrayList<>();
        String [][] datos = MascotaActivity.sql.Read(ConexionSQLite.TABLE_VACUNA, "MascotaID="+ idMascota);
        for (int i=0; i<datos.length;i++){
            vacunas.add(new VacunaItem(Integer.parseInt(datos[i][0]), datos[i][2], Integer.parseInt(datos[i][3]), datos[i][4]));
        }
        return vacunas;
    }

    private  ArrayList<VacunaItem> pruebaDatos(){
        ArrayList<VacunaItem> vacunas = new ArrayList<>();
        vacunas.add(new VacunaItem(1, "Vacuna 1", 1, "12/12/2000"));
        vacunas.add(new VacunaItem(2, "Vacuna 2", 0, "12/12/2000"));
        vacunas.add(new VacunaItem(3, "Vacuna 3", 1, "12/12/2000"));
        vacunas.add(new VacunaItem(4, "Vacuna 4", 0, "12/12/2000"));
        vacunas.add(new VacunaItem(5, "Vacuna 5", 1, "12/12/2000"));
        return vacunas;
    }
}