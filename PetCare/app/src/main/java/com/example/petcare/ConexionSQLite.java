package com.example.petcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite {
    private SQLiteHelper conn; //conexion a BD.
    private SQLiteDatabase db;

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        conn = new SQLiteHelper(context,name,factory,version);
    }

    /**
     * Metodos sobrecargados utilizados para la insercion de un nuevo objeto a la BD.
     * Reciben el objeto/registro que se desea Insertar.
     * @return resultado de la insercion
     */
    public long Insert(MascotaItem mascota){
        db = conn.getWritableDatabase();
        long result = db.insert("Mascota", null, mascota.toContentValues());
        db.close();
        return result;
    }

    public long Insert(VacunaItem vacuna){
        db = conn.getWritableDatabase();
        long result = db.insert("Vacuna", null, vacuna.toContentValues());
        db.close();
        return result;
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        /* Como no existen el tipo de dato boolean, se utilizará el Integer donde 0 es false y 1 es true
        Como tampoco existe el tipo de dato Datetime, se utilizará el TEXT con el formato DD/MM/AAAA
         */
        String crear_mascota = "CREATE TABLE  Mascota (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Imagen INTEGER NOT NULL, " +
                "Nombre TEXT NOT NULL, " +
                "Tipo TEXT NOT NULL, " +
                "Edad INTEGER NOT NULL)";

        String crear_vacuna = "CREATE TABLE  Vacuna (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MascotaID INTEGER, " +
                "Nombre TEXT NOT NULL, " +
                "Activa INTEGER NOT NULL, " +
                "Fecha TEXT NOT NULL, " +
                "FOREIGN KEY (MascotaID) REFERENCES Mascota(ID) ON DELETE CASCADE ON UPDATE CASCADE)";

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*Se ejecuta automaticamente para crear la BD si no existe*/
            db.execSQL(crear_mascota);
            db.execSQL(crear_vacuna);
            //Activar las foreign Keys
            db.execSQL("PRAGMA foreign_keys=ON");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Se ejecuta cuando la version de la BD cambia, por lo que se define la migracion de la estructura*/
            db.execSQL("DROP TABLE IF EXISTS Mascota");
            db.execSQL("DROP TABLE IF EXISTS Vacuna");

            db.execSQL(crear_mascota);
            db.execSQL(crear_vacuna);
            //Activar las foreign Keys
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }
}
