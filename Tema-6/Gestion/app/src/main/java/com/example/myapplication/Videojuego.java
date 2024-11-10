package com.example.myapplication;

public class Videojuego {
    private String nombre;
    private String descripcion;
    private int portadaResId; // ID del recurso de la imagen (en lugar de una URL o Image)
    private boolean jugado;
    private float valoracion;
    private String web;
    private String telefono;

    public Videojuego(String nombre, String descripcion, int portadaResId, boolean jugado, float valoracion, String web, String telefono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portadaResId = portadaResId;
        this.jugado = jugado;
        this.valoracion = valoracion;
        this.web = web;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getPortadaResId() { return portadaResId; }
    public boolean isJugado() { return jugado; }
    public float getValoracion() { return valoracion; }
    public String getWeb() { return web; }
    public String getTelefono() { return telefono; }
}
