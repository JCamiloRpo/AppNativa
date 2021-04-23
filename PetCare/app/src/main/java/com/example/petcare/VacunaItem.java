package com.example.petcare;

public class VacunaItem {
    private long idVacuna;
    private String nombre, fecha;
    private boolean activa;

    public VacunaItem(long idVacuna, String nombre, boolean activa, String fecha) {
        this.idVacuna = idVacuna;
        this.nombre = nombre;
        this.fecha = fecha;
        this.activa = activa;
    }

    public long getIdVacuna() {
        return idVacuna;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean getActiva() {
        return activa;
    }
}
