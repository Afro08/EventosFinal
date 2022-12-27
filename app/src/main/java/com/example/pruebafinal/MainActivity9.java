package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity9 extends AppCompatActivity {
    private TextView tvReestablecer,tvIndicacion;
    private TextInputLayout tilContrasena,tilConfirmacion;
    private Button btnCambiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        referencias();
        eventos();
    }



    private void referencias(){
        tvReestablecer = findViewById(R.id.tvReestablecer);
        tvIndicacion = findViewById(R.id.tvIndicacion);
        tilContrasena = findViewById(R.id.tilNueva);
        tilConfirmacion = findViewById(R.id.tilConfirma);
        btnCambiar = findViewById(R.id.btnCambiar);
    }

    private void pantallaHome(){
        Intent pantallaHome = new Intent(this, MainActivity.class);
        startActivity(pantallaHome);
        finishAndRemoveTask();
    }

    private void eventos(){
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reestablecerContrasena();

                pantallaHome();

            }
        });
    }
    private void reestablecerContrasena(){
        try {

            String nombre = getIntent().getExtras().getString("nombreUsuario");
            String nueva = tilContrasena.getEditText().getText().toString();
            String confirmar = tilConfirmacion.getEditText().getText().toString();
            AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null,1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();
            if (!nueva.isEmpty() && !confirmar.isEmpty()) {
                if (nueva.equals(confirmar)) {
                    ContentValues c = new ContentValues();
                    c.put("password", nueva);
                    miBD.update("usuarios", c, "usuario=?", new String[]{nombre});
                    Toast.makeText(this, "Reestableber contraseña correcto!!", Toast.LENGTH_SHORT).show();
                } else {
                    tilContrasena.setError("Contraseñas no coinciden");
                    tilConfirmacion.setError("Contraseñas no coinciden");
                }
            }
            else{
                Toast.makeText(this, "PORFAVOR RELLENAR AMBOS CAMPOS", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex) {
            Log.e("TAG_", ex.toString());
        }

    }
}