package com.example.petcare;

import android.content.ContentValues;

public class VacunaItem {
    private int VacunaID, MascotaID, activa;
    private String nombre, fecha;

    public VacunaItem(int VacunaID, String nombre, int activa, String fecha) {
        this.VacunaID = VacunaID;
        this.nombre = nombre;
        this.fecha = fecha;
        this.activa = activa;
    }

    public VacunaItem(int VacunaID, int MascotaID, String nombre, int activa, String fecha) {
        this.VacunaID = VacunaID;
        this.MascotaID = MascotaID;
        this.nombre = nombre;
        this.fecha = fecha;
        this.activa = activa;
    }

    public int getVacunaID() { return VacunaID; }

    public String getNombre() { return nombre; }

    public String getFecha() { return fecha; }

    public int getActiva() {
        return activa;
    }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("ID", VacunaID);
        values.put("MascotaID", MascotaID);
        values.put("Nombre", nombre);
        values.put("Activa", activa);
        values.put("Fecha", fecha);
        return values;
    }
}
