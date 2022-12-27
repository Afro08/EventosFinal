package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilUser, tilPassword;
    private Button btnLogin, btnRegister, btnOlvide;
    private TextView setError;
    String user, password, pregunta, respuesta, last_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referencias();
        eventos();

        try {
            AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();
            Cursor d = miBD.rawQuery("Select * from last_login", null);
            last_user = d.getString(0);
            miBD.close();
            Log.d("TAG_", "" + d.getString(0));

        }catch (Exception ex){

        }};


//REFERENCIA
    private void referencias(){
        tilUser = findViewById(R.id.tilUser);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnOlvide = findViewById(R.id.btnOlvide);
        setError = findViewById(R.id.setError);
    }
    private void pantallaregistrar() {
        Intent pantallaregistrar = new Intent(this, MainActivity2.class);
        startActivity(pantallaregistrar);
    }
    private void login() {
        user = tilUser.getEditText().getText().toString();
        password = tilPassword.getEditText().getText().toString();


        try {
            AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();
//miBD.update("usuarios", c, "usuario=?", new String[]{nombre});
            Cursor c = miBD.rawQuery("Select * from usuarios WHERE usuario='"+user+"'", null);


                if (c.moveToFirst()) {
                    Log.d("TAG_", "Registros recuperados " + c.getCount());
                    do {
                        if
                        (user.isEmpty() || password.isEmpty()){
                            setError.setText("Rellene todos los campos!");
                            setError.setTextColor(Color.parseColor("#FF0000"));
                        }
                        else if (user.equals(c.getString(1))  && password.equals(c.getString(2))){
                            if (last_user != user){
                                ContentValues values = new ContentValues();
                                values.put("usuario", user);
                                miBD.update("last_login",values, "usuario=?",  new String[]{last_user});
                                Intent pantallaregistrar = new Intent(this, MainActivity6.class);
                                pantallaregistrar.putExtra("nombreUsuario",user);
                                startActivity(pantallaregistrar);
                            }else{



                                Toast.makeText(this, "Ingreso correcto!!", Toast.LENGTH_SHORT).show();
                                ContentValues values = new ContentValues();
                                values.put("usuario", user);
                                //miBD.insert("last_login",null, values);
                                miBD.close();
                                Intent pantallaregistrar = new Intent(this, MainActivity6.class);
                                pantallaregistrar.putExtra("nombreUsuario",user);
                                startActivity(pantallaregistrar);
                            }

                        }
                        else if
                        (!user.equals(c.getString(1)) || !password.equals(c.getString(2))){
                            setError.setText("Usuario o contraseña incorrecto!");
                            setError.setTextColor(Color.parseColor("#FF0000"));
                        }
                    }while (c.moveToNext());
                }

             } catch (Exception ex) {
            Log.e("TAG_", ex.toString());
            }


    }
    private void pantallaOlvide(){
        Intent pantallaOlvide = new Intent(this,MainActivity4.class);
        startActivity(pantallaOlvide);
    }


    private void eventos(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantallaregistrar();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG_","boton LOGIN");
                login();
            }
        });
        btnOlvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG_","boton olvidé");
                pantallaOlvide();

            }
        });
    }
}