package com.upb.petcare.entities;

import android.content.ContentValues;

public class MascotaItem {

    private int MascotaID, edad;
    private String imagen, nombre, tipo, vacuna;

    public MascotaItem(int MascotaID, String imagen, String nombre, int edad, String vacuna) {
        this.MascotaID = MascotaID;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.vacuna = vacuna;
    }

    public MascotaItem(String imagen, String nombre, String tipo, int edad) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
    }

    public int getMascotaID() { return MascotaID; }

    public String getImagen() { return imagen; }

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
        values.put("Imagen", imagen);
        values.put("Nombre", nombre);
        values.put("Tipo", tipo);
        values.put("Edad", edad);
        return values;
    }
}
