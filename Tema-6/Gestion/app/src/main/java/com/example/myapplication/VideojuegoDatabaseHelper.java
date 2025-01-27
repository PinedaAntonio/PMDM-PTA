package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class VideojuegoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "videojuegos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VIDEOJUEGOS = "videojuegos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_PORTADA_RES_ID = "portada_res_id";
    private static final String COLUMN_JUGADO = "jugado";
    private static final String COLUMN_VALORACION = "valoracion";
    private static final String COLUMN_WEB = "web";
    private static final String COLUMN_TELEFONO = "telefono";
    private static final String COLUMN_FECHA_LANZAMIENTO = "fecha_lanzamiento";

    public VideojuegoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_VIDEOJUEGOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_DESCRIPCION + " TEXT, " +
                COLUMN_PORTADA_RES_ID + " INTEGER, " +
                COLUMN_JUGADO + " INTEGER, " +
                COLUMN_VALORACION + " REAL, " +
                COLUMN_WEB + " TEXT, " +
                COLUMN_TELEFONO + " TEXT, " +
                COLUMN_FECHA_LANZAMIENTO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEOJUEGOS);
        onCreate(db);
    }

    // Insertar un videojuego
    public long insertarVideojuego(Videojuego videojuego) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, videojuego.getNombre());
        values.put(COLUMN_DESCRIPCION, videojuego.getDescripcion());
        values.put(COLUMN_PORTADA_RES_ID, videojuego.getPortadaResId());
        values.put(COLUMN_JUGADO, videojuego.getJugado() ? 1 : 0);
        values.put(COLUMN_VALORACION, videojuego.getValoracion());
        values.put(COLUMN_WEB, videojuego.getWeb());
        values.put(COLUMN_TELEFONO, videojuego.getTelefono());
        values.put(COLUMN_FECHA_LANZAMIENTO, videojuego.getFechaLanzamiento());

        long id = db.insert(TABLE_VIDEOJUEGOS, null, values);
        db.close();
        return id;
    }

    // Obtener todos los videojuegos
    public List<Videojuego> obtenerTodosLosVideojuegos() {
        List<Videojuego> videojuegos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VIDEOJUEGOS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Videojuego videojuego = new Videojuego(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PORTADA_RES_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_JUGADO)) == 1,
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_VALORACION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WEB)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_LANZAMIENTO))
                );
                videojuegos.add(videojuego);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return videojuegos;
    }

    // Actualizar un videojuego
    public int actualizarVideojuego(Videojuego videojuego) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, videojuego.getNombre());
        values.put(COLUMN_DESCRIPCION, videojuego.getDescripcion());
        values.put(COLUMN_PORTADA_RES_ID, videojuego.getPortadaResId());
        values.put(COLUMN_JUGADO, videojuego.getJugado() ? 1 : 0);
        values.put(COLUMN_VALORACION, videojuego.getValoracion());
        values.put(COLUMN_WEB, videojuego.getWeb());
        values.put(COLUMN_TELEFONO, videojuego.getTelefono());
        values.put(COLUMN_FECHA_LANZAMIENTO, videojuego.getFechaLanzamiento());

        int rows = db.update(TABLE_VIDEOJUEGOS, values, COLUMN_NOMBRE + "=?", new String[]{videojuego.getNombre()});
        db.close();
        return rows;
    }

    // Eliminar un videojuego
    public int eliminarVideojuego(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_VIDEOJUEGOS, COLUMN_NOMBRE + "=?", new String[]{nombre});
        db.close();
        return rows;
    }
}

