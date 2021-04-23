package com.example.petcare;

public class MascotaItem {

    private long idMascota;
    private int imagen;
    private String nombre, edad, vacuna;

    public MascotaItem(long idMascota, int imagen, String nombre, String edad, String vacuna) {
        this.idMascota = idMascota;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.vacuna = vacuna;
    }

    public long getIdMascota() { return idMascota; }

    public int getImagen() { return imagen; }

    public String getNombre() { return nombre; }

    public String getEdad() { return edad; }

    public String getVacuna() { return vacuna; }
}
