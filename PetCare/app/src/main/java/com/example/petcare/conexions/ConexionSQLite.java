package com.example.petcare.conexions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petcare.entities.MascotaItem;
import com.example.petcare.entities.VacunaItem;

public class ConexionSQLite {
    public static final String TABLE_MASCOTA = "Mascota";
    public static final String TABLE_VACUNA = "Vacuna";

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

    /**
     * Metodos utilizados para actualizar la BD
     * Reciben la llave primaria de la tabla en cuestion
     * y un objeto con los parametros que se desean cambiar.
     * @return el resultado de la operacion en la BD
     */
    public int Update(MascotaItem item,long MascotaID){
        db = conn.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        int resul = db.update(TABLE_MASCOTA,item.toContentValues(),"ID = ?",new String[]{String.valueOf(MascotaID)});
        db.close();
        return resul;
    }

    /**
     * Metodos utilizados para eliminar registros en la BD
     * Reciben la llave primaria de la tabla en cuestion
     * @return el resultado de la operacion en la BD
     */
    public int Delete(long MascotaID){
        db = conn.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        int resul = db.delete(TABLE_MASCOTA, "ID = ?", new String[]{String.valueOf(MascotaID)});
        db.close();
        return resul;
    }

    /**
     * Metodo para leer infromacion de la BD.
     * @param table a consultar
     * @return un arreglo con la representacion de la tabla
     */
    public String[][] Read(String table){
        String[][] result;
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+table;
        Cursor c= db.rawQuery(query, null);
        if (c.moveToFirst()) {
            result = new String[c.getCount()][c.getColumnCount()];
            int i=0;
            do {
                for(int j=0; j<c.getColumnCount(); j++)
                    result[i][j] = c.getString(j);
                i++;
            } while (c.moveToNext());
        }
        else
            result=new String[0][0];
        db.close();
        return result;
    }

    /**
     * Metodo para leer infromacion de la BD.
     * Reciben la tabla, las columnas y la condicion a buscar
     * @return un arreglo con la misma estructura de una tabla
     */
    public String[][] Read(String table, String columns, String where){
        String[][] result;
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT "+columns+" FROM "+table+" WHERE "+where;
        Cursor c= db.rawQuery(query, null);
        if (c.moveToFirst()) {
            result = new String[c.getCount()][c.getColumnCount()];
            int i=0;
            do {
                for(int j=0; j<c.getColumnCount(); j++)
                    result[i][j] = c.getString(j);
                i++;
            } while (c.moveToNext());
        }
        else
            result=new String[0][0];
        db.close();
        return result;
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        /* Como no existen el tipo de dato boolean, se utilizará el Integer donde 0 es false y 1 es true
        Como tampoco existe el tipo de dato Datetime, se utilizará el TEXT con el formato DD/MM/AAAA
         */
        String crear_mascota = "CREATE TABLE "+TABLE_MASCOTA+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Imagen TEXT NOT NULL, " +
                "Nombre TEXT NOT NULL, " +
                "Tipo TEXT NOT NULL, " +
                "Edad INTEGER NOT NULL)";

        String crear_vacuna = "CREATE TABLE  "+TABLE_VACUNA+" (" +
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
