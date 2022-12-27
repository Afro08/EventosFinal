package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity4 extends AppCompatActivity {
    private TextView tvRecuperar,tvDescripcion,tvError;
    private TextInputLayout tilUsuario,tilRespuesta;
    private Spinner spnPregunta;
    private Button btnRecuperar,btnVolver;
    private String [] arraySpn;
    private ArrayAdapter<String> adaptadorSpn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        referencias();
        eventos();
        poblar();
    }

    private void referencias(){
        tvRecuperar = findViewById(R.id.tvRecuperar);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvError = findViewById(R.id.tvError);
        tilUsuario = findViewById(R.id.tilUsuario);
        tilRespuesta = findViewById(R.id.tilRespuesta);
        spnPregunta = findViewById(R.id.spnPregunta);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnVolver = findViewById(R.id.btnVolver);
        arraySpn = new String [6];
    }
    private void eventos(){
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarContraseña();
            }
        });
    }
    private void recuperarContraseña(){

        String usuario = tilUsuario.getEditText().getText().toString();
        String pregunta = spnPregunta.getSelectedItem().toString();
        String respuesta = tilRespuesta.getEditText().getText().toString();
        try {
            AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();

            Cursor c = miBD.rawQuery("Select * from usuarios WHERE usuario='"+usuario+"'", null);
            if (c.moveToFirst()) {
                Log.d("TAG_", "Registros recuperados " + c.getCount());
                Log.d("TAG_",usuario+pregunta+respuesta);
                do {
                    if (usuario.equals(c.getString(1)) && pregunta.equals(c.getString(3)) && respuesta.equals(c.getString(4)))
                    {
                        Log.d("TAG_","Datos correctos");
                        Toast.makeText(this,"Ingreso de datos aceptado!",Toast.LENGTH_SHORT).show();
                        Intent pantallaReestablecer = new Intent(this, MainActivity9.class);
                        pantallaReestablecer.putExtra("nombreUsuario",usuario);
                        startActivity(pantallaReestablecer);
                    }
                    else{
                        tvError.setText("Los datos ingresados no coinciden con nuestros registros");
                        tvError.setTextColor(Color.parseColor("#FF0000"));
                    }
                }while (c.moveToNext());
            }
        } catch (Exception ex) {
            Log.e("TAG_", ex.toString());
        }
    }

    private void poblar(){
        arraySpn [0] = "Seleccione pregunta secreta";
        arraySpn [1] = "¿Cuál es tu comida favorita?";
        arraySpn [2] = "¿Cuál es el nombre de su primer profesor?";
        arraySpn [3] = "¿Cuál es el segundo nombre de tu padre?";
        arraySpn [4] = "¿Cuál es tu ciudad natal?";
        arraySpn [5] = "¿Cuál es el nombre de tu mascota?";
        adaptadorSpn = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpn);
        spnPregunta.setAdapter(adaptadorSpn);
    }
}