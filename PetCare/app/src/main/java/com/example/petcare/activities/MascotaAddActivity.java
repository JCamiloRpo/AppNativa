package com.example.petcare.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petcare.dialogs.AvatarDialog;
import com.example.petcare.entities.MascotaItem;
import com.example.petcare.R;

public class MascotaAddActivity extends AppCompatActivity {
    //public static final int PICK_IMG = 10;
    public static String img = "";
    public static boolean add = false;
    Button btnGuardar;
    EditText txtNombre, txtTipo, txtEdad;
    ImageView imgPet;
    TextView txtImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_add);

        initComponents();
    }

    private void initComponents(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);

        txtNombre = findViewById(R.id.TxtNombre);
        txtTipo = findViewById(R.id.TxtTipo);
        txtEdad = findViewById(R.id.TxtEdad);

        imgPet = findViewById(R.id.ImgPet);
        txtImg = findViewById(R.id.TxtImg);
        txtImg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtImg.setText("Editar imagen");
        txtImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatars();
                /* Seleccionar imagen
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/");
                startActivityForResult(i.createChooser(i, "Seleccione la aplicación"), PICK_IMG);*/
            }
        });

        btnGuardar = findViewById(R.id.BtnSavePet);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePet();
            }
        });
    }

    private void showAvatars(){
        add = true;
        AvatarDialog dialogAvatar = new AvatarDialog();
        dialogAvatar.show(getSupportFragmentManager(), "Seleccionar avatar");
    }

    private void savePet() {
        String nombre = txtNombre.getText().toString(),
                tipo = txtTipo.getText().toString(),
                edad = txtEdad.getText().toString();
        if(img.equals(""))
            Toast.makeText(getApplicationContext(), "Debe selecionar una imagen.", Toast.LENGTH_SHORT).show();
        else if(nombre.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de la mascota.", Toast.LENGTH_SHORT).show();
        else if(tipo.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar el tipo de la mascota.", Toast.LENGTH_SHORT).show();
        else if(edad.equals(""))
            Toast.makeText(getApplicationContext(), "Debe ingresar la edad de la mascota.", Toast.LENGTH_SHORT).show();
        else{
            //Guardar la información
            long id = MascotaActivity.sql.Insert(new MascotaItem(img, nombre, tipo.toUpperCase(), Integer.parseInt(edad)));

            MascotaActivity.cargarList();
            VacunaActivity.idMascota = id;
            VacunaActivity.mascota = nombre;
            Intent i = new Intent(getApplicationContext(), VacunaActivity.class);
            startActivity(i);
            finish();
        }
    }

    /* Seleccionar imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMG){
            if (resultCode == RESULT_OK){
                Uri path = data.getData();
                img = path.toString();
                imgPet.setImageURI(path);
            }
        }
    }*/
}