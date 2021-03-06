package com.upb.petcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.upb.petcare.R;
import com.upb.petcare.entities.VacunaItem;

import java.util.Calendar;

public class VacunaAddActivity extends AppCompatActivity {
    Button btnGuardar;
    EditText txtNombre, txtFecha;
    Switch swtActiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna_add);

        initComponents();
    }

    private void initComponents(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        txtNombre = findViewById(R.id.TxtVacuna);
        swtActiva = findViewById(R.id.SwtActiva);
        txtFecha = findViewById(R.id.TxtFecha);
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH), month = c.get(Calendar.MONTH), year = c.get(Calendar.YEAR);
                DatePickerDialog date = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);
                date.show();
            }
        });

        btnGuardar = findViewById(R.id.BtnSaveVac);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveVacuna(); }
        });
    }

    private void saveVacuna(){
        String nombre = txtNombre.getText().toString(),
                fecha = txtFecha.getText().toString();
        int activa = swtActiva.isChecked() ? 1 : 0;
        if(nombre.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de la vacuna.", Toast.LENGTH_SHORT).show();
        else if(fecha.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar la fecha de la vacuna.", Toast.LENGTH_SHORT).show();
        else{
            //Guardar la informaci??n
            MascotaActivity.sql.Insert(new VacunaItem(0, (int)VacunaActivity.idMascota, nombre, activa, fecha));

            VacunaActivity.cargarList();
            MascotaActivity.cargarList();
            onBackPressed();
        }
    }
}