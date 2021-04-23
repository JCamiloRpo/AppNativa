package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MascotaActivity extends AppCompatActivity {
    Button btnAgregar;
    MascotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        initButtons();
        initList();
    }

    private void initButtons(){
        btnAgregar = (Button) findViewById(R.id.BtnAddPet);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vacuna = new Intent(getApplicationContext(), MascotaAddActivity.class);
                startActivity(vacuna);
            }
        });
    }

    private void initList(){
        ListView list = (ListView) findViewById(R.id.ListMascotas);

        //Consultar mascotas
        ArrayList<MascotaItem> mascotas = new ArrayList<>();
        mascotas.add(new MascotaItem(2,R.drawable.gato, "Gato", "1", "12/12/2000"));
        mascotas.add(new MascotaItem(1,R.drawable.perro, "Perro", "1", "12/12/2000"));
        mascotas.add(new MascotaItem(3,R.drawable.hamster, "Hamster", "1", "12/12/2000"));
        mascotas.add(new MascotaItem(4,R.drawable.conejo, "Conejo", "1", "12/12/2000"));
        mascotas.add(new MascotaItem(5,R.drawable.pollo, "Pollo", "1", "12/12/2000"));

        adapter = new MascotaAdapter(this, mascotas);
        list.setAdapter(adapter);
    }
}