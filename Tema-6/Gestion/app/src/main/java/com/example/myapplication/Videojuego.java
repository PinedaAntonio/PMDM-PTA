package com.example.myapplication;

import java.io.Serializable;

public class Videojuego implements Serializable {
    private static final long serialVersionUID = 1L;
    public String nombre;
    public String descripcion;
    public int portadaResId; // ID del recurso de la imagen (en lugar de una URL o Image)
    public boolean jugado;
    public float valoracion;
    public String web;
    public String telefono;

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
    public boolean getJugado() { return jugado; }
    public float getValoracion() { return valoracion; }
    public String getWeb() { return web; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPortadaResId(int portadaResId) {
        this.portadaResId = portadaResId;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
