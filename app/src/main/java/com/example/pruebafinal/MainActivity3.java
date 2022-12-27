package com.example.pruebafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    private Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        referencias();
        eventos();


    }
    private void referencias(){
        btnAgregar = findViewById(R.id.btnAgregar);
    }
    private void eventos(){
    btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            agregarEvento();
        }
    });}
    private void agregarEvento(){
        Intent pantallaReestablecer = new Intent(this, MainActivity7.class);
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        startActivity(pantallaReestablecer);
    }

}