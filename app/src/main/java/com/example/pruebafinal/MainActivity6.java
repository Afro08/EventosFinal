package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {

    private TextView tvBienvenido;
    private Button btnVerEventos, btnRegistrar, btnEliminarUsuario ,btnDesloguear;

    private void pantallaVisualizarEventos(){
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        Intent pantallaregistrarevento = new Intent(this, MainActivity5.class);
        pantallaregistrarevento.putExtra("nombreUsuario",nombre);
        startActivity(pantallaregistrarevento);
    }


    private void pantallaregistrarevento(){
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        Intent pantallaregistrarevento = new Intent(this, MainActivity7.class);
        pantallaregistrarevento.putExtra("nombreUsuario",nombre);
        startActivity(pantallaregistrarevento);
    }



    //REferences


    private void referencias(){
        btnRegistrar = findViewById(R.id.btnAgregarEventos);
        btnVerEventos= findViewById(R.id.btnVerEventos);
        tvBienvenido = findViewById(R.id.tvBienvenido);
        btnEliminarUsuario = findViewById(R.id.btnEliminaUsuario);
        btnDesloguear= findViewById(R.id.btnDesloguear);
    }


    private void eliminarcUsuario() {
        AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        miBD.delete("eventos","usuario = '"+ nombre+ "'",null);
        miBD.delete("usuarios","usuario = '"+ nombre+ "'",null);
        //Cursor d = miBD.rawQuery("DELETE from eventos WHERE usuario='"+nombre+"'", null);
        //Cursor c = miBD.delete("DELETE from usuarios WHERE usuario='"+nombre+"'", null);
        Log.d("TAG_",nombre );
    }


    private void eventos(){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pantallaregistrarevento();

            }
        });
        btnVerEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {pantallaVisualizarEventos();

            }
        });

        btnEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {eliminarcUsuario();
                finish();


            }
        });

        btnDesloguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        referencias();
        eventos();
        String nombre = getIntent().getExtras().getString("nombreUsuario").toUpperCase();
        String original = tvBienvenido.getText().toString();
        tvBienvenido.setText(original+ nombre);
    }



}