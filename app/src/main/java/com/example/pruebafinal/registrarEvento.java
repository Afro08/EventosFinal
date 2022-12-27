package com.example.pruebafinal;


public class registrarEvento {
    private String evento;
    private String importancia;
    private String observacion;
    private String fechaString;
    private String nombre;
    private Integer Dias;

    public registrarEvento(String evento, String importancia, String observacion, String fechaString, String nombre, Integer dias) {
        this.evento = evento;
        this.importancia = importancia;
        this.observacion = observacion;
        this.fechaString = fechaString;
        this.nombre = nombre;
        Dias = dias;
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
