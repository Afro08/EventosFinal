package com.example.pruebafinal;

import androidx.annotation.NonNull;

public class Evento {
    private String evento;
    private String importancia;
    private String observacion;
    private String fechaString;
    private String nombre;
    private Integer Dias;

    @NonNull
    @Override
    public String toString() {
        return "Evento: " +evento + "\n"
                + ""+
                "Importancia: " + importancia+  "\n" +
                "Observación "+ observacion+" " +"\n" +
                "Fecha: " + fechaString+"\n" +
                "Aviso en :" + Dias + " dias.";
    }

    public Evento(String evento, String importancia, String observacion, String fechaString, String nombre, Integer dias) {
        this.evento = evento;
        this.importancia = importancia;
        this.observacion = observacion;
        this.fechaString = fechaString;
        this.nombre = nombre;
        Dias = dias;
    }

    public Evento() {

    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDias() {
        return Dias;
    }

    public void setDias(Integer dias) {
        Dias = dias;
    }
}
