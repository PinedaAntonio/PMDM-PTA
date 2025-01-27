package com.example.myapplication;

import java.io.Serializable;

public class Videojuego implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;  // Added for database storage
    private String nombre;
    private String descripcion;
    private int portadaResId;
    private boolean jugado;
    private float valoracion;
    private String web;
    private String telefono;
    private String fechaLanzamiento;

    public Videojuego(int id, String nombre, String descripcion, int portadaResId, boolean jugado, float valoracion, String web, String telefono, String fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portadaResId = portadaResId;
        this.jugado = jugado;
        this.valoracion = valoracion;
        this.web = web;
        this.telefono = telefono;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Videojuego(String nombre, String descripcion, int portadaResId, boolean jugado, float valoracion, String web, String telefono, String fechaLanzamiento) {
        this.id = -1;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portadaResId = portadaResId;
        this.jugado = jugado;
        this.valoracion = valoracion;
        this.web = web;
        this.telefono = telefono;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getPortadaResId() { return portadaResId; }
    public boolean getJugado() { return jugado; }
    public float getValoracion() { return valoracion; }
    public String getWeb() { return web; }
    public String getTelefono() { return telefono; }
    public String getFechaLanzamiento() { return fechaLanzamiento; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPortadaResId(int portadaResId) { this.portadaResId = portadaResId; }
    public void setJugado(boolean jugado) { this.jugado = jugado; }
    public void setValoracion(float valoracion) { this.valoracion = valoracion; }
    public void setWeb(String web) { this.web = web; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setFechaLanzamiento(String fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
}
