package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity7 extends AppCompatActivity {
    private TextInputLayout tilNombreEvento, tilImportancia, tilObservacion, tilDiasAntes;
    private CalendarView cvFechaEvento;
    private Button btnVolver, btnContinuar;

    private ArrayList <registrarEvento> registro;

    //Referencias
    private void referencias(){
        tilNombreEvento = findViewById(R.id.tilNombreEvento);
        tilImportancia = findViewById(R.id.tilImportancia);
        tilObservacion = findViewById(R.id.tilObservacion);
        cvFechaEvento = findViewById(R.id.cvFechaEvento);
        btnContinuar = findViewById(R.id.btnRegistrarEvento);
        btnVolver= findViewById(R.id.btnHome);
        tilDiasAntes= findViewById(R.id.tilDiasAntes);
    }

    public void registrarEnBD(registrarEvento registro){

        try {
            AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();
            ContentValues reg = new ContentValues();
            reg.put("nombre_evento",registro.getEvento());
            reg.put("fecha_evento",registro.getFechaString());
            reg.put("importancia",registro.getImportancia());
            reg.put("observacion",registro.getObservacion());
            reg.put("usuario",registro.getNombre());
            reg.put("RECORDATORIO",registro.getDias());
            miBD.insert("eventos",null, reg);
            miBD.close();

        }catch (Exception ex){
            Log.e("TAG_" , ex.toString());
        }
    }


    private void GrabarEvento (){
        String evento, importancia, observacion;
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        Integer Dias;
        evento = tilNombreEvento.getEditText().getText().toString();
        importancia = tilImportancia.getEditText().getText().toString();
        observacion = tilObservacion.getEditText().getText().toString();
        tilDiasAntes.getEditText().getText().toString();
        long fecha=cvFechaEvento.getDate();
        SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formateo.format(new Date(fecha));
        Dias = Integer.parseInt(tilDiasAntes.getEditText().getText().toString());
        Log.e("TAG_", String.valueOf(Dias));
        registrarEvento registro = new registrarEvento (evento, importancia, observacion, fechaString, nombre, Dias);




        registrarEnBD(registro);
        //Log.d("TAG_","FECHA " + fechaString);
    }

    private void eventos(){
        btnContinuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("TAG_","boton registrar");
                GrabarEvento();
                finish();
            }

        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        referencias();
        eventos();

    }
}