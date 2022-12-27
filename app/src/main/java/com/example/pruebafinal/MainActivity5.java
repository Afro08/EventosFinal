package com.example.pruebafinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    private Button btnVolverEventos;
    private ListView lvEventos;
    private ArrayList<Evento> eventos;
    private ArrayAdapter<Evento> adaptadorEventos;


    //References
    private void referencias (){
        btnVolverEventos = findViewById(R.id.btnVolverEventos);
        lvEventos = findViewById(R.id.lvEventos);
        eventos = new ArrayList<Evento>();
    }

    private void eventos(){
        btnVolverEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lvEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity5.this);
                adb.setTitle("Borrar?");
                adb.setMessage("Esta seguro de querer borrar?" );
                final int posicionParaEliminar = posicion;
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        borrarEvento(posicion+1);
                        eventos.remove(posicion);
                        adaptadorEventos.notifyDataSetChanged();

                    }});
                adb.show();
                return true;
            }

        });

    }
    public void borrarEvento(Integer idEvento){
        AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        miBD.execSQL("Delete from eventos  WHERE id_evento= '" +idEvento+ "'");
    }
    private void mostrarEventos() {
        AdministradorBD adbd = new AdministradorBD(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        String nombre = getIntent().getExtras().getString("nombreUsuario");
        try {
            Cursor c = miBD.rawQuery("Select * from eventos WHERE usuario='" + nombre + "'", null);
            if (c.moveToFirst()) {
                Log.d("TAG_", "Registros recuperados " + c.getCount());
                do {
                    Evento e = new Evento();
                    e.setEvento(c.getString(1));
                    e.setImportancia(c.getString(3));
                    e.setObservacion(c.getString(4));
                    e.setFechaString(c.getString(2));
                    e.setNombre(c.getString(5));
                    e.setDias(c.getInt(6));
                    eventos.add(e);

                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            Log.e("TAG_", ex.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        referencias();
        eventos();
        mostrarEventos();
        adaptadorEventos = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_1, eventos);
        lvEventos.setAdapter(adaptadorEventos);
    }


}