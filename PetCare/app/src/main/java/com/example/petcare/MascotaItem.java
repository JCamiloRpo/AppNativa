package com.example.petcare;

import android.content.ContentValues;

public class MascotaItem {

    private int MascotaID, imagen, edad;
    private String nombre, tipo, vacuna;

    public MascotaItem(int MascotaID, int imagen, String nombre, int edad, String vacuna) {
        this.MascotaID = MascotaID;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.vacuna = vacuna;
    }

    public MascotaItem(int MascotaID, int imagen, String nombre, String tipo, int edad, String vacuna) {
        this.MascotaID = MascotaID;
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.vacuna = vacuna;
    }

    public int getMascotaID() { return MascotaID; }

    public int getImagen() { return imagen; }

    public String getNombre() { return nombre; }

    public String getTipo() { return tipo; }

    public int getEdad() { return edad; }

    public String getVacuna() { return vacuna; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("ID", MascotaID);
        values.put("Imagen", imagen);
        values.put("Nombre", nombre);
        values.put("Tipo", tipo);
        values.put("Edad", edad);
        return values;
    }
}
