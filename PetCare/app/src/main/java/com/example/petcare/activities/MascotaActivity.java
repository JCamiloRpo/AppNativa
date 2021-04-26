package com.example.petcare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.petcare.conexions.ConexionSQLite;
import com.example.petcare.adapters.MascotaAdapter;
import com.example.petcare.entities.MascotaItem;
import com.example.petcare.R;

import java.util.ArrayList;

public class MascotaActivity extends AppCompatActivity {
    public static ConexionSQLite sql;
    Button btnAgregar;
    static MascotaAdapter adapter;
    static ListView list;
    static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        sql = new ConexionSQLite(this, "db_PetCare",null, 1);
        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_info:
                /*Intent i = new Intent(getApplicationContext(), MascotaEditActivity.class);
                startActivity(i);*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initComponents(){
        act = this;
        btnAgregar = findViewById(R.id.BtnAddPet);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vacuna = new Intent(getApplicationContext(), MascotaAddActivity.class);
                startActivity(vacuna);
            }
        });

        list = findViewById(R.id.ListMascotas);
        cargarList();
    }

    public static void cargarList(){
        //Consultar mascotas
        ArrayList<MascotaItem> mascotas = consultaDatos();

        adapter = new MascotaAdapter(act, mascotas);
        list.setAdapter(adapter);
    }

    private static ArrayList<MascotaItem> consultaDatos(){
        ArrayList<MascotaItem> mascotas = new ArrayList<MascotaItem>();
        String [][] datos = sql.Read(ConexionSQLite.TABLE_MASCOTA);
        for (int i=0; i<datos.length;i++){
            String[][] vacuna = sql.Read(ConexionSQLite.TABLE_VACUNA, "Fecha", "MascotaID="+datos[i][0]+" ORDER BY Fecha DESC");
            if (vacuna.length == 0){
                vacuna = new String[1][1];
                vacuna[0][0] = "Sin vacunas";
            }
            mascotas.add(new MascotaItem(Integer.parseInt(datos[i][0]), datos[i][1], datos[i][2], Integer.parseInt(datos[i][4]), vacuna[0][0]));
        }
        return mascotas;
    }

    private  ArrayList<MascotaItem> pruebaDatos(){
        ArrayList<MascotaItem> mascotas = new ArrayList<>();
        mascotas.add(new MascotaItem(1, "gato", "Gato", 1, "12/12/2000"));
        mascotas.add(new MascotaItem(2, "perro", "Perro", 1, "12/12/2000"));
        mascotas.add(new MascotaItem(3, "hamster", "Hamster", 1, "12/12/2000"));
        mascotas.add(new MascotaItem(4, "conejo", "Conejo", 1, "12/12/2000"));
        mascotas.add(new MascotaItem(5, "pollo", "Pollo", 1, "12/12/2000"));
        return mascotas;
    }

}