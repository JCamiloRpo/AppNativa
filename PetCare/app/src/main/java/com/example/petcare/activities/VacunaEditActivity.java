package com.example.petcare.activities;

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

import com.example.petcare.R;
import com.example.petcare.conexions.ConexionSQLite;
import com.example.petcare.entities.VacunaItem;

import java.util.Calendar;

public class VacunaEditActivity extends AppCompatActivity {
    public static long idVacuna;
    Button btnEdit, btnRemove;
    EditText txtNombre, txtFecha;
    Switch swtActiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna_edit);

        initComponents();
    }

    private void initComponents(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        txtNombre = findViewById(R.id.TxtVacunaEdit);
        swtActiva = findViewById(R.id.SwtActivaEdit);
        txtFecha = findViewById(R.id.TxtFechaEdit);

        consultar();
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] c = txtFecha.getText().toString().split("/");
                int day = Integer.parseInt(c[0]), month = Integer.parseInt(c[1])-1, year = Integer.parseInt(c[2]);
                DatePickerDialog date = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);
                date.show();
            }
        });

        btnEdit = findViewById(R.id.BtnEditVac);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveVacuna(); }
        });

        btnRemove = findViewById(R.id.BtnRemoveVac);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVacuna();
            }
        });

    }

    private void consultar(){
        String[][] vacuna = MascotaActivity.sql.Read(ConexionSQLite.TABLE_VACUNA, "*", "ID="+idVacuna);
        txtNombre.setText(vacuna[0][2]);
        swtActiva.setChecked(Integer.parseInt(vacuna[0][3])==1);
        txtFecha.setText(vacuna[0][4]);
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
            //Guardar la información
            MascotaActivity.sql.Update(new VacunaItem((int)idVacuna, (int)VacunaActivity.idMascota, nombre, activa, fecha), idVacuna);

            VacunaActivity.cargarList();
            MascotaActivity.cargarList();
            onBackPressed();
        }
    }

    private void deleteVacuna(){
        MascotaActivity.sql.Delete(ConexionSQLite.TABLE_VACUNA, idVacuna);

        VacunaActivity.cargarList();
        MascotaActivity.cargarList();
        onBackPressed();
    }

}