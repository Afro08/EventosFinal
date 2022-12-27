package com.example.pruebafinal;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdministradorBD extends SQLiteOpenHelper {

    public AdministradorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists usuarios (id_usuario INTEGER primary key autoincrement, usuario text UNIQUE, password text, pregunta TEXT, respuesta text )");
        db.execSQL("CREATE TABLE IF NOT EXISTS eventos (id_evento INTEGER PRIMARY KEY autoincrement, nombre_evento TEXT, fecha_evento    TEXT, importancia    TEXT, observacion    TEXT, usuario TEXT, RECORDATORIO INTEGER, FOREIGN KEY (usuario) references usuarios (id_usuario))");
        db.execSQL("CREATE TABLE IF NOT EXISTS last_login(usuario text primary key)");
    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}