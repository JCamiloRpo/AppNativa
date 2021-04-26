package com.example.petcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petcare.R;
import com.example.petcare.activities.MascotaActivity;
import com.example.petcare.conexions.ConexionSQLite;
import com.example.petcare.dialogs.AvatarDialog;
import com.example.petcare.entities.MascotaItem;

public class MascotaEditActivity extends AppCompatActivity {
    public static String img = "";
    EditText txtNombre, txtTipo, txtEdad;
    ImageView imgPet;
    TextView txtImg;
    Button btnGuardar, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_edit);

        initComponents();
    }

    private void initComponents(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        txtNombre = findViewById(R.id.TxtNombreEdit);
        txtTipo = findViewById(R.id.TxtTipoEdit);
        txtEdad = findViewById(R.id.TxtEdadEdit);

        imgPet = findViewById(R.id.ImgPetEdit);
        txtImg = findViewById(R.id.TxtImgEdit);
        txtImg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtImg.setText("Editar imagen");
        txtImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatars();
            }
        });
        consultar();

        btnGuardar = findViewById(R.id.BtnEditPet);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePet();
            }
        });
        btnRemove = findViewById(R.id.BtnRemovePet);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePet();
            }
        });

    }

    private void showAvatars(){
        MascotaAddActivity.add = false;
        AvatarDialog dialogAvatar = new AvatarDialog();
        dialogAvatar.show(getSupportFragmentManager(), "Seleccionar avatar");
    }

    private void consultar(){
        String[][] pet = MascotaActivity.sql.Read(ConexionSQLite.TABLE_MASCOTA, "*", "ID="+VacunaActivity.id);
        imgPet.setImageURI(Uri.parse("android.resource://com.example.petcare/drawable/"+pet[0][1]));
        txtNombre.setText(pet[0][2]);
        txtTipo.setText(pet[0][3]);
        txtEdad.setText(pet[0][4]);

        img = pet[0][1];
    }

    private void savePet() {
        String nombre = txtNombre.getText().toString(),
                tipo = txtTipo.getText().toString(),
                edad = txtEdad.getText().toString();
        if(nombre.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de la mascota.", Toast.LENGTH_SHORT).show();
        else if(tipo.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar el tipo de la mascota.", Toast.LENGTH_SHORT).show();
        else if(edad.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar la edad de la mascota.", Toast.LENGTH_SHORT).show();
        else{
            //Guardar la información
            MascotaActivity.sql.Update(new MascotaItem(img, nombre, tipo.toUpperCase(), Integer.parseInt(edad)), VacunaActivity.id);

            MascotaActivity.cargarList();
            VacunaActivity.mascota = nombre;
            onBackPressed();
        }
    }

    private void deletePet(){
        MascotaActivity.sql.Delete(VacunaActivity.id);
        MascotaActivity.cargarList();
        Intent i = new Intent(getApplicationContext(), MascotaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finish();
    }
}